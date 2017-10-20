package project.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * @author Alexander Naumov on 20.10.2017.
 * @version 1.0.0
 */

public class Model {
    
    private int n;
    private int m;
    private ArrayList<Cell> cells;
    private final Random random;

    private Model(int n) {
        this.n = n;
        switch(n){
            case 9: m = 10;
            break;
            case 16: m = 40;
            break;
        } 
        random = new Random();
        cells = new ArrayList();
    }
    
    private void createMines(){
        for(int i = 0; i < m; i++) {
            int x = random.nextInt(n) + 1;
           // List<Integer> list = cells.stream().filter(cell -> cell.contain(x))
            //todo
        }
    }
}
