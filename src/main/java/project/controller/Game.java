package project.controller;

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
    private JButton[][] buttons;
    private JLabel timeLabel;
    private Level level;

    public Game(int height, int width, JButton[][] buttons, JLabel timeLabel, Level level) {
        this.height = height;
        this.width = width;
        this.buttons = buttons;
        this.timeLabel = timeLabel;
        this.level = level;
    }

    @Override
    public void run() {
        new Thread(new PaintField(height, width, buttons)).start();
        //new Thread(new Time(timeLabel)).start();
        List<MyMouseListener> listenersList = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                MyMouseListener myMouseListener = new MyMouseListener(listenersList, x, y, level, height, width, buttons);
                listenersList.add(myMouseListener);
                buttons[y][x].addMouseListener(myMouseListener);
            }
        }
    }
}
