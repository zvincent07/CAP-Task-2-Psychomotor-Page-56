import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(); // No need to bind to a port

            String message = "Hello, UDP Server!";
            byte[] buffer = message.getBytes();

            InetAddress serverAddress = InetAddress.getByName("localhost"); // Use server IP if remote
            int serverPort = 1234;

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, serverPort);
            socket.send(packet);

            System.out.println("Message sent to server.");

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}