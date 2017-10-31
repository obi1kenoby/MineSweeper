package project.helpers;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.*;

/**
 * @author Alexanred Naumov on 30.10.2017
 * @version 1.0.0
 */

public class PaintField implements Runnable {

    private final int n;
    private final JButton[][] buttons;
    private final ImageIcon image = new ImageIcon("images\\default.png");

    public PaintField(int n, JButton[][] buttons) {
        this.n = n;
        this.buttons = buttons;
    }

    @Override
    public void run() {
        int counter = 0;
        Random random = new Random();
        while (counter < (n * n)){
            List<Integer> xs = xRange();
            int x = xs.get(random.nextInt(xs.size()));
            List<Integer> ys = yRange(x);
            int y = ys.get(random.nextInt(ys.size()));
            buttons[x][y].setIcon(image);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
        }
        System.out.println("complete.");
    }

    private List<Integer> yRange(int currentX) {
        List<Integer> list = new ArrayList<>();
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (currentX == x && buttons[x][y].getIcon() == null) {
                    list.add(y);
                }
            }
        }
        return list;
    }

    private List<Integer> xRange() {
        List<Integer> list = new ArrayList<>();
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                if (buttons[x][y].getIcon() == null) {
                    list.add(x);
                }
            }
        }
        return list;
    }
}
