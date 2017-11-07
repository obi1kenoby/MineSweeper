package project.models;

import java.util.*;

/**
 * @author Alexander Naumov on 20.10.2017.
 * @version 1.0.0
 */

public final class Model {

    private int height;
    private int width;
    private int X;
    private int Y;
    private int totalMines;
    private Set<Cell> cells = new HashSet<>();
    private final Random random;

    public Model(int n, int inputX, int inputY) {
        this.X = inputX;
        this.Y = inputY;
        switch (n) {
            case 9:
                totalMines = 10;
                width = n;
                height = n;
                break;
            case 16:
                totalMines = 40;
                width = n;
                height = n;
                break;
            default:
                totalMines = 99;
                width = 30;
                height = 16;
                break;
        }
        random = new Random();
    }

    public void createNumbers(){
        for(Cell cell: cells) {
            if (cell.getStatus() == Status.MINE) {
                int x = cell.getX();
                int y = cell.getY();

                if (x == 1) {
                    if (y == 1) {

                    } else if (1 < y && y < height) {

                    } else {

                    }
                } else if (1 < x && x < width) {
                    if (y == 1) {

                    } else if (1 < y && y < height) {

                    } else {

                    }
                } else {
                    if (y == 1) {

                    } else if (1 < y && y < height) {

                    } else {

                    }
                }
            }
        }
    }

    private void setNumber(int x, int y){
        //todo set number method
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

    public void removeInitialCell(){
        cells.removeIf(Cell::isMain);
    }

    public void createMines(){
        int mineCounter = 0;
        while (mineCounter < totalMines){
            int x = randomX();
            int y = randomY(x);
            Cell mine = new Cell(x, y);
            mine.setStatus(Status.MINE);
            cells.add(mine);
            mineCounter++;
        }
    }

    private int randomX(){
        List<Integer> freeX = new ArrayList<>();
        for (int x = 1; x <= width; x++) {
            int counter = 0;
            for(Cell cell: cells){
                if (x == cell.getX())counter++;
            }
            if (counter < width)freeX.add(x);
        }
        return freeX.get(random.nextInt(freeX.size()));
    }

    private int randomY(int x){
        List<Integer> freeY = new ArrayList<>();
        for (int y = 1; y <= height; y++) {
            freeY.add(y);
        }
        List<Integer> busyY = new ArrayList<>();
        for (Cell cell: cells){
            if (cell.getX() == x)busyY.add(cell.getY());
        }
        freeY.removeAll(busyY);
        return freeY.get(random.nextInt(freeY.size()));
    }

    public Set<Cell> getCells() {
        return cells;
    }
}