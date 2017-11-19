package project.controller;

import project.helpers.Time;
import project.level.Level;

import javax.swing.*;
import java.awt.*;


public class GameController {

    private int height;
    private int width;
    private Level level;
    private Thread timer;
    private JPanel rootPanel;
    private JLabel timeLabel;
    private JPanel buttonPanel;
    private JButton[][] buttons;

    public GameController(JLabel timeLabel, JPanel rootPanel, JPanel buttonPanel, JButton[][] buttons, Level level) {
        this.timeLabel = timeLabel;
        this.rootPanel = rootPanel;
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
        buttonPanel.removeAll();
        prepareLevel(level);
        new Thread(new Game(height, width, buttons, timeLabel, level, timer)).start();
        System.out.println("create new game.");
    }

    public void prepareLevel(Level level) {
        System.out.println("prepare " + level + " level");
        if (level.equals(Level.EASY)) {
            height = 9;
            width = 9;
            buttonPanel.setPreferredSize(new Dimension(210, 210));
            rootPanel.repaint();
        } else if (level.equals(Level.MEDIUM)) {
            height = 16;
            width = 16;
            buttonPanel.setPreferredSize(new Dimension(420, 420));
            rootPanel.repaint();
        } else {
            height = 16;
            width = 30;
            buttonPanel.setPreferredSize(new Dimension(784, 420));
            rootPanel.repaint();
        }
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
}
