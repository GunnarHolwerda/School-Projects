import java.net.*;
import java.util.*;
import java.io.*;

class Routing {
    public static final String CONFIG_FILE_NAME = "configuration.txt";
    private HashMap<String, HashMap<String, String>> configs = new HashMap<String, HashMap<String, String>>();

    private String routerId;
    private DatagramSocket socket;

    public Routing(String routerId) throws Exception {
        this.routerId = routerId;
        // Initialize the hashmap for the current router
        configs.put(this.routerId, new HashMap<String, String>());
        this.setUpConfigMap();
        this.socket = new DatagramSocket(this.getPortNumber());
        System.out.printf("Router %s is running on port %d\n", this.routerId, this.getPortNumber());
        System.out.printf("Distance vector on router %s is:\n", this.routerId);
        this.printDistanceVector();
    }

    /**
        Returns the port number for the current router as an int

        @return int
    */
    public int getPortNumber() {
        return Integer.parseInt(this.configs.get(this.routerId).get("port"));
    }

    /**
        Initializes the configs Hashmap for the router
    */
    public void setUpConfigMap() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(CONFIG_FILE_NAME));
        String line;
        String[] setting = {"port", "X", "Y", "Z"};
        int settingPosition = 0;
        while ((line = in.readLine()) != null) {
            // Each string in the file is seperated by a tab
            String[] array = line.split("\t");
            // The position in the array that we want is the current Char - X
            String myValue = array[routerId.charAt(0) - (int)'X'];
            //TODO: Setup port number for other routers so that we can send them data
            this.configs.get(this.routerId).put(setting[settingPosition], myValue);
            settingPosition++;
        }
    }

    /**
        Starts the router waiting for updates and updates it's own tables
        and sends updates
    */
    public void start() {
        byte[] rcvData = new byte[1];
        DatagramPacket rcvPkt = new DatagramPacket(rcvData, rcvData.length);
        this.socket.receive(rcvPkt);

        InetAddress senderIP = rcvPkt.getAddress();
        int port = rcvPkt.getPort();
        int value = (int) rcvPkt.getData()[0];
    }

    /**
        Prints the distance vector for the current router
    */
    public void printDistanceVector() {
        Iterator it = configs.get(this.routerId).entrySet().iterator();
        String printString = "";
        while(it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (entry.getKey() != "port") {
                printString += entry.getValue() + ", ";
            }
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
