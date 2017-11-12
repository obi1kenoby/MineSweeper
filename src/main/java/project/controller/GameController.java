package project.controller;

import project.helpers.PaintField;
import project.helpers.Time;
import project.view.GameFrame;

import javax.swing.*;

/**
 * @author Alexander Naumov on 26.10.2017
 * @version 1.0.0
 */

public class GameController {

    private boolean begin;
    private final GameFrame view;
    private int n;

    public GameController(int n) {
        this.n = n;
        view = GameFrame.getFrame(n);
    }

    public void play() {
        new Thread(new PaintField(view.getHeightField(), view.getWidthField(), view.getButtons())).start();
        if (begin){
            new Thread(new Time(view.getTimeLabel())).start();
        }
        JButton[][] buttons = view.getButtons();
        for (int x = 0; x < view.getWidthField(); x++) {
            for (int y = 0; y < view.getHeightField(); y++) {
                buttons[y][x].addMouseListener(new MyMouseListener(x, y, n, view.getHeightField(), view.getWidthField(), buttons, view));
            }
        }

        view.getClose().addActionListener(e -> System.exit(0));

        //view.getNewGame().addActionListener();
    }
}
