package project.controller;


import project.level.Level;
import project.helpers.GameOver;
import project.models.Cell;
import project.models.Model;
import project.models.Number;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Random;
import javax.swing.*;

import project.models.Status;
import project.view.SettingsFrame;

/**
 * @author Alexander Naumov on 28.10.2017
 * @version 1.0.0
 */

public class MyMouseListener implements MouseListener {

    private final int x;
    private final int y;
    private long seed;
    private Thread timer;
    private JLabel flagLabel;
    private int width;
    private int height;
    private Model model;
    private Level level;
    private List<MyMouseListener> listenersList;
    private final JButton[][] buttons;
    private final ImageIcon accentuated = new ImageIcon("images/accentuated.png");
    private final ImageIcon def = new ImageIcon("images/default.png");
    private final ImageIcon mine = new ImageIcon("images/mine.png");
    private final ImageIcon flag = new ImageIcon("images/flag.png");
    private final ImageIcon empty = new ImageIcon("images/empty.png");
    private final ImageIcon one = new ImageIcon("images/1.png");
    private final ImageIcon two = new ImageIcon("images/2.png");
    private final ImageIcon three = new ImageIcon("images/3.png");
    private final ImageIcon four = new ImageIcon("images/4.png");
    private final ImageIcon five = new ImageIcon("images/5.png");
    private final ImageIcon six = new ImageIcon("images/6.png");
    private final ImageIcon seven = new ImageIcon("images/7.png");
    private final ImageIcon eight = new ImageIcon("images/8.png");

    public MyMouseListener(List<MyMouseListener> listenersList, JLabel flagLabel, int x, int y, Level level, int width, int height, JButton[][] buttons, Thread timer) {
        this.listenersList = listenersList;
        this.flagLabel = flagLabel;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.level = level;
        this.buttons = buttons;
        this.timer = timer;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int modifiers = e.getModifiers();
        if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
            if (model == null) {
                if (level.equals(Level.SPECIAL)) {
                    SettingsFrame settingsFrame = SettingsFrame.getSettingFrame();
                    int mines;
                    try {
                        mines = Integer.parseInt(settingsFrame.getMinesField().getText());
                    }
                    catch (Exception ex){
                        mines = 10;
                    }

                    model = new Model(x +1, y +1, height, width, mines, seed);
                }
                else {
                    model = new Model(level, x + 1, y + 1, seed);
                }
                model.initialCell();
                model.createMines();
                model.removeInitialCell();
                model.createNumbers();
                model.crateEmpties();
                timer.start();
                listenersList.forEach(listener -> listener.setModel(model));
                GameController gameController = GameController.gameController();
                gameController.setModel(model);
            }
            openButton(x, y);
        }
        if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
            if (!buttons[x][y].isEnabled() && buttons[x][y].getDisabledIcon().equals(flag)) {
                buttons[x][y].setEnabled(true);
                buttons[x][y].setDisabledIcon(def);
            } else if (buttons[y][x].isEnabled()) {
                buttons[x][y].setEnabled(false);
                buttons[x][y].setDisabledIcon(flag);
                int counter = Integer.parseInt(flagLabel.getText());
                counter++;
                flagLabel.setText(Integer.toString(counter));
                timer.interrupt();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        buttons[x][y].setIcon(accentuated);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        buttons[x][y].setIcon(def);
    }

    private void setNumber(JButton button, int value) {
        button.setEnabled(false);
        switch (value) {
            case 1:
                button.setDisabledIcon(one);
                break;
            case 2:
                button.setDisabledIcon(two);
                break;
            case 3:
                button.setDisabledIcon(three);
                break;
            case 4:
                button.setDisabledIcon(four);
                break;
            case 5:
                button.setDisabledIcon(five);
                break;
            case 6:
                button.setDisabledIcon(six);
                break;
            case 7:
                button.setDisabledIcon(seven);
                break;
            case 8:
                button.setDisabledIcon(eight);
                break;
        }
    }

    private void openButton(int x, int y) {
        if (!buttons[x][y].isEnabled()) {
            return;
        }
        for (Cell cell : model.getCells()) {
            if (cell.getX() == (x + 1) && cell.getY() == (y + 1)) {
                Status status = cell.getStatus();
                switch (status) {
                    case EMPTY:
                        buttons[x][y].setEnabled(false);
                        buttons[x][y].setDisabledIcon(empty);
                        if (x == 0) {
                            openButton(x + 1, y);
                            if (y == 0) {
                                openButton(x, y + 1);
                                openButton(x + 1, y + 1);
                            } else if (0 < y && y < height - 1) {
                                openButton(x, y - 1);
                                openButton(x, y + 1);
                                openButton(x + 1, y - 1);
                                openButton(x + 1, y + 1);
                            } else {
                                openButton(x, y - 1);
                                openButton(x + 1, y - 1);
                            }
                        } else if (0 < x && x < width - 1) {
                            openButton(x + 1, y);
                            openButton(x - 1, y);
                            if (y == 0) {
                                openButton(x, y + 1);
                                openButton(x - 1, y + 1);
                                openButton(x + 1, y + 1);
                            } else if (0 < y && y < height - 1) {
                                openButton(x - 1, y - 1);
                                openButton(x + 1, y - 1);
                                openButton(x, y - 1);
                                openButton(x, y + 1);
                                openButton(x - 1, y + 1);
                                openButton(x + 1, y + 1);
                            } else {
                                openButton(x, y - 1);
                                openButton(x - 1, y - 1);
                                openButton(x + 1, y - 1);
                            }
                        } else {
                            openButton(x - 1, y);
                            if (y == 0) {
                                openButton(x, y + 1);
                                openButton(x - 1, y + 1);
                            } else if (0 < y && y < height - 1) {
                                openButton(x, y - 1);
                                openButton(x, y + 1);
                                openButton(x - 1, y - 1);
                                openButton(x - 1, y + 1);
                            } else {
                                openButton(x, y - 1);
                                openButton(x - 1, y - 1);
                            }
                        }
                        break;
                    case NUMBER:
                        int value = ((Number) cell).getValue();
                        setNumber(buttons[x][y], value);
                        break;
                    case MINE:
                        buttons[x][y].setEnabled(false);
                        buttons[x][y].setDisabledIcon(mine);
                        timer.interrupt();
                        new Thread(new GameOver(model, buttons)).start();
                }
            }
        }
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }
}