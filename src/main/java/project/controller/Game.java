package project.controller;

import project.helpers.Time;
import project.level.Level;
import project.helpers.PaintField;

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
    private Thread timer;
    private JButton[][] buttons;
    private JLabel timeLabel;
    private Level level;

    public Game(int height, int width, JButton[][] buttons, JLabel timeLabel, Level level, Thread timer) {
        this.height = height;
        this.width = width;
        this.buttons = buttons;
        this.timeLabel = timeLabel;
        this.level = level;
        this.timer = timer;
    }

    @Override
    public void run() {
        new Thread(new PaintField(height, width, buttons)).start();
        List<MyMouseListener> listenersList = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                MyMouseListener myMouseListener = new MyMouseListener(listenersList, x, y, level, width, height, buttons, timer);
                listenersList.add(myMouseListener);
                buttons[x][y].addMouseListener(myMouseListener);
            }
        }
    }
}
