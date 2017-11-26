package project.controller;

import project.helpers.Time;
import project.level.Level;
import project.view.GameFrame;

import javax.swing.*;
import java.awt.*;


public class GameController {

    private int height;
    private int width;
    private Level level;
    private Thread timer;
    private JLabel timeLabel;
    private JPanel buttonPanel;
    private JButton[][] buttons;

    public GameController(JLabel timeLabel, JPanel buttonPanel, JButton[][] buttons, Level level) {
        this.timeLabel = timeLabel;
        this.buttonPanel = buttonPanel;
        this.buttons = buttons;
        this.level = level;
        height = 9;
        width = 9;
        newGame();
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public void newGame() {
        if (timer != null) {
            timer.interrupt();
        }
        timer = new Thread(new Time(timeLabel));
        prepareLevel(level);
        new Thread(new Game(height, width, buttons, timeLabel, level, timer)).start();
        System.out.println("create new game.");
    }

    public void prepareLevel(Level level) {
        System.out.println("prepare " + level + " level");
        switch (level) {
            case EASY:
                height = 9;
                width = 9;
                break;
            case MEDIUM:
                height = 16;
                width = 16;
                break;
            case HARD:
                height = 16;
                width = 30;
                break;
        }
        buttonPanel.removeAll();
        buttonPanel.setPreferredSize(new Dimension((width * 23) - 2, (height * 23) - 2));
        buttonPanel.setLayout(new GridLayout(height, width, 1, 1));
        buttons = new JButton[width][height];
        timeLabel.setText("00:00");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                buttons[x][y] = new JButton();
                buttons[x][y].setFocusPainted(false);
                buttonPanel.add(buttons[x][y]);
            }
        }
    }

    public void setSpecialParams(int h, int w) {
        this.height = h;
        this.width = w;
    }
}
