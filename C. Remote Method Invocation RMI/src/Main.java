import java.rmi.*;
import java.net.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

// Remote Interface
interface HelloInterface extends Remote {
    String display() throws RemoteException;

    void sendMessage(String message) throws RemoteException;
}

// Remote Implementation  
class HelloImpl extends UnicastRemoteObject implements HelloInterface {
    HelloImpl() throws RemoteException {
        super();
    }

    public String display() throws RemoteException {
        return "Hello from RMI Server!";
    }

    public void sendMessage(String message) throws RemoteException {
        System.out.println("Message received: " + message);
    }
}

// Server Program 
public class Main {
    public static void main(String[] args) {
        try {
            // Create and start RMI registry on port 1099
            LocateRegistry.createRegistry(1099);

            HelloImpl obj = new HelloImpl();
            Naming.rebind("rmi://localhost/Hello", obj);
            System.out.println("Server is ready");
        } catch (Exception e) {
            System.out.println("Server failed: " + e);
        }

        // Client code
        try {
            String host = "localhost";
            HelloInterface remoteObj = (HelloInterface) Naming.lookup("rmi://" + host + "/Hello");
            System.out.println(remoteObj.display());
            Thread.sleep(2000); // 2 second delay
            remoteObj.sendMessage("Hello from client!");
        } catch (RemoteException re) {
            re.printStackTrace();
        } catch (NotBoundException nbe) {
            nbe.printStackTrace();
        } catch (MalformedURLException mfe) {
            mfe.printStackTrace();
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}