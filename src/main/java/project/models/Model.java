package project.models;

import project.level.Level;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Alexander Naumov on 20.10.2017.
 * @version 1.0.0
 */

public class Model implements Serializable{

    private static final long serialVersionUID = 2491939809417725173L;
    private int height;
    private int width;
    private int X;
    private int Y;
    private int totalMines;
    private Set<Cell> cells = new HashSet<>();
    private final Random random;

    public Model(int inputX, int inputY, int height, int width, int totalMines, long seed){
        this.X = inputX;
        this.Y = inputY;
        this.height = height;
        this.width = width;
        this.totalMines = totalMines;
        random = new Random(seed);
    }

    public Model(Level level, int inputX, int inputY, long seed) {
        this.X = inputX;
        this.Y = inputY;
        switch (level) {
            case EASY:
                totalMines = 10;
                width = 9;
                height = 9;
                break;
            case MEDIUM:
                totalMines = 40;
                width = 16;
                height = 16;
                break;
            case HARD:
                totalMines = 99;
                width = 30;
                height = 16;
                break;
        }
        random = new Random(seed);
    }

    public void createNumbers(){
        Set<Cell> temp  = cells.stream().filter(cell -> !cell.getStatus().equals(Status.NUMBER)).collect(Collectors.toSet());
        for(Cell cell: temp){
            if (cells.contains(cell) && cell.getStatus().equals(Status.MINE)){
                addNumbers(cell.getX(), cell.getY());
            }
        }
    }

    private void addNumbers(int x, int y){
        if (x == 1) {
            initNumber(x +1, y);
            if (y == 1) {
                initNumber(x, y +1);
                initNumber(x + 1, y + 1);
            }
            else if (1 < y && y < height){
                initNumber(x, y - 1);
                initNumber(x + 1, y - 1);
                initNumber(x, y + 1);
                initNumber(x + 1, y + 1);
            }
            else{
                initNumber(x, y - 1);
                initNumber(x + 1, y - 1);
            }
        }else if (1 < x && x < width){
            initNumber(x + 1, y);
            initNumber(x - 1, y);
            if (y == 1) {
                initNumber(x - 1, y +1);
                initNumber(x, y + 1);
                initNumber(x + 1, y + 1);
            }
            else if (1 < y && y < height) {
                initNumber(x - 1, y - 1);
                initNumber(x, y - 1);
                initNumber(x + 1, y - 1);
                initNumber(x - 1, y + 1);
                initNumber(x, y + 1);
                initNumber(x + 1, y + 1);
            }
            else{
                initNumber(x - 1, y - 1);
                initNumber(x, y - 1);
                initNumber(x + 1, y - 1);
            }
        }else{
            initNumber(x - 1, y);
            if (y == 1) {
                initNumber(x - 1, y + 1);
                initNumber(x, y + 1);
            }
            else if (1 < y && y < height) {
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
            number.setValue(1);
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

    public void crateEmpties(){
        for (int x = 1; x <= width; x++) {
            for (int y = 1; y <= height; y++) {
                boolean noExist = false;
                for(Cell cell: cells){
                    if (cell.getX() == x && cell.getY() == y) {
                        noExist = true;
                    }
                }
                if (!noExist) {
                    Cell empty = new Cell(x,y);
                    empty.setStatus(Status.EMPTY);
                    cells.add(empty);
                }
            }
        }
    }

    public void initialCell() {
        Cell main = new Cell(X, Y);
        main.setStatus(Status.EMPTY);
        main.setMain(true);
        cells.add(main);
        Cell[] radiusCell = new Cell[8];
        if (X == 1) {
            radiusCell[0] = new Cell(X + 1, Y);
            if (Y == 1) {
                radiusCell[1] = new Cell(X + 1, Y + 1);
                radiusCell[2] = new Cell(X, Y + 1);
            } else {
                radiusCell[1] = new Cell(X + 1, Y - 1);
                radiusCell[2] = new Cell(X, Y - 1);
                if (1 < Y && Y < height) {
                    radiusCell[3] = new Cell(X + 1, Y + 1);
                    radiusCell[4] = new Cell(X, Y + 1);
                    radiusCell[5] = new Cell(X, Y - 1);
                }
            }
        } else if (1 < X && X < width) {
            radiusCell[0] = new Cell(X + 1, Y);
            radiusCell[1] = new Cell(X - 1, Y);
            if (Y == 1) {
                radiusCell[2] = new Cell(X + 1, Y + 1);
                radiusCell[3] = new Cell(X, Y + 1);//
                radiusCell[4] = new Cell(X - 1, Y + 1);
            } else {
                radiusCell[2] = new Cell(X - 1, Y - 1);
                radiusCell[3] = new Cell(X, Y - 1);
                radiusCell[4] = new Cell(X + 1, Y - 1);
                if (1 < Y && Y < height) {
                    radiusCell[5] = new Cell(X + 1, Y + 1);
                    radiusCell[6] = new Cell(X, Y + 1);
                    radiusCell[7] = new Cell(X - 1, Y + 1);
                }
            }
        } else {
            radiusCell[0] = new Cell(X - 1, Y);
            if (Y == 1) {
                radiusCell[1] = new Cell(X, Y + 1);
                radiusCell[2] = new Cell(X - 1, Y + 1);
            } else {
                radiusCell[1] = new Cell(X - 1, Y - 1);
                radiusCell[2] = new Cell(X, Y - 1);
                if (1 < Y && Y < height) {
                    radiusCell[3] = new Cell(X, Y + 1);
                    radiusCell[4] = new Cell(X - 1, Y + 1);
                }
            }
        }
        for (Cell cell : radiusCell) {
            if (cell != null) {
                cell.setStatus(Status.EMPTY);
                cell.setMain(true);
                cells.add(cell);
            }
        }
    }

    public void removeInitialCell() {
        cells.removeIf(Cell::isMain);
    }

    public void createMines() {
        int mineCounter = 0;
        while (mineCounter < totalMines) {
            int x = randomX();
            int y = randomY(x);
            Cell mine = new Cell(x, y);
            mine.setStatus(Status.MINE);
            cells.add(mine);
            mineCounter++;
        }
    }

    //todo
    private int randomX() {
        List<Integer> freeX = new ArrayList<>();
        for (int x = 1; x <= width; x++) {
            int counter = 0;
            for (Cell cell : cells) {
                if (x == cell.getX()) counter++;
            }
            if (counter < height) freeX.add(x);
        }
        return freeX.get(random.nextInt(freeX.size()));
    }

    private int randomY(int x) {
        List<Integer> freeY = new ArrayList<>();
        for (int y = 1; y <= height; y++) {
            freeY.add(y);
        }
        List<Integer> busyY = new ArrayList<>();
        for (Cell cell : cells) {
            if (cell.getX() == x) busyY.add(cell.getY());
        }
        freeY.removeAll(busyY);
        return freeY.get(random.nextInt(freeY.size()));
    }

    public Set<Cell> getCells() {
        return cells;
    }
}