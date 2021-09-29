package Collection;

import javax.xml.bind.annotation.*;
import java.time.LocalDate;

import types.*;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
    public class StudyGroup {
        private long id;
        private String name;
        private Coordinates coordinates;
        @XmlElement(name = "creationDate")
        private String dateTimeString;
        @XmlTransient
        private LocalDate creationDate;
        private Long studentsCount;
        private Semester semesterEnum;
        //private Color groupColor;
        private FormOfEducation form;
       // private Location location;

        public StudyGroup(){}
        public StudyGroup(long id, String name, Coordinates coordinates, LocalDate creationDate, Long studentsCount, FormOfEducation form, Semester semester){
            this.id = id;
            this.name = name;
            dateTimeString=creationDate.toString();
            this.coordinates = coordinates;
            this.creationDate = creationDate;
            this.studentsCount = studentsCount;
            this.form = form;
            this.semesterEnum = semester;
            //this.groupColor = groupColor;
            //this.location = location;
        }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCreationDate() {
        return dateTimeString;
    }

    public Long getStudentsCount() {
        return studentsCount;
    }

    public Semester getSemester() {
        return semesterEnum;
    }

    // Color getGroupColor() {
    //    return groupColor;
    //}

    public FormOfEducation getForm() {
        return form;
    }

    //public Location getLocation() {
    //    return location;
    //}
/*
    @Override
    public String toString() {
        return "Person{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", coordinates=" + getCoordinates() +
                ", creationDate=" + getCreationDate() +
                ", height=" + getStudentsCount() +
                ", semester=" + getSemester() +
                ", formOfEducation=" + getForm() +
                '}' + '\n';
    }
*/
    @Override
    public String toString() {
        return "Person{"+
                "id(" + getId() +") "+
                "name(" +getName()+") "+
                "coordinates("+getCoordinates()+") "+
                "creation time("+getCreationDate()+") "+
                "form of education("+getForm()+") "+
                "semester("+getSemester()+") "+
                "}"+'\n';
    }
}
