package project.controller;

import java.util.Set;
import javax.swing.JButton;
import project.helpers.Time;
import project.models.Cell;
import project.models.Model;
import project.view.GameFrame;

/**
 * @author Alexander Naumov on 26.10.2017
 * @version 1.0.0
 */

public class GameController {

    private final GameFrame view;
    private final Model model;
    private final int n;


    public GameController(int n) {
        this.n = n;
        view = new GameFrame(n);
        model = new Model(n, 4, 1);
        new Thread(new Time(view.getTimeLabel())).start();
    }

    public void play(){
        JButton[][] buttons = view.getButtons();
        Set<Cell> cells = model.getCells();
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                buttons[x][y].addMouseListener(new MyMouseListener(buttons[x][y], model.getCells(), x, y));
            }
        }
    }
}
