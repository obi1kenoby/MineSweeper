package project.helpers;

import javax.swing.*;
import java.util.*;

/**
 * @author Alexanred Naumov on 30.10.2017
 * @version 1.0.0
 */

public class PaintField implements Runnable{

    private final int height;
    private final int width;
    private final JButton[][] buttons;
    private final ImageIcon image = new ImageIcon("images\\default.png");

    public PaintField(int height, int width, JButton[][] buttons) {
        this.height = height;
        this.width = width;
        this.buttons = buttons;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (xRange().size() > 0){
            List<Integer> xs = xRange();
            int x = xs.get(random.nextInt(xs.size()));
            List<Integer> ys = yRange(x);
            int y = ys.get(random.nextInt(ys.size()));
            buttons[x][y].setIcon(image);
        }
    }

    private List<Integer> yRange(int currentX) {
        List<Integer> list = new ArrayList<>();
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (currentX == x && buttons[x][y].getIcon() == null) {
                    list.add(y);
                }
            }
        }
        return list;
    }

    private List<Integer> xRange() {
        List<Integer> list = new ArrayList<>();
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                if (buttons[x][y].getIcon() == null) {
                    if (!list.contains(x)) list.add(x);
                }
            }
        }
        return list;
    }
}
