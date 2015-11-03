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

    public Routing(String routerId) throws Exception {
        this.routerId = routerId.charAt(0) - (int)'X';
        // This program is meant to run on this machine alone, so just set the IP to localhost
        this.ip = InetAddress.getByName("localhost");

        // Initialize the hashmap for the current router
        this.setUpConfigMap();
        this.costs = this.distancesTable[this.routerId];
        for (int cost: costs) {
            System.out.print(cost + " ");
        }
        System.out.println();
        System.out.printf("Router %s is running on port %d\n", this.convertRouterIdToChar(this.routerId), this.getMyPortNumber());
        System.out.printf("Distance vector on router %s is:\n", this.convertRouterIdToChar(this.routerId));
        this.printDistanceVector(this.distancesTable[this.routerId]);
    }

    /**
        Returns the port number for the current router as an int

        @return int
    */
    private int getMyPortNumber() {
        return this.ports[(this.routerId)];
    }

    /**
        Returns the port number for the router with the routerId passed in

        @param routerId: String, the router Id to get the port number for
        @return int
    */
    private int getPortNumberForRouterId(int routerId) {
        return this.ports[routerId];
    }

    /**
        Initializes the configs Hashmap for the router
    */
    private void setUpConfigMap() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(CONFIG_FILE_NAME));
        String line;

        // Set up the port numbers
        line = in.readLine();
        String[] portsFromFile = line.split("\t");
        int router = 0;
        for (String port: portsFromFile) {
            this.ports[router] = Integer.parseInt(port);
            router++;
        }

        // Set up the Distance Vectors
        router = 0;
        while ((line = in.readLine()) != null) {
            // Each string in the file is seperated by a tab
            String[] array = line.split("\t");

            // Read in each element from the line
            for (int i = 0; i < array.length; i++) {
                this.distancesTable[router][i] = Integer.parseInt(array[i]);
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
                this.updateOtherNodes();
                // Set changed to false to stop infinite loop of updating
                changed = false;
            }

            this.receive();

            // TODO: Create function to update the distance vectors
            changed = this.update();
            if (changed) {
                System.out.printf("Distance vector on router %c:\n", this.convertRouterIdToChar(this.routerId));
                this.printDistanceVector(this.distancesTable[this.routerId]);
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
        for (int router = 0; router < this.distancesTable.length; router++) {
            // Make a check that we aren't updating ourself
            if (router != this.routerId) {
                byte[] sendData = new byte[4];
                // The router the update is coming from is the first byte in the packet
                sendData[0] = (byte) this.routerId;

                // Distance to X, Y, Z is the second, third, and fourth byte respectively
                for (int location = 1; location <= this.distancesTable[router].length; location++) {
                    sendData[location] = (byte) this.distancesTable[this.routerId][location - 1];
                }

                // Create the packet to send using the data just created
                DatagramPacket updatePkt = new DatagramPacket(
                    sendData,
                    sendData.length,
                    this.ip,
                    this.ports[router]
                );
                System.out.printf("Sending update to router %d\n", router);
                // Send the packet
                this.send(updatePkt);
            }
        }
    }

    /**
        Sends a packet using UDP

        @param pkt: DatagramPacket, the packet to be sent
    */
    private void send(DatagramPacket pkt) throws Exception {
        this.socket = new DatagramSocket(this.getMyPortNumber());
        this.socket.send(pkt);
        this.socket.close();
    }

    /**
        Waits to recieve a packet to update the distance vectors

        @return int[] the updated distance vector that was sent to this router
    */
    private void receive() throws Exception {
        this.socket = new DatagramSocket(this.getMyPortNumber());
        byte[] rcvData = new byte[4];
        DatagramPacket rcvPkt = new DatagramPacket(rcvData, rcvData.length);

        // Wait for two updates, one from the other two nodes not this one
        this.socket.receive(rcvPkt);
        byte[] data = rcvPkt.getData();

        int updatingRouter = (int) data[0];
        System.out.printf("Receives distance vector from router %c: ", (char)('X' + updatingRouter));
        // Iterate over the received data and add it to the distancesTable
        // 0 -> router that sent data, 1-> distance to X, 2 -> distance to Y
        // 3 -> distance to Z
        for (int j = 0; j < this.distancesTable[updatingRouter].length; j++) {
            this.distancesTable[updatingRouter][j] = (int) data[j + 1];
        }

        this.printDistanceVector(this.distancesTable[updatingRouter]);
        this.socket.close();
    }

    /**
        Runs the distance vector algorithm using the receivedData

        @param int[] receivedData: an array where the first position is the sending router and the
                                   next three are all the updated distancesTable
        @return boolean, true if we have made a change, false otherwise
    */
    private boolean update() {
        boolean changed = false;

        for (int i = 0; i < this.routerId; i++) {
            int currentValue = this.distancesTable[this.routerId][i];

            // Iterate over all nodes past the current one to use them as middle men
            for (int j = 0; j < this.distancesTable.length; j++) {
                if (j != this.routerId && j != i) {
                    int updatedValue = bellmanFord(i, j);

                    // This will keep changed as true if it gets changed to True and keep checking when
                    // false
                    if (currentValue != updatedValue) {
                        this.distancesTable[this.routerId][i] = updatedValue;
                        //this.printDistanceVector(this.distancesTable[this.routerId]);
                        changed = true;
                    }
                }

            }
        }

        for (int i = this.distancesTable.length - 1; i > this.routerId; i--) {
            int currentValue = this.distancesTable[this.routerId][i];

            for (int j = 0; j < this.distancesTable.length; j++) {
                if (j != this.routerId && j != i) {
                    int updatedValue = bellmanFord(i, j);

                    // This will keep changed as true if it gets changed to True and keep checking when
                    // false
                    if (currentValue != updatedValue) {
                        this.distancesTable[this.routerId][i] = updatedValue;
                        this.printDistanceVector(this.distancesTable[this.routerId]);
                        changed = true;
                    }
                }

            }
        }

        return changed;
    }

    private int bellmanFord(int destination, int middleMan) {
        int directCost = this.costs[destination];
        //System.out.printf("Cost from %d to %d = %d\n", middleMan, destination, this.distancesTable[middleMan][destination]);
        int middleManCost = this.costs[middleMan] + this.distancesTable[middleMan][destination];;
        // Run the Belman Ford equation, compare direct cost, to cost going through other router
        //System.out.printf("Distance from %d to %d is %d\n", this.routerId, destination, directCost);
        //System.out.printf("Distance from %d to %d to %d is %d\n", this.routerId, middleMan, destination, middleManCost);
        //this.printTable();

        int updatedValue = Math.min(
            directCost,
            middleManCost
        );

        return updatedValue;
    }

    /**
        Prints the distance vector for the current router
    */
    private void printDistanceVector(int[] vector) {
        String printString = "";
        for (int num: vector) {
            printString += num + ", ";
        }
        // Remove the last ', ' from the string
        printString = cutOffEnd(printString, 2);
        System.out.printf("<%s>\n", printString);
    }

    private void printTable() {
        for (int i = 0; i < this.distancesTable.length; i++) {
            for (int j = 0; j < this.distancesTable.length; j++) {
                System.out.print(this.distancesTable[i][j]);
            }
            System.out.println();
        }
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
