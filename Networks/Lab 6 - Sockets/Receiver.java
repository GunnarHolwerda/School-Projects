import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Receiver {

    public static void main(String[] args) throws Exception {
        DatagramSocket receiverSocket = new DatagramSocket(9877);
        int value = 0;
        while(value < 1) {
            byte[] rcvData = new byte[1024];

            DatagramPacket rcvPkt = new DatagramPacket(rcvData, rcvData.length);

            receiverSocket.receive(rcvPkt);
            InetAddress IPAddress = rcvPkt.getAddress();
            int port = rcvPkt.getPort();
            value = (int) rcvPkt.getData()[0];
            System.out.println(value);

            byte[] ackData = new byte[1024];
            ackData[0] = (byte) value;

            DatagramPacket ackPkt = new DatagramPacket(ackData, ackData.length, IPAddress, port);
            receiverSocket.send(ackPkt);
        }
        receiverSocket.close();
    }

}
