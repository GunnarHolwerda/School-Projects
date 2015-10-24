import java.net.InetAddress;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

class Routing {
    public static final String CONFIG_FILE_NAME = "configuration.txt";

    public static void main(String args[]) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(CONFIG_FILE_NAME));
        }
        catch (FileNotFoundException e) {
            System.out.println("Could not find configuration file");
        }
    }
}
