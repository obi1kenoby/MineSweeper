package project.models;

import javax.swing.*;
import java.io.Serializable;


/**
 * @author Alexandr Naumov on 20.10.2017.
 * @version 1.0.0
 */

public class Cell implements Serializable{

    private static final long serialVersionUID = -266141377718622095L;
    private int x;
    private int y;
    private Status status;
    private ImageIcon icon;
    private boolean main;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Cell(){}

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isMain() {
        return main;
    }

    public void setMain(boolean main) {
        this.main = main;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public void setIcon(ImageIcon icon) {
        this.icon = icon;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        if (x != cell.x) return false;
        if (y != cell.y) return false;
        return status == cell.status;
    }

    @Override
    public int hashCode(){
        int hashCode = 31;
        hashCode = hashCode * 17 + x;
        hashCode = hashCode * 17 + y;
        hashCode = hashCode * 17 + ((status == null) ? 0 : status.toString().hashCode());
        return hashCode;
    }
    
    @Override
    public String toString(){
        return "Cell: [ x:" + x + " y:" + y +
                " status: " + status + " ]";
    }
}
