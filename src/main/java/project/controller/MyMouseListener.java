package project.controller;

import project.models.Cell;
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
    private final int n;
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

    public MyMouseListener(Set<Cell> cells, int x, int y, int n, JButton[][] buttons) {
        this.x = x;
        this.y = y;
        this.n = n;
        this.cells = cells;
        this.buttons = buttons;
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
        for (Cell cell : cells) {
            if (cell.getX() == (x + 1) && cell.getY() == (y + 1)) {
                Status status = cell.getStatus();
                switch (status) {
                    case EMPTY:     //если пустая
                        buttons[y][x].setEnabled(false);
                        buttons[y][x].setDisabledIcon(empty);

                        if (x == 0) {
                            openButton(x + 1, y);
                            if (y == 0) {
                                openButton(x, y + 1);
                                openButton(x + 1, y + 1);
                            } else if (0 < y && y < n -1) {
                                openButton(x, y - 1);
                                openButton(x, y + 1);
                                openButton(x + 1, y - 1);
                                openButton(x + 1, y + 1);
                            } else {
                                openButton(x, y - 1);
                                openButton(x + 1, y - 1);
                            }
                        } else if (0 < x && x < n - 1) {
                            openButton(x + 1, y);
                            openButton(x - 1, y);
                            if (y == 0) {
                                openButton(x, y + 1);
                                openButton(x - 1, y + 1);
                                openButton(x + 1, y + 1);
                            } else if (0 < y && y < n - 1) {
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
                            } else if (0 < y && y < n - 1) {
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
                }
            }
        }
    }
}
