package project.controller;

import project.models.Cell;
import project.models.Number;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import project.models.Status;

/**
 * @author Alexander Naumov on 28.10.2017
 * @version 1.0.0
 */

public class MyMouseListener implements MouseListener {

    private final int x;
    private final int y;
    private final JButton button;
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

    public MyMouseListener(JButton button, Set<Cell> cells, int x, int y) {
        this.x = x;
        this.y = y;
        this.button = button;
        this.cells = cells;
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        open(x,y);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        button.setIcon(accentuated);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        button.setIcon(def);
    }

    private void setNumber(JButton button, Cell cell) {
        switch (((Number) cell).getValue()) {
            case 1:
                button.setEnabled(false);
                button.setDisabledIcon(one);
                break;
            case 2:
                button.setEnabled(false);
                button.setDisabledIcon(two);
                break;
            case 3:
                button.setEnabled(false);
                button.setDisabledIcon(three);
                break;
            case 4:
                button.setEnabled(false);
                button.setDisabledIcon(four);
                break;
            case 5:
                button.setEnabled(false);
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

    private void open(int x, int y) {
        for(Cell cell: cells){
            if (cell.getX() == x +1 && cell.getY() == y +1) {
                Status status = cell.getStatus();
                if (status.equals(Status.EMPTY)) {
                    button.setEnabled(false);
                    button.setDisabledIcon(empty);
                }
                else if (status.equals(Status.NUMBER)){
                    setNumber(button, cell);
                }
                else{
                    button.setEnabled(false);
                    button.setDisabledIcon(mine);
                }
            }
        }
    }
}
