package model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import project.models.Cell;

/**
 * @author Alexandr Naumov on 20.10.2017.
 * @version 1.0.0
 */

public class CellTest {
    
    private Cell cellA;
    private Cell cellB;
    private Cell cellC;
    
    @Before
    public void before(){
        cellA = new Cell(1,2);
        cellB = new Cell(1,4);
        cellC = new Cell(2,2);
    }
    
    @Test
    public void equalsTest1(){
        assertFalse(cellA.equals(cellB));
    }
    
    @Test
    public void equalsTest2(){
        assertTrue(cellB.equals(cellB));
    }
    
    @Test
    public void hashCodeTes1(){
        int hashCode = cellA.hashCode();
        if (hashCode != cellA.hashCode()) {
            fail("test is failed.");
        }
    }
    
    @Test
    public void hashCodeTest2(){
        int hashCode = cellB.hashCode();
        for (int i = 0; i < 5; i++) {
            if (hashCode != cellB.hashCode()) {
                fail("test is failed.");
            }
        }
    }
}
