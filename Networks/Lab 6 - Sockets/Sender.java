import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.util.Scanner;
import java.net.UnknownHostException;

//TODO: Clean up code

public class Sender {
    private int windowSize, maxSeqNum;
    private int sendPort = 9876;
    private int rcvPort = 9877;
    Data[] data;
    private InetAddress ip;

    public Sender(int windowSize, int maxSeqNum) throws Exception {
        this.windowSize = windowSize;
        this.maxSeqNum = maxSeqNum;
        this.ip = InetAddress.getByName("localhost");
        this.data = generateDataToBeSent();
    }

    /**
        Starts the Selective repeat protocol
    */
    public void startSender() throws Exception {
        DatagramSocket senderSocket = new DatagramSocket(this.sendPort);

        // Get the current window
        int winPosition = 0;
        Data[] win = determineWindow();

        while(!dataAcknowledged()) {
            DatagramPacket sendPkt;
            while ((sendPkt = getPacketToSend(win)) != null) {
                senderSocket.send(sendPkt);
                printPacketInfo(sendPkt, win, false);
            }

            // Prepare to receive acknowledgement
            byte[] ackData = new byte[1];
            DatagramPacket ackPkt = new DatagramPacket(ackData, ackData.length);
            try {
                // Set a timeout on connection so that we don't need to thread
                // the receiving of the ACKs
                senderSocket.setSoTimeout(1500);
                senderSocket.receive(ackPkt);
            }
            catch (Exception e) {
                // If socket times out restart loop to check if packets have timedout
                continue;
            }

            // Get the value for the acknowledgement and acknowledge
            int ackValue = (int) ackPkt.getData()[0];
            this.data[ackValue].acknowledged = true;

            //Determine if the window needs to change after this acknowledgment
            Data[] newWin = determineWindow();
            if (windowHasChanged(win, newWin)) {
                win = newWin;
                winPosition = 0;
            }
            printPacketInfo(ackPkt, win, true);
        }
        senderSocket.close();
    }

    /**
        Determines and returns which packet needs to be sent next

        @param: win, the window for which we are sending packets for
        @return: byte[] the data for the packet to be sent
    */
    private DatagramPacket getPacketToSend(Data[] win) {
        int winPosition = 0;
        int sendValue = 0;
        byte[] sendData = new byte[1];

        // Check if any packet has timed out
        for (Data pkt: win) {
            if (pkt.sent && pkt.hasTimedOut() && pkt.value != -1 && !pkt.acknowledged) {
                sendData[0] = (byte) pkt.value;
                System.out.printf("Packet %d times out, resend packet %d\n", pkt.value, pkt.value);
                // Set the packet sent again to restart the timeout
                pkt.setSent(true);
                return new DatagramPacket(sendData, sendData.length, this.ip, this.rcvPort);
            }
        }

        // If no packet has timed out, then move through the window
        for (int i = winPosition; i < win.length; i++) {
            // If the packet we are going to send has already been sent,
            // don't send again, look for the next
            if (!win[i].sent && !win[i].acknowledged) {
                sendData[0] = (byte) win[winPosition].value;
                win[winPosition].setSent(true);

                return new DatagramPacket(sendData, sendData.length, this.ip, this.rcvPort);
            }
            else {
                winPosition++;
            }
        }

        return null;
    }

    /**
        Creates the current window for the selective repeat protocol

        @return: a Data[] containing the window
    */
    private Data[] determineWindow() {
        Data[] window = new Data[this.windowSize];

        // Iterate over data until first not acknowledged packet to find startPos
        int startPos = 0;
        for (startPos = 0; startPos < this.data.length; startPos++) {
            if (!this.data[startPos].acknowledged) {
                break;
            }
        }

        // Create window
        for (int i = 0; i < window.length; i++) {
            if (startPos + i < this.data.length) {
                window[i] = this.data[startPos + i];
            }
            else {
                // Fill the empty spots with dummy packets
                window[i] = new Data(-1);
                window[i].sent = true;
                window[i].acknowledged = true;
            }
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
    private void printPacketInfo(DatagramPacket pkt, Data[] window, boolean isAck) {
        String startStr = "%s %d %s, window [";
        int pktNum = (int)pkt.getData()[0];
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

    private boolean windowHasChanged(Data[] oldWin, Data[] newWin) {
        // A new window can be determined if the first element of the window is
        // different than the first element in the old window
        return oldWin[0] != newWin[0];
    }

    // Private class to represent data
    private class Data {
        boolean acknowledged, sent;
        int value;
        long timeSent;

        public Data(int value) {
            this.acknowledged = false;
            this.value = value;
        }

        public void setSent(boolean val) {
            this.sent = val;

            // Set the timeSent which will be used in the timeout
            this.timeSent = System.currentTimeMillis();
        }

        public boolean hasTimedOut() {
            // REturn if the current time is less than timeSent - 2 sec
            return !(System.currentTimeMillis() < this.timeSent + 5000);
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

        //Handling of dropping packets is on the receiver side

        Sender sender = new Sender(winSize, maxSeqNum);
        sender.startSender();
    }
}
