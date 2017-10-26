package model;

import java.util.Random;

import static org.junit.Assert.assertEquals;

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
    private Random random;
    private static final int beginner = 9;
    private static final int medium = 16;

    @Before
    public void before() {
        random = new Random();
        int x = random.nextInt(medium) + 1;
        int y = random.nextInt(medium) + 1;
        model = new Model(medium, x, y);
    }

    @Test
    public void model(){
        assertEquals(model.getCells().size(), medium * medium);
    }

    @Test
    public void mines() {
        long count = model.getCells().
                     stream().
                     filter((cell) -> (cell.getStatus().equals(Status.MINE))).
                     count();
        assertEquals(count, 40);
    }
}

