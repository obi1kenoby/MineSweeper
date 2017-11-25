package project;

import project.level.Level;
import project.view.GameFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @author Alexander Naumov on 24.10.2017
 * @version 1.0.0
 */

public class Launcher {
    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        EventQueue.invokeLater(() -> GameFrame.getFrame(Level.EASY));
        System.out.println(System.getProperty("os.name").toLowerCase());
    }
}
