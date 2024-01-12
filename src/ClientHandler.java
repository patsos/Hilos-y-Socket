import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;

    //configuration of different possible responses. allows for unique interactions
    private final String[] compliment = {"amazing!", "nice!", "kind!", "gorgeous!",
            "cheerful!", "cool!", "likeable!"};

    private final String[] joke = {"How does Moses make tea? He brews!!!",
            "What do you call a fake noodle? An impasta!!!",
            "Why did the frog take the bus to work today? His car got toad away!!"};

    private final String[] fact = {
            "Honey will never spoil!",
            "Lemons float, but limes sink!",
            "Japan has one vending machine for every 40 people!",
            "Bananas are a type of berry, but Strawberries are not!",
            "The circulatory system is more than 60,000 miles long!",
            "Scotland has 421 words for snow!" };

    private final String[] goodbye = {"Goodbye, Have a great day :)",
            "Farewell! Take care!", "See you later!",
            "It was lovely chatting with you!"};

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            //this sets up the input and the output streams
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            Scanner in = new Scanner(clientSocket.getInputStream());

            //this portion sends out the possible options of the chatbot
            out.println("Choose an option:");
            out.println("1. Compliment Giver");
            out.println("2. Date Teller");
            out.println("3. Joke Teller");
            out.println("4. Greeting");
            out.println("5. Goodbye");
            //inserted here to indicate the termination of the options, will then proceed to listen to client repsonse
            out.println();
            //this portion made to listen to clients response
            while (in.hasNextLine()) {
                String clientResponse = in.nextLine();
                System.out.println("Client's response: " + clientResponse);

                //this processes the client's response
                String serverResponse = processClientResponse(clientResponse);
                //sends the servers response based on what the client chose
                out.println(serverResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //executes the correct behaviour based on what the client chose
    private String processClientResponse(String clientResponse) {
        Random random = new Random();
        switch (clientResponse) {
            case "1": //gives compliments to the client
                return "You chose Compliment Giver: You are " + compliment[random.nextInt(compliment.length)];
            case "2": //tells the client the current date
                return "You chose Date Teller: The current date is " + java.time.LocalDate.now();
            case "3": //tells the client a joke
                return "You chose Joke Teller: " + joke[random.nextInt(joke.length)];
            case "4": //tells the client an interesting fact
                return "Interesting Fact: " + fact[random.nextInt(fact.length)];
            case "5": //says goodbye to the client if they want to stop chatting
                return "You chose Goodbye: " + goodbye[random.nextInt(goodbye.length)];
            default://let's client know they entered an invalid option
                return "Invalid option. Please choose again.";
        }
    }
}
