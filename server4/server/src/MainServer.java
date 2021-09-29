import javax.xml.bind.JAXBException;
import java.io.IOException;



public class MainServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException, JAXBException {
        Server server = new Server();
        server.start(5000);
        server.loadCollectionFromFile("Data.xml");
        while(true){
            System.out.println("Поиск подключения");
            server.acceptConnection();
            server.readCommand();
            server.saveCollection();
        }
        //server.readObject();
    }
}
