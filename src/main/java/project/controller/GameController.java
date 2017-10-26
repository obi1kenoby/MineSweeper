package project.controller;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import project.helpers.Time;
import project.models.Cell;
import project.models.Model;
import project.models.Status;
import project.view.MyView;

/**
 * @author Alexander Naumov on 26.10.2017
 * @version 1.0.0
 */

public class GameController {

    private final MyView view;
    private final Model model;
    private final int n;

    public GameController(int n) {
        this.n = n;
        view = new MyView(n);
        model = new Model(n, 4, 1);
        new Thread(new Time(view.getTimeLabel())).start();
    }

    public void setCells() throws IOException {
        JButton[][] buttons = view.getButtons();
        Set<Cell> cells = model.getCells();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        for (Cell cell : cells) {
            int x = cell.getX() - 1;
            int y = cell.getY() - 1;
            ImageIcon image = null;
            if (cell instanceof project.models.Number) {
                int value = ((project.models.Number) cell).getValue();
                switch (value) {
                    case 1:
                        image = new ImageIcon("images\\1.png");
                        break;
                    case 2:
                        image = new ImageIcon("images\\2.png");
                        break;
                    case 3:
                        image = new ImageIcon("images\\3.png");
                        break;
                    case 4:
                        image = new ImageIcon("images\\4.png");
                        break;
                    case 5:
                        image = new ImageIcon("images\\5.png");
                        break;
                    case 6:
                        image = new ImageIcon("images\\6.png");
                        break;
                    case 7:
                        image = new ImageIcon("images\\7.png");
                        break;
                    case 8:
                        image = new ImageIcon("images\\8.png");
                        break;
                }
                buttons[x][y].setIcon(image);
            } else if (cell.getStatus().equals(Status.MINE)) {
                image = new ImageIcon("images\\mine.png");
                buttons[x][y].setIcon(image);
            } else {
                image = new ImageIcon("images\\0.png");
                buttons[x][y].setIcon(image);
            }
        }
    }
}
