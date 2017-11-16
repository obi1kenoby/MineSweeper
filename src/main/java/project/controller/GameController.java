package project.controller;

import project.level.Level;
import project.view.GameFrame;

import javax.swing.*;
import java.awt.*;


public class GameController {

    private int height;
    private int width;
    private Level level;
    private JPanel rootPanel;
    private JLabel timeLabel;
    private JPanel buttonPanel;
    private JButton[][]buttons;

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

    public void newGame(){
        System.out.println("create new game.");
        buttonPanel.removeAll();
        setLevel(level);
        new Thread(new Game(height, width, buttons, timeLabel, level)).start();
    }

    public void setLevel(Level level) {
        System.out.println("change level to " + level);
        this.level = level;
        if (level.equals(Level.EASY)){
            height = 9;
            width = 9;
            buttonPanel.setPreferredSize(new Dimension(210, 210));
            rootPanel.repaint();
        } else if (level.equals(Level.MEDIUM)){
            height = 16;
            width = 16;
            buttonPanel.setPreferredSize(new Dimension(420, 420));
            rootPanel.repaint();
        } else {
            height = 16;
            width = 30;
            buttonPanel.setPreferredSize(new Dimension(788, 420));
            rootPanel.repaint();
        }
        buttonPanel.setLayout(new GridLayout(height, width, 1, 1));
        buttons = new JButton[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                buttons[x][y] = new JButton();
                buttons[x][y].setFocusPainted(false);
                buttonPanel.add(buttons[x][y]);
            }
        }
    }
}
