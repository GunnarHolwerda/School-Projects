import java.net.*;
import java.util.*;
import java.io.*;

class Routing {
    public static final String CONFIG_FILE_NAME = "configuration.txt";
    private int[][] distancesTable = new int[3][3];
    private int[] ports = new int[3];
    private int[] costs = new int[3];
    private InetAddress ip;
    private int routerId;
    private DatagramSocket socket;
    private boolean costUpdated = false;
    private boolean updatedAfterCostUpdate = false;

    public Routing(String routerId) throws Exception {
        // Convert the routerId to an integer for easy indexing of arrays
        this.routerId = routerId.charAt(0) - (int)'X';
        // This program is meant to run on this machine alone, so just set the IP to localhost
        this.ip = InetAddress.getByName("localhost");

        // Initialize the 2D distance table array for the current router
        setUpDistancesTable();

        // Store the direct costs to each node
        for (int i = 0; i < this.distancesTable.length; i++) {
            this.costs[i] = this.distancesTable[this.routerId][i];
        }

        System.out.printf("Router %s is running on port %d\n", convertRouterIdToChar(this.routerId), getMyPortNumber());
        System.out.printf("Distance vector on router %s is:\n", convertRouterIdToChar(this.routerId));
        printDistanceVectorForRouter(this.routerId);

        // Open the socket to listen for requests
        this.socket = new DatagramSocket(getMyPortNumber());

        System.out.println("Sleeping for 10 seconds to give time to get all programs running before sending data");
        Thread.sleep(10000);
    }

    /**
        Returns the port number for the current router as an int

        @return int
    */
    private int getMyPortNumber() {
        return ports[routerId];
    }

