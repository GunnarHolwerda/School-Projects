import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;
import java.util.ArrayList;

public class Receiver {
    private int windowSize, maxSeqNum, portNum;
    private Data[] data;
    private ArrayList<Integer> pktToDrop;

    public Receiver(int windowSize, int maxSeqNum, ArrayList<Integer> pktToDrop) {
        this.windowSize = windowSize;
        this.maxSeqNum = maxSeqNum;
        this.pktToDrop = pktToDrop;
        this.portNum = 9877;

        this.data = generateDataToBeSent();
    }

    public void startReceiver() throws Exception {
        DatagramSocket receiverSocket = new DatagramSocket(this.portNum);
        int value = 0;

        while(!dataAcknowledged()) {
            // Set up to receive data
            byte[] rcvData = new byte[1];
            DatagramPacket rcvPkt = new DatagramPacket(rcvData, rcvData.length);
            receiverSocket.receive(rcvPkt);

            // Get the port, IP, and data of the sender packet
            InetAddress senderIP = rcvPkt.getAddress();
            int port = rcvPkt.getPort();
            value = (int) rcvPkt.getData()[0];

            if (this.pktToDrop.indexOf((Integer) value) != -1) {
                // The packet we received is a packet to drop
                // remove it from the list because it has been dropped and
                // then skip the rest of the loop
                this.pktToDrop.remove((Integer) value);
                continue;
            }

            // We have received packet set acknowledged to true
            this.data[value].acknowledged = true;
            printPacketInfo(value, this.data, false);

            // Sleep to simulate delay
            Thread.sleep(1000);

            // Create and send the acknowledgement packet
            byte[] ackData = new byte[1];
            ackData[0] = (byte) value;
            DatagramPacket ackPkt = new DatagramPacket(ackData, ackData.length, senderIP, port);
            receiverSocket.send(ackPkt);
            printPacketInfo(value, this.data, true);
        }
        receiverSocket.close();
    }

    /**
        Generates an array of Data objects sequenced from 0 to maxSeqNum

        @return: Data[]
    */
    private Data[] generateDataToBeSent() {
        Data[] data = new Data[this.maxSeqNum];
        for (int i = 0; i < this.maxSeqNum; i++) {
            data[i] = new Data(i);
        }

        return data;
    }

    // Private class to represent data
    private class Data {
        boolean acknowledged, sent;
        int value;

        public Data(int value) {
            this.acknowledged = false;
            this.value = value;
        }
    }

    /**
        Determines whether all data has been acknowledged

        @return: true if all data is acknowledged, else false
    */
    private boolean dataAcknowledged() {
        for (Data d: this.data) {
            if (!d.acknowledged) {
                return false;
            }
        }

        return true;
    }

    /**
        Prints a message for sending the packet that looks like:
        "Packet _ is recieved, window [0, 1, 2, 3*]" where an asterisk specifies
        acknowledged packet.

        @param: pktNum, the packet number we are sending
        @param: window, a Data[] for the current window
        @param: isAck, boolean to determine if we are printing Ack or Pkt
    */
    private void printPacketInfo(int pktNum, Data[] window, boolean isAck) {
        String startStr = "%s %d %s, window [";
        //Create Ack message or pkt message depending on isAck boolean
        if (isAck) {
            startStr = String.format(startStr, "Ack", pktNum, "sent");
        }
        else {
            startStr = String.format(startStr, "Pkt", pktNum, "received");
        }


        System.out.print(startStr);
        for (int i = 0; i < window.length; i++) {
            // If packet acknowledged add * to print
            String curWinStr = !window[i].acknowledged ? "%d*" : "%d";

            // Check if we have a window value that is past maxSeqNum
            // We will print a _ in this case, the value is signified by -1
            if (window[i].value == -1) {
                curWinStr = "%s";
            }

            System.out.printf(curWinStr, (window[i].value != -1) ? window[i].value : "_");

            // If we aren't the final print add a comma
            if (i < window.length - 1) {
                System.out.printf(", ");
            }
        }

        System.out.printf("]\n");
    }

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        // Get the window size
        System.out.print("Enter the window's size on the sender: ");
        int winSize = in.nextInt();
        in.nextLine();

        // Get the sequence number
        System.out.print("Enter the maximum sequence number on the sender: ");
        int maxSeqNum = in.nextInt();
        in.nextLine();

        System.out.print("Enter the packet(s) that will be dropped (csv format for multiple): ");
        String packets = in.nextLine();

        //Parse the packets into an ArrayList by comma
        String[] packetArray = packets.split(",");
        ArrayList<Integer> pktToDrop = new ArrayList<Integer>();
        for (String pkt: packetArray) {
            pktToDrop.add(Integer.parseInt(pkt));
        }

        Receiver receiver = new Receiver(winSize, maxSeqNum, pktToDrop);
        receiver.startReceiver();
    }

}
