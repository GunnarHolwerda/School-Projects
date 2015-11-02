import java.net.*;
import java.util.*;
import java.io.*;

class Routing {
    public static final String CONFIG_FILE_NAME = "configuration.txt";
    private int[][] configsArray = new int[3][3];
    private int[] ports = new int[3];
    private InetAddress ip;

    private String routerId;
    private int routerConfigLocation;
    private DatagramSocket socket;

    public Routing(String routerId) throws Exception {
        this.routerId = routerId;
        this.routerConfigLocation = routerId.charAt(0) - (int)'X';
        // This program is meant to run on this machine alone, so just set the IP to localhost
        this.ip = InetAddress.getByName("localhost");

        // Initialize the hashmap for the current router
        this.setUpConfigMap();
        System.out.printf("Router %s is running on port %d\n", this.routerId, this.getMyPortNumber());
        System.out.printf("Distance vector on router %s is:\n", this.routerId);
        this.printDistanceVector();
    }

    /**
        Returns the port number for the current router as an int

        @return int
    */
    public int getMyPortNumber() {
        return this.ports[(this.routerId.charAt(0) - (int)'X')];
    }

    /**
        Returns the port number for the router with the routerId passed in

        @param routerId: String, the router Id to get the port number for
        @return int
    */
    public int getPortNumberForRouterId(String routerId) {
        return this.ports[(routerId.charAt(0) - (int)'X')];
    }

    /**
        Initializes the configs Hashmap for the router
    */
    public void setUpConfigMap() throws Exception {
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
                this.configsArray[router][i] = Integer.parseInt(array[i]);
            }

            router++;
        }
    }

    /**
        Starts the router waiting for updates and updates it's own tables
        and sends updates
    */
    public void start() throws Exception {
        while (true) {
            // TODO: Create logic to only update other nodes when a change has occured.
            this.updateOtherNodes();
            this.receive();
            // TODO: Create function to update the distance vectors
        }
    }

    /**
        Updates other routing nodes currently known by this router
    */
    public void updateOtherNodes() throws Exception {
        for (int router = 0; router < this.configsArray.length; router++) {
            // Make a check that we aren't updating ourself
            if (router != this.routerConfigLocation) {
                byte[] sendData = new byte[4];
                // The router the update is coming from is the first byte in the packet
                sendData[0] = (byte) this.routerConfigLocation;

                // Distance to X, Y, Z is the second, third, and fourth byte respectively
                for (int location = 1; location <= this.configsArray[router].length; location++) {
                    sendData[location] = (byte) this.configsArray[this.routerConfigLocation][location - 1];
                }

                // Create the packet to send using the data just created
                DatagramPacket updatePkt = new DatagramPacket(
                    sendData,
                    sendData.length,
                    this.ip,
                    this.ports[router]
                );

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

        // TODO: Parse out the array sent and update the distance vector
        try {
            this.socket.setSoTimeout(2000);
            this.socket.receive(rcvPkt);
            byte[] data = rcvPkt.getData();
            for (int i = 0; i < data.length; i++) {
                System.out.println((int) data[i]);
            }
            int sendingRouter = (int) rcvPkt.getData()[0];
            System.out.println("Received a packet from " + sendingRouter);
        }
        catch(Exception e) {
            // if there is a timeout, just move on with life
            System.out.println("Timeout");
        }
        this.socket.close();

        //TODO: Return the array for the new distance vector
    }

    /**
        Prints the distance vector for the current router
    */
    public void printDistanceVector() {
        String printString = "";
        for (int num: this.configsArray[this.routerConfigLocation]) {
            printString += num + ", ";
        }
        // Remove the last ', ' from the string
        printString = cutOffEnd(printString, 2);
        System.out.printf("<%s>", printString);
    }

    public static String cutOffEnd(String s, int n) {
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
