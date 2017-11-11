package model;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import project.models.Cell;
import project.models.Model;
import project.models.Status;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(value = Parameterized.class)
public class ModelTest {

    private int level;
    private int size;
    private int mines;
    private int width;
    private int height;

    public ModelTest(int level, int size, int mines) {
        this.level = level;
        this.size = size;
        this.mines = mines;
        switch (level) {
            case 9:
                width = 9;
                height = 9;
                break;
            case 16:
                width = 16;
                height = 16;
                break;
            default:
                width = 30;
                height = 16;
                break;
        }
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{9, 81, 10}, {16, 256, 40}, {30, 480, 99}};
        return Arrays.asList(data);
    }

    @Test
    public void defaultCells() {
        Random random = new Random();
        int x = random.nextInt(width) + 1;
        int y = random.nextInt(height) + 1;
        int initCells;
        Model model = Model.getModel(level, x, y);
        model.initialCell();
        if (x == 1) {
            if (y == 1) {
                initCells = 4;
            } else if (1 < y && y < height) {
                initCells = 6;
            } else {
                initCells = 4;
            }
        } else if (1 < x && x < width) {
            if (y == 1) {
                initCells = 6;
            } else if (1 < y && y < height) {
                initCells = 9;
            } else {
                initCells = 6;
            }
        } else {
            if (y == 1) {
                initCells = 4;
            } else if (1 < y && y < height) {
                initCells = 6;
            } else {
                initCells = 4;
            }
        }
        assertEquals(initCells, model.getCells().size());
    }

    @Test
    public void mines() {
        Random random = new Random();
        int x = random.nextInt(width) + 1;
        int y = random.nextInt(height) + 1;
        int minesCounter = 0;
        Model model = Model.getModel(level, x, y);
        model.initialCell();
        model.createMines();
        for (Cell cell : model.getCells()) {
            if (cell.getStatus() == Status.MINE) minesCounter++;
        }
        assertEquals(minesCounter, mines);
    }

    @Test
    public void nonIntersection() {
        Random random = new Random();
        int x = random.nextInt(width) + 1;
        int y = random.nextInt(height) + 1;
        Model model = Model.getModel(level, x, y);
        model.initialCell();
        model.createMines();
        List<Cell> initCells = model.getCells().stream().
                filter(Cell::isMain).
                collect(Collectors.toList());
        List<Cell> mines = model.getCells().stream().
                filter(cell -> cell.getStatus() == Status.MINE).
                collect(Collectors.toList());
        for (Cell mine : mines) {
            for (Cell cell : initCells) {
                if (mine.getX() == cell.getX() && mine.getY() == cell.getY()) fail(
                        "mine and init cell can't have one cell " + mine);
            }
        }
    }

    @Test
    public void createNumbers() {
        Random random = new Random();
        int X = random.nextInt(width) + 1;
        int Y = random.nextInt(height) + 1;
        Model model = Model.getModel(level, X, Y);
        model.initialCell();
        model.createMines();
        model.removeInitialCell();
        model.createNumbers();
        model.crateEmpties();
        List<Cell> mines = new ArrayList<>();
        List<Cell> numbers = new ArrayList<>();
        List<Cell> empties = new ArrayList<>();
        for(Cell cell: model.getCells()){
            switch (cell.getStatus()){
                case NUMBER:
                    numbers.add(cell);
                    break;
                case EMPTY:
                    empties.add(cell);
                    break;
                case MINE:
                    mines.add(cell);
                    break;
            }
        }
        assertEquals((mines.size() + numbers.size() + empties.size()), size);
    }
}