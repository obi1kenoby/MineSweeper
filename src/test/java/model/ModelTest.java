package model;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import project.models.Model;
import project.models.Status;

/**
 * @author Alexandr Naumov on 21.10.2017
 * @version 1.0.0
 */

public class ModelTest {

    private Model model;
    private static final int N = 16;
    private int x;
    private int y;

    @Before
    public void before() {
        Random random = new Random();
        x = random.nextInt(N) + 1;
        y = random.nextInt(N) + 1;
        model = new Model(N, x, y);
    }

    @Test
    public void model(){
        assertEquals(model.getCells().size(), N * N);
    }

    @Test
    public void mines() {
        long count = model.getCells().
                     stream().
                     filter((cell) -> (cell.getStatus().equals(Status.MINE))).
                     count();
        assertEquals(count, 40);
    }

    @Test
    public void firstNoMine(){
        model.getCells().stream().filter((cell) -> (cell.getX() == x && cell.getY() == y)).
                        filter((cell) -> (cell.getStatus().equals(Status.MINE))).
                        forEachOrdered((_item) -> fail());
    }
}

