package project.helpers;


import project.models.Cell;
import project.models.Model;
import project.models.Status;
import project.view.GameFrame;
import project.view.GameOverFrame;

import javax.swing.*;


public class GameOver implements Runnable {

    private final ImageIcon mine = new ImageIcon("images/mine.png");
    private JButton[][] buttons;
    private Model model;

    public GameOver(Model model, JButton[][] buttons) {
        this.model = model;
        this.buttons = buttons;
    }

    @Override
    public void run() {
        GameFrame gameFrame = GameFrame.getFrame();
        gameFrame.setEnabled(false);
        try {
            for (Cell cell : model.getCells()) {
                if (cell.getStatus().equals(Status.MINE)) {
                    Thread.sleep(100);
                    buttons[cell.getY() - 1][cell.getX() - 1].setIcon(mine);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        GameOverFrame gameOverFrame = GameOverFrame.getGameOverFrame();
        gameOverFrame.visibleControl();
    }
}
