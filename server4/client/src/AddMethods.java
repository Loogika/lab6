import types.Coordinates;
import types.FormOfEducation;
import types.Semester;

import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

public class AddMethods {

    public static int addId(){
        Scanner scanner = new Scanner(System.in);
        int id = 0;
        int check;
        do{
            try {
                check = 0;
                System.out.print("Укажите id ");
                id = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Данные представлены в неправильном формате. Пожалуйста, повторите запрос ");
                check = -1;
            }
        }while (check != 0);
        return id;
    }

    public static int addStudentCount(){
        Scanner scanner = new Scanner(System.in);
        int studCnt = 0;
        int check;
        do{
            try {
                check = 0;
                System.out.print("Укажите количество студентов ");
                studCnt = Integer.parseInt(scanner.nextLine());
            }catch (NumberFormatException e){
                System.out.println("Данные представлены в неправильном формате. Пожалуйста, повторите запрос ");
                check = -1;
            }
        }while (check != 0);
        return studCnt;
    }

    public static String addName(){
        Scanner scanner = new Scanner(System.in);
        String name = null;
        int check = 0;
        while (check != 1){
            System.out.print("Введите имя ");
            name = scanner.nextLine();
            check = 0;
            if (name.replaceAll(" ", "").equals("")) {
                System.out.println("Именем Студента не может быть пустая строка, пожалуйста, введите имя еще раз");
            }else{
                check++;
            }
        }
        return name;
    }

    public static String addScriptName(){
        Scanner scanner = new Scanner(System.in);
        String name = null;
        int check = 0;
        while (check != 1){
            System.out.print("Введите имя ");
            name = scanner.nextLine();
            check = 0;
            if (name.replaceAll(" ", "").equals("")) {
                System.out.println("Названием скрипта не может быть пустая строка, пожалуйста, введите название еще раз");
            }else{
                check++;
            }
        }
        return name;
    }

    public static Coordinates addCoordinates(){
        int x=-1;
        int y=-1;
        Scanner scanner = new Scanner(System.in);
        int check;
        do {
            try {
                check = 0;
                System.out.print("Укажите искомый x ");
                x = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Данные представлены в неправильном формате. Пожалуйста, повторите запрос ");
                check = -1;
            }
        }while (check != 0);

        do {
            try {
                check = 0;
                System.out.print("Укажите искомый y ");
                y = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Данные представлены в неправильном формате. Пожалуйста, повторите запрос ");
                check = -1;
            }
        }while (check != 0);


        Coordinates coord =new Coordinates(x,y);
        return coord;
    }

    public static FormOfEducation addEducation(){
        Scanner scanner = new Scanner(System.in);
        String name = null;
        int check = 0;


        while (check != 1){
            System.out.print("Введите форму обучения {DISTANCE_EDUCATION,FULL_TIME_EDUCATION,EVENING_CLASSES} ");
            name = scanner.nextLine();
            check = 0;
            if ((name.replaceAll(" ", "").equals(""))) {
                System.out.println("Пустая строка");
            }else if(  (name.equals("DISTANCE_EDUCATION") ||  name.equals("FULL_TIME_EDUCATION") ||  name.equals("EVENING_CLASSES")) == false   ){
                System.out.println("Нет такой формы обучения");
            }else{
                check++;
            }
        }
        //(  name.equals("DISTANCE_EDUCATION") ||  name.equals("FULL_TIME_EDUCATION") ||  name.equals("DEVENING_CLASSES") == false   )
        FormOfEducation value =FormOfEducation.valueOf(name);
        return value;
    }

    public static Semester addSemester(){
        Scanner scanner = new Scanner(System.in);
        String name = null;
        int check = 0;


        while (check != 1){
            System.out.print("Введите семестр {THIRD, FIFTH, SIXTH, SEVENTH, EIGHTH} ");
            name = scanner.nextLine();
            check = 0;
            if ((name.replaceAll(" ", "").equals(""))) {
                System.out.println("Пустая строка");
            }else if(  (name.equals("THIRD") ||  name.equals("FIFTH") ||  name.equals("SIXTH") ||  name.equals("SEVENTH") ||  name.equals("EIGHTH"))    == false   ){
                System.out.println("Нет такого семестра");
            }else{
                check++;
            }
        }
        //(  name.equals("DISTANCE_EDUCATION") ||  name.equals("FULL_TIME_EDUCATION") ||  name.equals("DEVENING_CLASSES") == false   )
        //FormOfEducation value =FormOfEducation.valueOf(name);
        Semester value =Semester.valueOf(name);
        return value;
    }
}
