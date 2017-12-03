package project.helpers;


import project.controller.GameController;
import project.models.Cell;
import project.models.Model;
import project.models.Status;
import project.view.GameFrame;
import project.view.GameOverFrame;

import javax.swing.*;
import java.util.Date;

/**
 * @author Alexander Naumov on 19.11.2017.
 * @version 1.0.0
 */

public class GameOver implements Runnable {

    private final ImageIcon mine = new ImageIcon("src/main/resources/mine.png");
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
                    buttons[cell.getX() - 1][cell.getY() - 1].setIcon(mine);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        GameController gameController = GameController.gameController();
        String time = gameController.getTimeLabel().getText();
        GameOverFrame gameOverFrame = GameOverFrame.getGameOverFrame();
        gameOverFrame.setTime(time);
        gameOverFrame.setDate(new Date());
        gameOverFrame.visibleControl();
    }
}
