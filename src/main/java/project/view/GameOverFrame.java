package project.view;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

/**
 * @author Alexander Naumov on 26.10.2017
 * @version 1.0.0
 */

public class GameOverFrame extends ChildFrame {

    private JLabel cal;
    private JLabel timer;
    private static GameOverFrame gameOverFrame;
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");

    private GameOverFrame() {
        super();
        GridLayout gridLayout = new GridLayout(1, 3);
        gridLayout.setHgap(10);

        JPanel rootPanel = new JPanel(new BorderLayout());
        JPanel buttonPanel = new JPanel(gridLayout);
        GridLayout statLayout = new GridLayout(1, 3);
        JPanel statPanel = new JPanel(statLayout);
        statPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));

        JButton exit = new JButton("Выйти");
        JButton restart = new JButton("Переиграть");
        JButton newGame = new JButton("Новая Игра");
        exit.setFocusPainted(false);
        restart.setFocusPainted(false);
        newGame.setFocusPainted(false);

        newGame.addActionListener(e ->{
            GameFrame gameFrame = GameFrame.getFrame();
            gameFrame.setEnabled(true);
            visibleControl();

            gameFrame.getMainController().newGame();
            gameFrame.repaint();
        });

        restart.addActionListener(e ->{
            GameFrame gameFrame = GameFrame.getFrame();
            gameFrame.setEnabled(true);
            visibleControl();

            gameFrame.getMainController().restartGame();
            gameFrame.repaint();
        });

        exit.addActionListener(e -> System.exit(0));

        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        rootPanel.add(buttonPanel, "South");
        rootPanel.add(statPanel, "Center");
        rootPanel.add(new JPanel(), "West");
        rootPanel.add(new JPanel(), "East");

        JLabel timeLabel = new JLabel("Время:");
        timer = new JLabel();
        timer.setForeground(Color.red);
        JPanel timePanel = new JPanel();
        timePanel.add(timeLabel);
        timePanel.add(timer);
        statPanel.add(timePanel);

        JPanel datePanel = new JPanel();
        JLabel dateLabel = new JLabel("Дата:");
        cal = new JLabel();
        cal.setForeground(Color.red);
        datePanel.add(dateLabel);
        datePanel.add(cal);
        statPanel.add(datePanel);

        buttonPanel.add(exit);
        buttonPanel.add(restart);
        buttonPanel.add(newGame);

        JPanel titleLabel = new JPanel(new GridLayout(2,1));
        JLabel title1 = new JLabel("К сожалению Вы проиграли. В следующий раз вам повезет", SwingConstants.CENTER);
        JLabel title2 = new JLabel("больше!", SwingConstants.CENTER);
        titleLabel.setBorder(new EmptyBorder(15, 10, 10, 10));
        titleLabel.add(title1);
        titleLabel.add(title2);
        rootPanel.add(titleLabel, BorderLayout.NORTH);

        add(rootPanel);
        setResizable(false);
        pack();
        setTitle("Игра окончена");
        setModalExclusionType(Dialog.ModalExclusionType.TOOLKIT_EXCLUDE);
    }

    public void setTime(String t){
        timer.setText(t);
    }

    public void setDate(Date d){
        cal.setText(sdf.format(d));
    }

    public static GameOverFrame getGameOverFrame(){
        if (gameOverFrame == null){
            gameOverFrame = new GameOverFrame();
        }
        return gameOverFrame;
    }
}
