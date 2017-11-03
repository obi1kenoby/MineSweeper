package project.models;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Alexander Naumov on 20.10.2017.
 * @version 1.0.0
 */

public final class Model {

    private int height;
    private int width;
    private int totalMines;
    private final Set<Cell> cells = new HashSet<>();
    private final Random random;
    private static Model model;

    private Model(int n, int inputX, int inputY) {
        if (n == 9) {
            totalMines = 10;
            width = n;
            height = n;
        } else if (n == 16) {
            totalMines = 40;
            width = n;
            height = n;
        } else {
            totalMines = 99;
            width = 30;
            height = 16;
        }
        random = new Random();
        Cell inputCell = new Cell(inputX, inputY);
        inputCell.setStatus(Status.EMPTY);
        cells.add(inputCell);
        createMines();
        cells.remove(inputCell);
        createNumbers();
        crateEmpties();
        for (Cell cell : cells) {
            if (cell.getStatus().equals(Status.MINE)) {
                System.out.print((cell.getX() + "-" + cell.getY()) + " ");
            }
        }
    }

    private void createMines() {
        for (int i = 0; i < totalMines; i++) {
            int x = xAxis().get(random.nextInt(xAxis().size()));
            List<Integer> yRange = yAxis(x);
            int y = yRange.get(random.nextInt(yRange.size()));
            Cell cell = new Cell(x, y);
            cell.setStatus(Status.MINE);
            cells.add(cell);
        }
    }

    private void createNumbers() {
        Set<Cell> temp = cells.stream().filter(cell -> !cell.getStatus().equals(Status.NUMBER)).collect(Collectors.toSet());
        for (Cell cell : temp) {
            if (cells.contains(cell) && cell.getStatus().equals(Status.MINE)) {
                addNumbers(cell.getX(), cell.getY());
            }
        }
    }

    private void addNumbers(int x, int y) {
        if (x == 1) {
            initNumber(x + 1, y);
            if (y == 1) {
                initNumber(x, y + 1);
                initNumber(x + 1, y + 1);
            } else if (1 < y && y < width) {
                initNumber(x, y - 1);
                initNumber(x + 1, y - 1);
                initNumber(x, y + 1);
                initNumber(x + 1, y + 1);
            } else {
                initNumber(x, y - 1);
                initNumber(x + 1, y - 1);
            }
        } else if (1 < x && x < height) {
            initNumber(x + 1, y);
            initNumber(x - 1, y);
            if (y == 1) {
                initNumber(x - 1, y + 1);
                initNumber(x, y + 1);
                initNumber(x + 1, y + 1);
            } else if (1 < y && y < width) {
                initNumber(x - 1, y - 1);
                initNumber(x, y - 1);
                initNumber(x + 1, y - 1);
                initNumber(x - 1, y + 1);
                initNumber(x, y + 1);
                initNumber(x + 1, y + 1);
            } else {
                initNumber(x - 1, y - 1);
                initNumber(x, y - 1);
                initNumber(x + 1, y - 1);
            }
        } else {
            initNumber(x - 1, y);
            if (y == 1) {
                initNumber(x - 1, y + 1);
                initNumber(x, y + 1);
            } else if (1 < y && y < width) {
                initNumber(x - 1, y - 1);
                initNumber(x, y - 1);
                initNumber(x - 1, y + 1);
                initNumber(x, y + 1);
            } else {
                initNumber(x - 1, y - 1);
                initNumber(x, y - 1);
            }
        }
    }

    private void initNumber(int x, int y) {
        Cell temp = checkNumber(x, y);
        if (temp != null) {
            if (temp instanceof Number) {
                int value = ((Number) temp).getValue();
                ((Number) temp).setValue(value + 1);
            }
        } else {
            Number number = new Number(x, y);
            number.setValue(1);
            number.setStatus(Status.NUMBER);
            cells.add(number);
        }
    }

    private Cell checkNumber(int x, int y) {
        for (Cell cell : cells) {
            if (cell.getX() == x && cell.getY() == y) {
                return cell;
            }
        }
        return null;
    }

    private void crateEmpties() {
        for (int x = 1; x <= height; x++) {
            for (int y = 1; y <= width; y++) {
                boolean noExist = false;
                for (Cell cell : cells) {
                    if (cell.getX() == x && cell.getY() == y) {
                        noExist = true;
                    }
                }
                if (!noExist) {
                    Cell empty = new Cell(x, y);
                    empty.setStatus(Status.EMPTY);
                    cells.add(empty);
                }
            }
        }
    }

    private List<Integer> xAxis(){
        List<Integer> list = new ArrayList();
        for (int x = 1; x <= width; x++) {
            if (yAxis(x).size() > 0 && !list.contains(x)) list.add(x);
        }
        return list;
    }

    private List<Integer> yAxis(int x){
        List<Integer> list = new ArrayList();
        for (int i = 1; i <= width; i++) {
            list.add(i);
        }
        List<Integer> removed = new ArrayList<>();
        for(Cell cell: cells){
            if (cell.getX() == x){
                removed.add(cell.getY());
            }
        }
        list.remove(removed);
        return list;
    }

    public Set<Cell> getCells() {
        return cells;
    }

    public static Model getModel(int n, int inputX, int inputY) {
        if (model == null) {
            model = new Model(n, inputX, inputY);
        }
        return model;
    }
}