package project.controller;

import project.helpers.Time;
import project.level.Level;
import project.models.Model;
import project.saver.Saver;
import project.view.GameFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Random;


public class GameController {

    private Model model;
    private int height;
    private int width;
    private Level level;
    private Thread timer;
    private JLabel timeLabel;
    private JLabel flagLabel;
    private JPanel buttonPanel;
    private JButton[][] buttons;
    private static GameController gameController;
    private Saver saver;
    private long seed;

    private GameController(JLabel timeLabel, JLabel flagLabel, JPanel buttonPanel, JButton[][] buttons, Level level) {
        this.timeLabel = timeLabel;
        this.flagLabel = flagLabel;
        this.buttonPanel = buttonPanel;
        this.buttons = buttons;
        this.level = level;
        saver = Saver.getSaver();
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

        Random random = new Random();
        seed = random.nextInt(1000000);
        timer = new Thread(new Time(timeLabel));
        prepareLevel(level);

        if (saver.getFile().exists()){
            restoreGame();
        }
        else {
            Game game = new Game(height, width, buttons, timeLabel, flagLabel, level, timer, seed);
            new Thread(game).start();
        }
        saver.deleteSaves();
        System.out.println("create new game.");
    }

    public void restartGame(){
        if (timer != null) {
            timer.interrupt();
        }

        timer = new Thread(new Time(timeLabel));
        prepareLevel(level);
        new Thread(new Game(height, width, buttons, timeLabel, flagLabel, level, timer, seed)).start();
        System.out.println("restart new game.");
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

    public void restoreGame(){
        Object[] objects = saver.read();
        Model model = (Model) objects[0];
        JButton[][] butts = (JButton[][]) objects[1];
        timeLabel.setText(((JLabel)objects[2]).getText());
        flagLabel.setText(((JLabel)objects[3]).getText());
        int h = butts.length;
        int w = butts[0].length;
        buttonPanel.removeAll();
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                buttonPanel.add(butts[j][i]);
            }
        }
        Game game = new Game(height, width, butts, timeLabel, flagLabel, level, timer, seed);
        game.setModel(model);
        new Thread(game).start();
    }

    public void storeGame(){
        saver.save(model, buttons, timeLabel, flagLabel);
    }

    public void setSpecialParams(int h, int w) {
        this.height = h;
        this.width = w;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }

    public long getSeed() {
        return seed;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public static GameController gameController(JLabel timeLabel, JLabel flagLabel, JPanel buttonPanel, JButton[][] buttons, Level level){
        if (gameController == null){
            gameController = new GameController(timeLabel, flagLabel, buttonPanel, buttons, level);
        }
        return gameController;
    }

    public static GameController gameController(){
        return gameController;
    }
}
