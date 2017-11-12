package project.view;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class GameOverFrame extends JDialog {

    private JPanel rootPanel;
    private JPanel buttonPanel;
    private JButton exit;
    private JButton restart;
    private JButton newGame;

    public GameOverFrame() {
        super();


        rootPanel = new JPanel(new BorderLayout());
        GridLayout gridLayout = new GridLayout(1, 3);
        gridLayout.setHgap(10);
        buttonPanel = new JPanel(gridLayout);
        buttonPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        rootPanel.add(buttonPanel, BorderLayout.SOUTH);
        exit = new JButton("Выйти");
        restart = new JButton("Переиграть");
        newGame = new JButton("Новая Игра");
        buttonPanel.add(exit);
        buttonPanel.add(restart);
        buttonPanel.add(newGame);



        String headText = "К сожалению Вы проиграли. В следующий раз вам повезет\n больше!";
        JLabel head = new JLabel(headText, SwingConstants.CENTER);
        JPanel north = new JPanel();
        north.setBorder(new EmptyBorder(15, 10, 10, 10));
        north.add(head);
        rootPanel.add(north, BorderLayout.NORTH);


        add(rootPanel);
        setSize(400, 225);
        setTitle("Игра окончена");
        setVisible(true);
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new GameOverFrame();
    }
}
