import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainClient {
    public static void main(String args[]) throws IOException, ClassNotFoundException {
        Client client = new Client();
        client.connection("127.0.0.1", 5000);

        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));


        String text = "";
        while (text.equals("exit") == false) {
            text = userInput.readLine();
            client.writeCommand(text);
        }



       // Emote poggers = new Emote("poggers",2);
       // client.sendThemObject(poggers);
       // poggers.getName();
    }
}