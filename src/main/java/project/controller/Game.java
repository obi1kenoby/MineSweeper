package project.controller;

import project.level.Level;
import project.helpers.PaintField;
import project.models.Model;

import javax.swing.*;
import java.util.*;
import java.util.List;

/**
 * @author Alexander Naumov on 26.10.2017
 * @version 1.0.0
 */

public class Game implements Runnable {

    private int height;
    private int width;
    private long seed;
    private Thread timer;
    private JButton[][] buttons;
    private JLabel flagLabel;
    private Model model;
    private Level level;

    public Game(int height, int width, JButton[][] buttons, JLabel flagLabel, Level level, Thread timer, long seed) {
        this.height = height;
        this.width = width;
        this.buttons = buttons;
        this.flagLabel = flagLabel;
        this.level = level;
        this.timer = timer;
        this.seed = seed;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void run() {
        new Thread(new PaintField(height, width, buttons)).start();
        List<MyMouseListener> listenersList = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                MyMouseListener myMouseListener = new MyMouseListener(listenersList, flagLabel, x, y, level, width, height, buttons, timer);
                myMouseListener.setSeed(seed);
                myMouseListener.setModel(model);
                listenersList.add(myMouseListener);
                buttons[x][y].addMouseListener(myMouseListener);
            }
        }
    }
}
