import types.*;

import java.io.Serializable;
import java.time.LocalDate;

public class CommandInfoZ implements Serializable {
    int commNumber;

    int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    String name; //Поле не может быть null, Строка не может быть пустой
    Coordinates coordinates; //Поле не может быть null
    LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    long studentsCount; //Значение поля должно быть больше 0
    FormOfEducation formOfEducation; //Поле не может быть null
    Semester semesterEnum; //Поле не может быть null

    public CommandInfoZ(int commNumber) {
        this.commNumber = commNumber;
    }

    public CommandInfoZ(int commNumber, int id) {
        this.commNumber = commNumber;
        this.id=id;
    }

    public CommandInfoZ(int commNumber, Semester semesterEnum){
        this.commNumber=commNumber;
        this.semesterEnum=semesterEnum;
    }

    public CommandInfoZ(int commNumber, String name){
        this.commNumber=commNumber;
        this.name=name;
    }

    public CommandInfoZ(int commNumber, String name, Coordinates coordinates, LocalDate creationDate, int studentsCount,
                        FormOfEducation formOfEducation, Semester semesterEnum){
        this.commNumber=commNumber;
        this.name=name;
        this.coordinates=coordinates;
        this.creationDate=creationDate;
        this.studentsCount=studentsCount;
        this.formOfEducation=formOfEducation;
        this.semesterEnum=semesterEnum;
    }

    public CommandInfoZ(int commNumber,int id, String name, Coordinates coordinates, LocalDate creationDate, int studentsCount,
                        FormOfEducation formOfEducation, Semester semesterEnum) {

        this.commNumber=commNumber;
        this.id=id;
        this.name=name;
        this.coordinates=coordinates;
        this.creationDate=creationDate;
        this.studentsCount=studentsCount;
        this.formOfEducation=formOfEducation;
        this.semesterEnum=semesterEnum;
    }

    @Override
    public String toString() {
        return "Command{"+
                "commNumber(" + commNumber + ") "+
                "id(" + id +") "+
                "name(" +name+") "+
                "coordinates("+coordinates+") "+
                "creation time("+creationDate+") "+
                "students count("+studentsCount+") "+
                "form of education("+formOfEducation+") "+
                "semester("+semesterEnum+") "+
                "}";
    }
}
