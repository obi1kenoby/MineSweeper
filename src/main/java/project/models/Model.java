package project.models;

import java.util.*;

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
        createNumbers();
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
    
    private void createNumbers(){
        //todo
        for (int x = 1; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                addNumbers(x,y);
            }
        }
    }

    public void addNumbers(int x, int y){
        if (x == 1) {
            initNumber(x +1, y);
            if (y == 1) {
                initNumber(x, y +1);
                initNumber(x + 1, y + 1);
            }
            else if (1 < y && y < n){
                initNumber(x, y - 1);
                initNumber(x + 1, y - 1);
                initNumber(x, y + 1);
                initNumber(x + 1, y + 1);
            }
            else{ //y == n
                initNumber(x, y - 1);
                initNumber(x + 1, y - 1);
            }
        }else if (1 < x && x < n){
            initNumber(x + 1, y);
            initNumber(x - 1, y);
            if (y == 1) {
                initNumber(x - 1, y +1);
                initNumber(x, y + 1);
                initNumber(x + 1, y + 1);
            }
            else if (1 < y && y < n) {
                initNumber(x - 1, y - 1);
                initNumber(x, y - 1);
                initNumber(x + 1, y - 1);
                initNumber(x - 1, y + 1);
                initNumber(x, y + 1);
                initNumber(x + 1, y + 1);
            }
            else{   //y == n
                initNumber(x - 1, y - 1);
                initNumber(x, y - 1);
                initNumber(x + 1, y - 1);
            }
        }else{
            initNumber(x - 1, y);
            if (y == 1) {
                initNumber(x - 1, y - 1);
                initNumber(x, y - 1);
            }
            else if (1 < y && y < n) {
                initNumber(x - 1, y - 1);
                initNumber(x, y - 1);
                initNumber(x - 1, y + 1);
                initNumber(x, y + 1);
            }
            else{
                initNumber(x - 1, y - 1);
                initNumber(x, y - 1);
            }
        }
    }
    
    private void initNumber(int x, int y){
        Cell temp = checkNumber(x, y);
        if (temp != null) {
            if (temp instanceof Number) {
                int value = ((Number)temp).getValue();
                ((Number)temp).setValue(value + 1);
            }
        }else{
            Number number = new Number(x, y);
            number.setStatus(Status.NUMBER);
            cells.add(number);
        }
    }

    private Cell checkNumber(int x, int y){
        for(Cell cell: cells){
            if(cell.getX() == x && cell.getY() == y){
                return cell;
            }
        }
        return null;
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