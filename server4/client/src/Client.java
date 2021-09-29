import types.Coordinates;
import types.FormOfEducation;
import types.Semester;

import javax.activation.CommandInfo;
import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class Client{
    private Socket socket = null;
    //private PrintWriter pr;
    BufferedReader in;
    PrintWriter out;
    BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

    CommandInfoZ output;

    ObjectOutputStream outobj;
    ObjectInputStream inobj;

    public Client(){
    }

    public void connection(String address, int port) throws IOException {
        try {
            socket = new Socket(address, port);
        }
        catch (UnknownHostException e)
        {
            System.out.println("unknown host [CLIENT CONNECTION]");
            System.exit(0);
        }
        catch (IOException e)
        {
            System.out.println("Сервер Недоступен (io exception [CLIENT CONNECTION])");
            System.exit(0);
        }

        //this.pr = new PrintWriter(socket.getOutputStream());//чё, почему это например не работает
        //this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        //this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true); //Вот это ваще зачем такая длинная конструкция?
        this.outobj = new ObjectOutputStream(socket.getOutputStream());
        this.inobj = new ObjectInputStream(socket.getInputStream());
    }


    public void writeCommand(String text) throws IOException, ClassNotFoundException {
        if (text.equals("help")) {
                output = new CommandInfoZ(1);
            } else if (text.equals("info")) {
                output = new CommandInfoZ(2);
            } else if (text.equals("show")) {
                output = new CommandInfoZ(3);
            } else if (text.equals("insert")) {
                output = new CommandInfoZ(4, AddMethods.addName(), AddMethods.addCoordinates(), LocalDate.now(),AddMethods.addStudentCount(), AddMethods.addEducation(), AddMethods.addSemester());
            } else if (text.equals("update")) {
                output = new CommandInfoZ(5, AddMethods.addId(),  AddMethods.addName(), AddMethods.addCoordinates(), LocalDate.now(),AddMethods.addStudentCount(), AddMethods.addEducation(), AddMethods.addSemester());
            } else if (text.equals("remove")) {
                output = new CommandInfoZ(6, AddMethods.addId());
            } else if (text.equals("clear")) {
                output = new CommandInfoZ(7);
            }/* else if (text.equals("save")) {
              output = new Command(8);
            }*/ else if (text.equals("execute_script")) {
                executeScript(AddMethods.addScriptName());
                output = new CommandInfoZ(9);
            } else if (text.equals("exit")) {
                output = new CommandInfoZ(10);
            } else if (text.equals("remove_lower")) {
                output = new CommandInfoZ(11, AddMethods.addId());
            } else if (text.equals("history")) {
                output = new CommandInfoZ(12);
            } else if (text.equals("filter_by_semester_enum")) {
                output = new CommandInfoZ(13, AddMethods.addSemester());
            } else if (text.equals("filter_starts_with_name")) {
                output = new CommandInfoZ(14, AddMethods.addName());
            }else{
                output = new CommandInfoZ(0);
            }

            if (output.commNumber==0){
                System.out.println("Нет такой команды");
            }else if (output.commNumber==10){
                System.out.println("Закрытие Клиента");
                System.exit(0);
            }else {
                //System.out.println(output);
                try {
                    outobj.writeObject(output);
                }catch (SocketException e){
                    System.out.println("Пропала связь с сервером");
                    System.exit(0);
                }
                if (output.commNumber==1 | output.commNumber==2 | output.commNumber==3 | output.commNumber==4 | output.commNumber==5 | output.commNumber==6 | output.commNumber==7 | output.commNumber==11 | output.commNumber==12 | output.commNumber==13 | output.commNumber==14){
                    System.out.println((String) inobj.readObject());
                }

        }
    }

    public void executeScript(String text) throws IOException, ClassNotFoundException {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(text));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String str;
            ArrayList<String> list = new ArrayList<>();
            while((str = reader.readLine()) != null ){
                if(!str.isEmpty()){
                    list.add(str);
                    System.out.println(str);
                }
            }
            System.out.println(list);
            String[] commands = list.toArray(new String[0]);

            for(String command : commands){
                //System.out.println(command);
                writeCommand(command);
            }
            System.out.println("Скрипт завершён");
        }catch (FileNotFoundException e){
            System.out.println("Файл с таким именем не найден");
        }
    }
}


