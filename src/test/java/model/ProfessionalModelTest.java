package model;

import org.junit.Test;
import project.models.Cell;
import project.models.Model;
import project.models.Status;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * @author Alexander Naumov on 07.11.2017.
 * @version 2.0.0
 */

public class ProfessionalModelTest {

    private int width = 30;
    private int height = 16;

    @Test
    public void initCells16(){
        Random random = new Random();
        int x = random.nextInt(width) + 1;
        int y = random.nextInt(height) + 1;
        Model model = new Model(50, x, y);
        model.initialCell();
        int initCells = 0;
        if (x == 1){
            if (y == 1){
                initCells = 4;
            }
            else if (1 < y && y < height){
                initCells = 6;
            }
            else {
                initCells = 4;
            }
        }
        else if(1 < x && x < width) {
            if (y == 1){
                initCells = 6;
            }
            else if (1 < y && y < height){
                initCells = 9;
            }
            else {
                initCells = 6;
            }
        }
        else {
            if (y == 1){
                initCells = 4;
            }
            else if (1 < y && y < height){
                initCells = 6;
            }
            else {
                initCells = 4;
            }
        }
        assertEquals(initCells, model.getCells().size());
    }

    @Test
    public void mines9(){
        Random random = new Random();
        int x = random.nextInt(width) + 1;
        int y = random.nextInt(height) + 1;
        int minesCounter = 0;
        Model model = new Model(50, x, y);
        model.initialCell();
        model.createMines();
        for (Cell cell: model.getCells()){
            if (cell.getStatus() == Status.MINE)minesCounter++;
        }
        assertEquals(minesCounter, 99);
    }

    @Test
    public void nonIntersection(){
        Random random = new Random();
        int x = random.nextInt(width) + 1;
        int y = random.nextInt(height) + 1;
        Model model = new Model(50, x, y);
        model.initialCell();
        model.createMines();
        List<Cell> initCells = model.getCells().stream().
                filter(Cell::isMain).
                collect(Collectors.toList());
        List<Cell> mines = model.getCells().stream().
                filter(cell -> cell.getStatus() == Status.MINE).
                collect(Collectors.toList());
        for(Cell mine: mines){
            for (Cell cell: initCells){
                if (mine.getX() == cell.getX() && mine.getY() == cell.getY()) fail(
                        "mine and init cell can't have one cell " + mine);
            }
        }
    }

    @Test
    public void removeInitCells() {
        Random random = new Random();
        int x = random.nextInt(width) + 1;
        int y = random.nextInt(height) + 1;
        Model model = new Model(50, x, y);
        model.initialCell();
        model.createMines();
        model.removeInitialCell();
        long mines = model.getCells().stream().
                filter(cell -> cell.getStatus() == Status.MINE).count();
        assertEquals(mines, 99);
    }
}
