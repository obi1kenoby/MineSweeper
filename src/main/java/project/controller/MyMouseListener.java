package project.controller;

import project.helpers.GameOver;
import project.models.Cell;
import project.models.Model;
import project.models.Number;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import project.models.Status;
import project.view.GameFrame;

/**
 * @author Alexander Naumov on 28.10.2017
 * @version 1.0.0
 */

public class MyMouseListener implements MouseListener {

    private final int x;
    private final int y;
    private int width;
    private int height;
    private Model model;
    private GameFrame frame;
    private final int n;
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

    public MyMouseListener(int x, int y, int n, int width, int height, JButton[][] buttons, GameFrame frame) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.n = n;
        this.buttons = buttons;
        this.frame = frame;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int modifiers = e.getModifiers();
        if ((modifiers & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {
            model = Model.getModel(n, x+1, y+1);
            openButton(x, y);
        }
        if ((modifiers & InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
            if (!buttons[y][x].isEnabled() && buttons[y][x].getDisabledIcon().equals(flag)){
                buttons[y][x].setEnabled(true);
                buttons[y][x].setDisabledIcon(def);
            }
            else if (buttons[y][x].isEnabled()){
                buttons[y][x].setEnabled(false);
                buttons[y][x].setDisabledIcon(flag);
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
        buttons[y][x].setIcon(accentuated);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        buttons[y][x].setIcon(def);
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
        if (!buttons[y][x].isEnabled()){
            return;
        }
        for (Cell cell : model.getCells()) {
            if (cell.getX() == (x + 1) && cell.getY() == (y + 1)) {
                Status status = cell.getStatus();
                switch (status) {
                    case EMPTY:
                        buttons[y][x].setEnabled(false);
                        buttons[y][x].setDisabledIcon(empty);
                        if (x == 0) {
                            openButton(x + 1, y);
                            if (y == 0) {
                                openButton(x, y + 1);
                                openButton(x + 1, y + 1);
                            } else if (0 < y && y < width - 1) {
                                openButton(x, y - 1);
                                openButton(x, y + 1);
                                openButton(x + 1, y - 1);
                                openButton(x + 1, y + 1);
                            } else {
                                openButton(x, y - 1);
                                openButton(x + 1, y - 1);
                            }
                        } else if (0 < x && x < height - 1) {
                            openButton(x + 1, y);
                            openButton(x - 1, y);
                            if (y == 0) {
                                openButton(x, y + 1);
                                openButton(x - 1, y + 1);
                                openButton(x + 1, y + 1);
                            } else if (0 < y && y < width - 1) {
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
                            } else if (0 < y && y < width - 1) {
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
                        setNumber(buttons[y][x], value);
                        break;
                    case MINE:
                        buttons[y][x].setEnabled(false);
                        buttons[y][x].setDisabledIcon(mine);
                        frame.setEnabled(false);
                        new Thread(new GameOver(model, buttons)).start();
                }
            }
        }
    }
}