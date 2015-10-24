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
        configs.put(this.routerId, new HashMap<String, String>());
        this.setUpConfigMap();
        this.socket = new DatagramSocket(this.getPortNumber());
        System.out.printf("Router %s is running on port %d\n", this.routerId, this.getPortNumber());
        System.out.printf("Distance vector on router %s is:\n", this.routerId);
        this.printDistanceVector();
    }

    public int getPortNumber() {
        return Integer.parseInt(this.configs.get(this.routerId).get("port"));
    }

    public void setUpConfigMap() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader(CONFIG_FILE_NAME));
        String line;
        String[] setting = {"port", "X", "Y", "Z"};
        int settingPosition = 0;
        while ((line = in.readLine()) != null) {
            String[] array = line.split("\t");
            String myValue = array[routerId.charAt(0) - (int)'X'];
            this.configs.get(this.routerId).put(setting[settingPosition], myValue);
            settingPosition++;
        }
    }

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

    }
}
