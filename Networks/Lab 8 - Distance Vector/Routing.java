import java.net.*;
import java.util.*;
import java.io.*;

class Routing {
    public static final String CONFIG_FILE_NAME = "configuration.txt";
    private int[][] configsArray = new int[3][3];
    private int[] ports = new int[3];

    private String routerId;
    private int routerConfigLocation;
    private DatagramSocket socket;

    public Routing(String routerId) throws Exception {
        this.routerId = routerId;
        this.routerConfigLocation = routerId.charAt(0) - (int)'X';
        // Initialize the hashmap for the current router
        this.setUpConfigMap();
        this.socket = new DatagramSocket(this.getMyPortNumber());
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
    public void start() {
        // while (true) {
        //     byte[] rcvData = new byte[1];
        //     DatagramPacket rcvPkt = new DatagramPacket(rcvData, rcvData.length);
        //     this.socket.receive(rcvPkt);
        //
        //     InetAddress senderIP = rcvPkt.getAddress();
        //     int port = rcvPkt.getPort();
        //     int value = (int) rcvPkt.getData()[0];
        // }
    }

    public void updateOtherNodes() {
        // for (HashMap<String, String> config: config) {
        //
        // }
        // DatagramPacket sendPkt = new DatagramPacket(sendData, sendData.length, this.ip, this.rcvPort);
        // this.socket.send(sendPkt);
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
