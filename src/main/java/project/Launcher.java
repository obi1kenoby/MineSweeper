package project;

import java.awt.EventQueue;
import project.controller.GameController;

/**
 * @author Alexander Naumov on 24.10.2017
 * @version 1.0.0
 */

public class Launcher {
    public static void main(String[] args){
        EventQueue.invokeLater(() ->{
            GameController controller = new GameController(9);
            controller.play();
        });
    }
}
