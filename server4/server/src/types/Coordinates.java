package types;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name = "coordinates")
@XmlAccessorType(XmlAccessType.FIELD)
public class Coordinates implements Serializable {
    private double x;
    private long y;
    public Coordinates(double x, long y){
        this.x = x;
        this.y = y;
    }
    Coordinates(){}
    public double getX() {
        return x;
    }

    public long getY() {
        return y;
    }

    @Override
    public String toString() {
        return "x=" + getX() +
                ", y=" + getY();
    }
}
