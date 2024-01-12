import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public static void main(String[] args) {
        try {
            //this sets the address to be paired up with the server
            ServerSocket serverSocket = new ServerSocket(11101);
            System.out.println("The server is running. Waiting for new connections...");

            //while loop creates an infinite loop that will have the server continuously listening to the client
            while(true) {
                //this lets the server know that there is a connection established with a client
                Socket clientSocket = serverSocket.accept();
                System.out.println("A new client has connected.");

                // this will create a thread to handle the new client
                ClientHandler clientHandler = new ClientHandler(clientSocket);
                new Thread(clientHandler).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
