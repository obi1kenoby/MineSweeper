package project.models;

/**
 * @author Alexander Naumov on 22.10.2017
 * @version 1.0.0
 */

public class Number extends Cell{
    
    private int value = 0;

    public Number(int x, int y) {
        super(x, y);
        setStatus(Status.NUMBER);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + value; 
    }
}
