package project.controller;


import project.level.Level;
import project.helpers.GameOver;
import project.models.Cell;
import project.models.Model;
import project.models.Number;

import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.List;
import javax.swing.*;

import project.models.Status;
import project.view.SettingsFrame;
import project.view.WinFrame;

/**
 * @author Alexander Naumov on 28.10.2017
 * @version 1.0.0
 */

public class MyMouseListener implements MouseListener {

    private static long mines;
    private final int x;
    private final int y;
    private long seed;
    private static int count;
    private Thread timer;
    private JLabel flagLabel;
    private int width;
    private int height;
    private Model model;
    private Level level;
    private List<MyMouseListener> listenersList;
    private final JButton[][] buttons;
    private final ImageIcon accentuated = new ImageIcon("src/main/resources/accentuated.png");
    private final ImageIcon def = new ImageIcon("src/main/resources/default.png");
    private final ImageIcon mine = new ImageIcon("src/main/resources/mine.png");
    private final ImageIcon flag = new ImageIcon("src/main/resources/flag.png");
    private final ImageIcon empty = new ImageIcon("src/main/resources/empty.png");
    private final ImageIcon one = new ImageIcon("src/main/resources/1.png");
    private final ImageIcon two = new ImageIcon("src/main/resources/2.png");
    private final ImageIcon three = new ImageIcon("src/main/resources/3.png");
    private final ImageIcon four = new ImageIcon("src/main/resources/4.png");
    private final ImageIcon five = new ImageIcon("src/main/resources/5.png");
    private final ImageIcon six = new ImageIcon("src/main/resources/6.png");
    private final ImageIcon seven = new ImageIcon("src/main/resources/7.png");
    private final ImageIcon eight = new ImageIcon("src/main/resources/8.png");

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
                    } catch (Exception ex) {
                        mines = 10;
                    }
                    model = new Model(x + 1, y + 1, height, width, mines, seed);
                } else {
                    model = new Model(level, x + 1, y + 1, seed);
                }
                model.initialCell();
                model.createMines();
                model.removeInitialCell();
                model.createNumbers();
                model.crateEmpties();
                listenersList.forEach(listener -> listener.setModel(model));
                GameController gameController = GameController.gameController();
                gameController.setModel(model);
                mines = model.getCells().stream().filter(cell -> cell.getStatus().equals(Status.MINE)).count();
                flagLabel.setText(Long.toString(mines));
                timer.start();
                count = 0;
            } else {
                if (timer.getState() == Thread.State.NEW) {
                    timer.start();
                }
            }
            openButton(x, y);
        }

        if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
            int flagCounter = Integer.parseInt(flagLabel.getText());
            if (!buttons[x][y].isEnabled() && buttons[x][y].getDisabledIcon().equals(flag)) {
                buttons[x][y].setEnabled(true);
                buttons[x][y].setIcon(def);
                flagCounter++;
            } else if (buttons[x][y].isEnabled()) {
                buttons[x][y].setEnabled(false);
                buttons[x][y].setDisabledIcon(flag);
                flagCounter--;
            }
            flagLabel.setText(Integer.toString(flagCounter));
            if (timer.getState() == Thread.State.NEW) {
                timer.start();
            }
        }

        win();
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
                        count++;
                        break;
                    case NUMBER:
                        int value = ((Number) cell).getValue();
                        setNumber(buttons[x][y], value);
                        count++;
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

    private void win(){
        if ((model.getCells().size() - count) <= mines){
            timer.interrupt();
            GameController gameController = GameController.gameController();
            WinFrame winFrame = WinFrame.getWinFrame();
            winFrame.setTime(gameController.getTimeLabel().getText());
            winFrame.setDate(new Date());
            winFrame.visibleControl();
        }
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public void setSeed(long seed) {
        this.seed = seed;
    }
}