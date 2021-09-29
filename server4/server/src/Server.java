import Collection.CollectionManager;
import Collection.StudyGroup;
import types.Commands;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Server {

    private Socket socket = null;
    private ServerSocket serverSocket = null;
    int port;
    BufferedReader in;
    BufferedWriter out;

    CollectionManager collection = new CollectionManager();

    ObjectOutputStream outobj;
    ObjectInputStream inobj;

    private final List<String> history = new LinkedList<>();

    Scanner scanner = new Scanner(System.in);

    public Server() {
    }

    public void start(int port) throws IOException {
        this.port = port;
        serverSocket = new ServerSocket(port);
        System.out.println("Сервер запущен");
    }

    public void acceptConnection() throws IOException {
        socket = serverSocket.accept();
        System.out.println("Клиент присоединился");
        this.outobj = new ObjectOutputStream(socket.getOutputStream());
        this.inobj = new ObjectInputStream(socket.getInputStream());
    }

    public void readAndSendBack() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        String text = "";

        while (text.equals("over") == false) {
            text = in.readLine();
            System.out.println("client: " + text);
            out.write("Пидорас написал \""+text+"\" \n");
            out.flush();
        }
    }




    public void readCommand() throws IOException, ClassNotFoundException {

        boolean check=true;
        while (check) {

            try {
                CommandInfoZ pog = (CommandInfoZ) inobj.readObject();
                System.out.println(pog);

                if (pog.commNumber==4){
                    StudyGroup p = new StudyGroup(collection.GetMaxKey()+1,pog.name,pog.coordinates,pog.creationDate, pog.studentsCount,pog.formOfEducation,pog.semesterEnum);
                    collection.insert(p);
                    outobj.writeObject("Студент Добавлен");

                }else if(pog.commNumber==5){
                    if (collection.get(pog.id) == null) {
                        outobj.writeObject("Элемент с заданным ключём отстутствует");
                    } else {
                        collection.remove(pog.id);
                        StudyGroup p = new StudyGroup(pog.id,pog.name,pog.coordinates,pog.creationDate, pog.studentsCount,pog.formOfEducation,pog.semesterEnum);
                        collection.insert(p);
                        outobj.writeObject("Объект был обновлён");
                    }

                }else if(pog.commNumber==3){
                    outobj.writeObject(collection.getPersonsA().toString());

                }else if(pog.commNumber==6){
                    if (collection.get(pog.id) == null) {
                        outobj.writeObject("Элемент с заданным ключём отстутствует");
                    } else {
                        collection.remove(pog.id);
                        outobj.writeObject("Объект был удалён");
                    }

                }else if(pog.commNumber==1){
                    outobj.writeObject(collection.description);

                }else if(pog.commNumber==2){
                    outobj.writeObject( "Тип коллекции: " + collection.getClass()+"\n" +
                                        "Дата инициализации: " + collection.getInitializationDate()+"\n" +
                                        "Количество элементов: " + collection.getSize()+"\n");

                }else if(pog.commNumber==7){
                    collection.clear();
                    outobj.writeObject("Коллекция была очищена");

                }else if(pog.commNumber==12){
                    outobj.writeObject(history.toString());

                }else if(pog.commNumber==11){
                    if (collection.get(pog.id) == null) {
                        outobj.writeObject("Элемент с заданным ключём отстутствует.");
                    } else {
                        collection.removeLower(pog.id);
                        outobj.writeObject("Элементы убраны");
                    }
                }else if(pog.commNumber==13){
                    String list = collection.FilterSemester(pog.semesterEnum);
                    if (list==""){
                        outobj.writeObject("Студентов с таким семестром не найдено");
                    }else {
                        outobj.writeObject(list);
                    }
                }else if(pog.commNumber==14){
                    outobj.writeObject(collection.subStringSearcher(pog.name));
                }

                history.add(Commands.getCommand(pog.commNumber).toString());
                if (history.size()>14){
                    history.remove(0);
                }

            }catch (SocketException e){
                System.out.println("Потеряно соединение с клиентом");
                check=false;
            }
        }
    }

    public void saveCollection(){
        collection.save();
    }

    public void loadCollectionFromFile(String filename) throws JAXBException {
        collection.loadFromFile(filename);
    }

}