    /**
        Initializes the configs Hashmap for the router
    */
    private void setUpDistancesTable() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(CONFIG_FILE_NAME));
        String line;

        // Set up the port numbers
        line = in.readLine();
        String[] portsFromFile = line.split("\t");
        int router = 0;
        for (String port: portsFromFile) {
            ports[router] = Integer.parseInt(port);
            router++;
        }

        // Set up the Distance Vectors
        router = 0;
        while ((line = in.readLine()) != null) {
            // Each string in the file is seperated by a tab
            String[] array = line.split("\t");

            // Read in each element from the line
            for (int i = 0; i < array.length; i++) {
                // Only update our distance vector so we aren't cheating
                if (router == routerId) {
                    int cost = Integer.parseInt(array[i]);
                    distancesTable[router][i] = cost;
                    costs[i] = cost;
                }
                else {
                    // Set value to large value because we don't know it yet.
                    distancesTable[router][i] = 9999999;
                }
            }

            router++;
        }
    }

    /**
        Starts the router waiting for updates and updates it's own tables
        and sends updates
    */
    public void start() throws Exception {
        boolean changed = true;
        while (true) {
            if (changed) {
                // If we have changed we need to update the other routers
                updateOtherNodes();
            }

            receive();

            changed = update();
            if (changed) {
                System.out.printf("Distance vector on router %c:\n", this.convertRouterIdToChar(this.routerId));
                this.printDistanceVectorForRouter(this.routerId);
            }
            else {
                System.out.printf("Distance vector on router %c is not updated.\n", this.convertRouterIdToChar(this.routerId));
            }
        }
    }

    /**
        Updates other routing nodes currently known by this router
    */
    private void updateOtherNodes() throws Exception {
        for (int routerToUpdate = 0; routerToUpdate < distancesTable.length; routerToUpdate++) {
            // Make a check that we aren't updating ourself
            if (routerToUpdate != this.routerId) {
                byte[] sendData = new byte[4];
                // The router the update is coming from is the first byte in the packet
                sendData[0] = (byte) this.routerId;

                // Distance to X, Y, Z is the second, third, and fourth byte respectively
                for (int location = 1; location <= distancesTable[routerToUpdate].length; location++) {
                    sendData[location] = (byte) distancesTable[this.routerId][location - 1];
                }

                // Create the packet to send using the data just created
                DatagramPacket updatePkt = new DatagramPacket(
                    sendData,
                    sendData.length,
                    ip,
                    ports[routerToUpdate]
                );

                // Send the packet
                socket.send(updatePkt);
            }
        }
    }

    /**
        Waits to recieve a packet to update the distance vectors

        @return int[] the updated distance vector that was sent to this router
    */
    private void receive() throws Exception {
        byte[] rcvData = new byte[4];
        DatagramPacket rcvPkt = new DatagramPacket(rcvData, rcvData.length);
        int updatingRouter = 0;
        byte[] data = new byte[3];

        // Wait for two updates, one from the other two nodes not this one
        try {
            // Only set this timeout once when we have to update the cost between X and Y
            if (this.costUpdated == false && this.routerId != 2) {
                socket.setSoTimeout(5000);
            }
            else {
                // Set back to infinite timeout
                socket.setSoTimeout(0);
            }

            socket.receive(rcvPkt);
            data = rcvPkt.getData();
            updatingRouter = (int) data[0];
        }
        catch (Exception e) {
            // We timed out meaning that we probably have the shortest vector.
            // Let's update the value
            this.costUpdated = true;
            if (routerId == 0) {
                System.out.println("Connection between X and Y is updated to 60");
                this.costs[1] = 60;
            }
            else if (routerId == 1) {
                System.out.println("Connection between Y and X is updated to 60");
                this.costs[0] = 60;
            }
            // Update distances table to accomodate new change
            for (int router = 0; router < distancesTable.length; router++) {
                for (int i = 0; i < distancesTable.length; i++) {
                    // Only update our distance vector so we aren't cheating
                    if (router == routerId) {
                        distancesTable[router][i] = costs[i];
                    }
                }
            }
            return;
        }

        // Iterate over the received data and add it to the distancesTable
        // 0 -> router that sent data, 1-> distance to X, 2 -> distance to Y
        // 3 -> distance to Z
        for (int j = 0; j < distancesTable[updatingRouter].length; j++) {
            distancesTable[updatingRouter][j] = (int) data[j + 1];
        }

        System.out.printf("Receives distance vector from router %c: ", convertRouterIdToChar(updatingRouter));
        printDistanceVectorForRouter(updatingRouter);
    }

    /**
        Runs the distance vector algorithm using the receivedData

        @param int[] receivedData: an array where the first position is the sending router and the
                                   next three are all the updated distancesTable
        @return boolean, true if we have made a change, false otherwise
    */
    private boolean update() {
        boolean changed = false;

        for (int destination = 0; destination < distancesTable.length; destination++) {
            //this.printDistanceVectorForRouter(0);
            // Skip the iteration if we are checking distance to ourself
            if (destination != this.routerId) {
                int currentValue = distancesTable[this.routerId][destination];

                // Iterate over all nodes past the current one to use them as middle men
                for (int middleMan = 0; middleMan < distancesTable.length; middleMan++) {
                    if (middleMan != this.routerId && middleMan != destination) {
                        int updatedValue = bellmanFord(destination, middleMan);

                        // This will keep changed as true if it gets changed to True and keep checking when
                        // false
                        if (currentValue != updatedValue) {
                            this.distancesTable[this.routerId][destination] = updatedValue;
                            changed = true;
                        }
                    }

                }
            }
        }

        // We need to update if our cost has been updated no matter what. It won't be detected
        // by the normal method so we need to force an update here.
        if (this.updatedAfterCostUpdate == false && this.costUpdated) {
            this.updatedAfterCostUpdate = true;
            return true;
        }
        else {
            return changed;
        }
    }

    /**
        Runs the Bellman Ford equation to compute the shortest distance from itself to the
        destination using the middleMan passed in as the alternate

        @param destination: int, the id of the router that we want to compute the distance to
        @param middleMan: int, the id of the router to use as a middleMan between source and
                          destination
    */
    private int bellmanFord(int destination, int middleMan) {
        int directCost = this.costs[destination];
        int middleManCost = this.costs[middleMan] + this.distancesTable[middleMan][destination];

        int updatedValue = Math.min(
            directCost,
            middleManCost
        );

        return updatedValue;
    }

    /**
        Prints the distance vector for the router Id supplied

        @param routerId: int, the routerId for which to print the distance vector for
    */
    private void printDistanceVectorForRouter(int routerId) {
        int[] vector = distancesTable[routerId];
        String printString = "";
        for (int num: vector) {
            printString += num + ", ";
        }
        // Remove the last ', ' from the string
        printString = cutOffEnd(printString, 2);
        System.out.printf("<%s>\n", printString);
    }

    private char convertRouterIdToChar(int id) {
        return (char)('X' + id);
    }

    private static String cutOffEnd(String s, int n) {
        return s == null || s.length() <= n ? "" : s.substring(0, s.length() - n);
    }

    public static void main(String args[]) throws Exception {
        Scanner consoleIn = new Scanner(System.in);
        System.out.print("Enter the router's id: ");
        String routerId = consoleIn.next();

        Routing r = new Routing(routerId);
        r.start();
    }
}
