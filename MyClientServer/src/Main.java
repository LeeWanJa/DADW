import java.io.DataInput;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;

public class Main {
    public void start() throws IOException, UnknownHostException {
        DatagramSocket datagramSocket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("127.0.0.1");

        byte[] msg = new byte[100];

        DatagramPacket outPacket = new DatagramPacket(msg, 1, serverAddress, 7777);
        DatagramPacket inputPacket = new DatagramPacket(msg, msg.length);

        datagramSocket.send(outPacket);
        datagramSocket.receive(inputPacket);

        System.out.println("current server time : " + new String(inputPacket.getData()));

        datagramSocket.close();
    }

    public static void main(String[] args){
        try {
            new Main().start();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}