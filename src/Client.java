import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client{
    public static void main(String[] args) {
        try {
            //this sets the address to be paired up with the server
            Socket socket = new Socket("localhost", 11101);

            //this sets up the input and output streams
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            //this allows the client to display the offered options from the server
            // additionally, the while loop goes until all the options are read out
            while (in.hasNextLine()) {

                String serverMessage = in.nextLine();

                //once the options stop being outputted this code runs, to let the client select an option
                if (serverMessage.isEmpty()) {
                    // this allows the client to choose an option
                    Scanner input = new Scanner(System.in);
                    String selection;

                    // this do-while will continue until the goodbye option is chosen, which will terminate the conversation
                    do {
                        System.out.print("Choose an option: ");
                        selection = input.nextLine();
                        out.println(selection);

                        // this will wait until the servers response
                        String serverResponse = in.nextLine();
                        System.out.println("Server's response: " + serverResponse);

                    }
                    while (!selection.equals("5"));
                } else { System.out.println(serverMessage);
              }
            }
        } catch (IOException e)
        { e.printStackTrace(); }
    }
}
