package project.controller;

import project.helpers.PaintField;
import project.helpers.Time;
import project.models.Model;
import project.view.GameFrame;

import javax.swing.*;

/**
 * @author Alexander Naumov on 26.10.2017
 * @version 1.0.0
 */

public class GameController {

    private final GameFrame view;

    public GameController(int n) {
        view = new GameFrame(n);
        new Thread(new Time(view.getTimeLabel())).start();
    }

    public void play(){
        new Thread(new PaintField(view.getHeightField(), view.getWidthField(), view.getButtons())).start();
        JButton[][] buttons = view.getButtons();
        for (int x = 0; x < view.getHeightField(); x++) {
            for (int y = 0; y < view.getWidthField(); y++) {

            }
        }

        view.getClose().addActionListener(e -> System.exit(0));
    }
}
