package project.controller;

import project.helpers.Time;
import project.level.Level;
import project.models.Model;
import project.saver.Saver;

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

        if (saver.getFile().exists()){
            restoreGame();
            int dialogResult = JOptionPane.showConfirmDialog(null,"Продолжить сохраненную игру ?","Обнаружена сохраненная игра.", JOptionPane.YES_NO_OPTION);
            if(dialogResult == JOptionPane.NO_OPTION){
                prepareLevel(level);
                Game game = new Game(height, width, buttons, timeLabel, flagLabel, level, timer, seed);
                new Thread(game).start();
            }
        }
        else {
            prepareLevel(level);
            Game game = new Game(height, width, buttons, timeLabel, flagLabel, level, timer, seed);
            new Thread(game).start();
        }
        saver.deleteSaves();
        System.out.println("created new game.");
    }

    public void restartGame(){
        if (timer != null) {
            timer.interrupt();
        }

        timer = new Thread(new Time(timeLabel));
        prepareLevel(level);
        new Thread(new Game(height, width, buttons, timeLabel, flagLabel, level, timer, seed)).start();
        System.out.println("restarted game.");
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
        Model restoreModel = (Model) objects[0];
        JButton[][] butts = (JButton[][]) objects[1];
        timeLabel.setText(((JLabel)objects[2]).getText());
        flagLabel.setText(((JLabel)objects[3]).getText());
        Level restoreLevel = (Level)objects[4];
        int w = butts.length;
        int h = butts[0].length;
        buttonPanel.removeAll();
        buttonPanel.setPreferredSize(new Dimension((w * 23) - 2, (h * 23) - 2));
        buttonPanel.setLayout(new GridLayout(h, w, 1, 1));
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                buttonPanel.add(butts[j][i]);
            }
        }
        Game game = new Game(h, w, butts, timeLabel, flagLabel, restoreLevel, timer, seed);
        game.setModel(restoreModel);
        new Thread(game).start();
        buttons = butts;
        model = restoreModel;
        height = h;
        width = w;
        level = restoreLevel;
    }

    public void storeGame(){
        saver.save(model, buttons, timeLabel, flagLabel, level);
    }

    public void setSpecialParams(int h, int w) {
        this.height = h;
        this.width = w;
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
