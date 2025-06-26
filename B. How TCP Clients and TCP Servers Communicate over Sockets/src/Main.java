import java.io.*;
import java.net.*;

public class Main {
    public static void main(String[] args) {
        // Start server in a separate thread
        new Thread(() -> {
            try {
                // Create server socket
                ServerSocket server = new ServerSocket(5000);
                System.out.println("Server started. Waiting for clients...");

                // Wait for client connection
                Socket channel = server.accept();
                System.out.println("Client connected");

                // Setup input and output streams
                PrintWriter out = new PrintWriter(channel.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(channel.getInputStream()));

                // Send message to client
                out.println("Hello! Nice to hear from you!");

                // Read response from client
                String clientResponse = in.readLine();
                System.out.println("Client says: " + clientResponse);

                // Close resources
                in.close();
                out.close();
                channel.close();
                server.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        // Give server time to start
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Client code
        try {
            Socket client = new Socket("localhost", 5000);

            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));

            // Send message to server  
            out.println("Hi there! Thanks for connecting!");

            // Read response from server
            String serverResponse = in.readLine();
            System.out.println("Server says: " + serverResponse);

            // Close resources
            in.close();
            out.close();
            client.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}