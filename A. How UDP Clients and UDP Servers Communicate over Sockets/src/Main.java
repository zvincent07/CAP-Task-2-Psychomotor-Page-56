import java.net.*;

public class Main {
    public static void main(String[] args) throws Exception {
        int port = 1234;
        DatagramSocket socket = new DatagramSocket(port);

        byte[] buffer = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

        socket.receive(packet);

        byte[] data = packet.getData();
        InetAddress remoteHost = packet.getAddress();
        int remotePort = packet.getPort();

        String message = new String(data, 0, packet.getLength());
        System.out.println("Received message: " + message);
        System.out.println("From: " + remoteHost + ":" + remotePort);

        socket.close();
    }
}