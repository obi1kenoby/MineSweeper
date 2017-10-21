package project.models;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author Alexander Naumov on 20.10.2017.
 * @version 1.0.0
 */

public class Model {

    private int n;
    private int m;
    private final HashSet<Cell> cells;
    private final Random random;

    public Model(int n) {
        this.n = n;
        switch (n) {
            case 9:
                m = 10;
                break;
            case 16:
                m = 40;
                break;
        }
        random = new Random();
        cells = new HashSet();
        createMines();
    }

    private void createMines() {
        for (int i = 0; i < m; i++) {
            int x = random.nextInt(n)+1;
            int y = 0;
            if(cells.size() > 0){
                List<Integer> rangeY = ys(x);
                y = rangeY.get(random.nextInt(rangeY.size()));
            }
            else{
                y = random.nextInt(n)+1;
            }
            Cell cell = new Cell(x,y);
            cell.setStatus(Status.MINE);
            cells.add(cell);
        }
    }
    
    private List<Integer> ys(int x){
        List<Integer> list = new ArrayList();
        for (int i = 1; i <= n; i++) {
            list.add(i);
        }
        cells.stream().filter((cell) -> (cell.getX() == x && list.contains(cell.getY()))).forEachOrdered((cell) -> {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == cell.getY()) {
                    list.remove(i);  
                }
            }
        });   
        return list;
    }

    public Set<Cell> getCells() {
        return cells;
    }
}
