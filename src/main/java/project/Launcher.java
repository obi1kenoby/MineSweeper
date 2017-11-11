package project;

import java.awt.*;
import java.util.Random;

import project.controller.GameController;
import project.models.Cell;
import project.models.Model;
import project.models.Number;
import project.view.GameFrame;

import javax.swing.*;

/**
 * @author Alexander Naumov on 24.10.2017
 * @version 1.0.0
 */

public class Launcher {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> new GameController(9).play());
    }
}
