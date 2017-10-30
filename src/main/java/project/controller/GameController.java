package project.controller;

import javax.swing.JButton;
import project.helpers.PaintField;
import project.helpers.Time;
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
        new Thread(new PaintField(n, view.getButtons())).start();
        JButton[][] buttons = view.getButtons();
        for (int y = 0; y < n; y++) {
            for (int x = 0; x < n; x++) {
                buttons[y][x].addMouseListener(new MyMouseListener(model.getCells(), x, y, n, buttons));
            }
        }
    }
}
