package project;

import java.awt.EventQueue;
import java.io.IOException;
import project.controller.GameController;

/**
 * @author Alexander Naumov on 24.10.2017
 * @version 1.0.0
 */

public class Laucher {
    public static void main(String[] args){
        EventQueue.invokeLater(() ->{
            GameController controller = new GameController(9);
            try {
                controller.setCells();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
