package project.helpers;

import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * @author Alexanred Naumov on 30.10.2017
 * @version 1.0.0
 */

public class PaintField implements Runnable{

    private final int n;
    private final JButton[][] buttons;
    private final ImageIcon image = new ImageIcon("images\\default.png");

    public PaintField(int n, JButton[][] buttons) {
        this.n = n;
        this.buttons = buttons;
    }

    @Override
    public void run() {
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                buttons[x][y].setIcon(image);
                try {
                    Thread.sleep(3);
                } catch (InterruptedException ex) {
                    System.out.println("exception!");
                }
            }
        }
    }
}
