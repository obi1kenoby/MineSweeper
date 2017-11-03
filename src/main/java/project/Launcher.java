package project;

import java.awt.EventQueue;
import project.controller.GameController;

import javax.swing.*;

/**
 * @author Alexander Naumov on 24.10.2017
 * @version 1.0.0
 */

public class Launcher {
    public static void main(String[] args){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        EventQueue.invokeLater(() ->{
            new GameController(9).play();
        });
    }
}
