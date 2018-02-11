package project.view;

import project.controller.GameController;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Alexander Naumov on 19.11.2017.
 * @version 1.0.0
 */

public class WinFrame extends JDialog {

    private JLabel time;
    private JLabel date;
    private static WinFrame winFrame;
    private GameController gameController;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    private WinFrame(){

        JPanel rootPanel = new JPanel(new BorderLayout());
        JPanel south = new JPanel(new GridLayout(1, 2, 25, 1));
        south.setBorder(new EmptyBorder(10, 20, 10, 20));
        JButton newGame = new JButton("Новая игра");
        JButton exit = new JButton("Выйти");
        newGame.setFocusPainted(false);
        exit.setFocusPainted(false);
        JPanel north = new JPanel();
        north.setBorder(new EmptyBorder(10,10,10,10));
        JLabel northLabel = new JLabel("Поздравляем, вы выиграли!", SwingConstants.CENTER);
        JPanel center = new JPanel(new GridLayout(1, 2));
        center.setBorder(new EmptyBorder(1,35,5,35));
        JPanel timePanel = new JPanel();
        timePanel.setPreferredSize(new Dimension(100, 10));
        JPanel datePanel = new JPanel();
        datePanel.setPreferredSize(new Dimension(100, 10));
        JLabel timeLabel = new JLabel("Время: ");
        time = new JLabel();
        time.setForeground(new Color(20, 135, 0));
        timePanel.add(timeLabel);
        timePanel.add(time);
        JLabel dateLabel = new JLabel("Дата: ");
        date = new JLabel();
        date.setForeground(new Color(20, 135, 0));
        datePanel.add(dateLabel);
        datePanel.add(date);

        center.add(timePanel);
        center.add(datePanel);
        north.add(northLabel);
        south.add(newGame);
        south.add(exit);
        rootPanel.add(center, "Center");
        rootPanel.add(south, "South");
        rootPanel.add(north, "North");
        add(rootPanel);

        setModalExclusionType(Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
        setTitle("Игра выиграна.");
        pack();
        setResizable(false);
        setLocationRelativeTo(null);

        newGame.addActionListener(e ->{
            gameController = GameController.gameController();
            gameController.newGame();
            visibleControl();
        });

        exit.addActionListener(e -> System.exit(0));
    }

    public void visibleControl() {
        if (isVisible()) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

    public void setTime(String t){
        time.setText(t);
    }

    public void setDate(Date d){
        date.setText(sdf.format(d));
    }

    public static WinFrame getWinFrame(){
        if (winFrame == null){
            winFrame = new WinFrame();
        }
        return winFrame;
    }
}