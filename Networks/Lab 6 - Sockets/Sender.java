import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.util.Scanner;
import java.util.ArrayList;
import java.net.UnknownHostException;

public class Sender {
    private int windowSize, maxSeqNum;
    private int sendPort = 9876;
    private int rcvPort = 9877;
    Data[] data;
    private InetAddress ip;

    public Sender(int windowSize, int maxSeqNum) {
        this.windowSize = windowSize;
        this.maxSeqNum = maxSeqNum;

        // Get this machine's IP address
        try {
            this.ip = InetAddress.getByName("localhost");
        }
        catch(UnknownHostException e) {
            System.out.println("Was unable to find localhost");
        }

        this.data = generateDataToBeSent();
    }

    public void startSender() throws Exception {

        // Create the socket to send data through
        DatagramSocket senderSocket = new DatagramSocket(this.sendPort);

        //Create loop that runs until all data has been acknowledged
        int winPosition = 0;
        while(!dataAcknowledged()) {
            // Get the current window
            Data[] win = determineWindow();

            byte[] sendData = new byte[1024];
            // Move through the data until all has been sent
            if (winPosition < win.length) {
                    sendData[0] = (byte) win[winPosition++].value;
            }

            DatagramPacket sendPkt = new DatagramPacket(sendData, sendData.length, this.ip, this.rcvPort);
            //TODO: Implement a timeout for the packets
            senderSocket.send(sendPkt);
            // The window position is the current packet being sent
            printPacketInfo(winPosition, win, false);

            byte[] ackData = new byte[1024];
            DatagramPacket ackPkt = new DatagramPacket(ackData, ackData.length);
            senderSocket.receive(ackPkt);
            int ackValue = (int) ackPkt.getData()[0];
            printPacketInfo(ackValue, win, true);
            if (checkPacketInWindow(win, ackValue)) {
                System.out.println("Did I find a packet?" + ackValue);
                // Acknowledge the packet
                this.data[ackValue].acknowledged = true;
                /*
                 Here I need to mark which packets in the window I have
                 acknowledged, and then check if I can move the window
                */
                // TODO make check for if whole window acknowledged if so,
                // set winPosition to 0
            }
        }

        senderSocket.close();
    }

    /**
        Creates the current window for the selective repeat protocol

        @return: a Data[] containing the window
    */
    private Data[] determineWindow() {
        Data[] window = new Data[this.windowSize];

        // Iterate over data until first not acknowledged packet
        int startPos = 0;
        for (startPos = 0; startPos < this.data.length; startPos++) {
            if (!this.data[startPos].acknowledged) {
                break;
            }
        }

        for (int i = startPos; i < window.length; i++) {
            window[i - startPos] = this.data[i];
        }

        return window;
    }

    /**
        Prints a message for sending the packet that looks like:
        "Packet _ is sent, window [0, 1, 2, 3*]" where an asterisk specifies
        acknowledged packet.

        @param: pktNum, the packet number we are sending
        @param: window, a Data[] for the current window
        @param: isAck, boolean to determine if we are printing Ack or Pkt
    */
    private void printPacketInfo(int pktNum, Data[] window, boolean isAck) {
        String startStr = "%s %d %s, window [";
        //Create Ack message or pkt message depending on isAck boolean
        if (isAck) {
            startStr = String.format(startStr, "Ack", pktNum, "received");
        }
        else {
            startStr = String.format(startStr, "Pkt", pktNum, "sent");
        }


        System.out.print(startStr);

        for (int i = 0; i < window.length; i++) {
            // If packet acknowledged add * to print
            String curWinStr = !window[i].acknowledged ? "%d*" : "%d";
            System.out.printf(curWinStr, window[i].value);

            // If we aren't the final print add a comma
            if (i < window.length - 1) {
                System.out.printf(", ");
            }
        }

        System.out.printf("]\n");
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
        Checks if the packet is in the current window

        @param: window, the current window
        @param: value, the value to check if exists in the window

        @return: true if packet is in the window, false otherwise
    */
    private boolean checkPacketInWindow(Data[] window, int value) {
        for (int i = 0; i < window.length; i++) {
            if (window[i].value == value) {
                return true;
            }
        }

        return false;
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
        boolean acknowledged;
        int value;

        public Data(int value) {
            this.acknowledged = false;
            this.value = value;
        }
    }


    // Main method
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

        Sender sender = new Sender(winSize, maxSeqNum);
        sender.startSender();
    }
}
