import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class Receiver {
    private int windowSize, maxSeqNum, portNum;

    public Receiver(int windowSize, int maxSeqNum) {
        this.windowSize = windowSize;
        this.maxSeqNum = maxSeqNum;
        this.portNum = 9877;
    }

    public void startReceiver() throws Exception {
        DatagramSocket receiverSocket = new DatagramSocket(this.portNum);
        int value = 0;

        // TODO: Implement window for this side
        while(value < 7) {
            byte[] rcvData = new byte[1];

            DatagramPacket rcvPkt = new DatagramPacket(rcvData, rcvData.length);

            receiverSocket.receive(rcvPkt);
            InetAddress senderIP = rcvPkt.getAddress();
            int port = rcvPkt.getPort();
            value = (int) rcvPkt.getData()[0];
            printPacketInfo(value, new int[8], false);
            Thread.sleep(5000);

            byte[] ackData = new byte[1];
            ackData[0] = (byte) value;

            DatagramPacket ackPkt = new DatagramPacket(ackData, ackData.length, senderIP, port);
            receiverSocket.send(ackPkt);
            printPacketInfo(value, new int[8], true);
        }
        receiverSocket.close();
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        // Get the window size
        System.out.print("Enter the window's size on the sender: ");
        int winSize = in.nextInt();

        // Get the sequence number
        System.out.print("Enter the maximum sequence number on the sender: ");
        int maxSeqNum = in.nextInt();

        // System.out.print("Enter the packet(s) that will be dropped (csv format for multiple): ");
        // String packets = in.nextLine();

        // Parse the packets into an ArrayList by comma
        // String[] packetArray = packets.split(",");
        // ArrayList<Integer> pktToDrop = new ArrayList<Integer>();
        // for (String pkt: packetArray) {
        //     pktToDrop.add(Integer.parseInt(pkt));
        // }

        //TODO: Figure out what to do with packets to drop

        Receiver receiver = new Receiver(winSize, maxSeqNum);
        receiver.startReceiver();
    }

    /**
        Prints a message for sending the packet that looks like:
        "Packet _ is recieved, window [0, 1, 2, 3*]" where an asterisk specifies
        acknowledged packet.

        @param: pktNum, the packet number we are sending
        @param: window, a Data[] for the current window
        @param: isAck, boolean to determine if we are printing Ack or Pkt
    */
    private static void printPacketInfo(int pktNum, int[] window, boolean isAck) {
        String startStr = "%s %d %s, window [";
        //Create Ack message or pkt message depending on isAck boolean
        if (isAck) {
            startStr = String.format(startStr, "Ack", pktNum, "sent");
        }
        else {
            startStr = String.format(startStr, "Pkt", pktNum, "received");
        }


        System.out.print(startStr);

        //TODO print an _ for the -1 value
        for (int i = 0; i < window.length; i++) {
            // If packet acknowledged add * to print
            //String curWinStr = !window[i] == 0 ? "%d*" : "%d";
            String curWinStr = "";
            System.out.printf(curWinStr, window[i]);

            // If we aren't the final print add a comma
            if (i < window.length - 1) {
                System.out.printf(", ");
            }
        }

        System.out.printf("]\n");
    }

}
