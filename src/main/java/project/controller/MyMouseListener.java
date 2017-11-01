package project.controller;

import project.models.Cell;
import project.models.Model;
import project.models.Number;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;
import javax.swing.*;

import project.models.Status;

/**
 * @author Alexander Naumov on 28.10.2017
 * @version 1.0.0
 */

public class MyMouseListener implements MouseListener {

    private final int x;
    private final int y;
    private Model model;
    private final int height;
    private final int width;
    private final JButton[][] buttons;
    private final Set<Cell> cells;
    private final ImageIcon accentuated = new ImageIcon("images\\accentuated.png");
    private final ImageIcon def = new ImageIcon("images\\default.png");
    private final ImageIcon mine = new ImageIcon("images\\mine.png");
    private final ImageIcon empty = new ImageIcon("images\\empty.png");
    private final ImageIcon one = new ImageIcon("images\\1.png");
    private final ImageIcon two = new ImageIcon("images\\2.png");
    private final ImageIcon three = new ImageIcon("images\\3.png");
    private final ImageIcon four = new ImageIcon("images\\4.png");
    private final ImageIcon five = new ImageIcon("images\\5.png");
    private final ImageIcon six = new ImageIcon("images\\6.png");
    private final ImageIcon seven = new ImageIcon("images\\7.png");
    private final ImageIcon eight = new ImageIcon("images\\8.png");

    public MyMouseListener(Model model, int x, int y, int n, int height, int width, JButton[][] buttons) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.buttons = buttons;
        this.model = model;
        cells = Model.getModel(n, x, y).getCells();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        openButton(x, y);
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
        for (Cell cell : cells) {
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
                        setNumber(buttons[x][y], value);
                        break;
                    case MINE:
                        buttons[x][y].setEnabled(false);
                        buttons[x][y].setDisabledIcon(mine);
                        gameOver();
                }
            }
        }
    }

    private void gameOver() {

    }
}
