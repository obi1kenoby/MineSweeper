package model;

import java.util.Iterator;
import java.util.Set;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import project.models.Cell;
import project.models.Model;


/**
 * @author Alexandr Naumov on 21.10.2017
 * @version 1.0.0
 */

public class ModelTest {
    
    private Model model;
    
    @Before
    public void before(){
        model = new Model(9);
    }
    
    @Test
    public void minesTest1(){
        Set<Cell> mines = model.getCells();
        System.out.println(mines.size());
        Iterator iterator = mines.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
    }

    @Test
    public void minesTest2(){
        for (int i = 0; i < 1000; i++) {
            Model model = new Model(16);
            assertEquals(model.getCells().size(), 40);
        }
    }
    
    @Test
    public void numbersTest1(){
        Set<Cell> cells = model.getCells();
        Iterator iterator = cells.iterator();
        while(iterator.hasNext()){
            if (iterator.next() instanceof Number) {
                Number number = (Number) iterator.next();
                System.out.println(number);
            }
        }
    }
}
