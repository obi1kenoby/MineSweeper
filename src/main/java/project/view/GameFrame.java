package project.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * @author Alexander Naumov on 26.10.2017
 * @version 1.0.0
 */

public final class GameFrame extends JFrame {

    private static GameFrame frame;
    private JLabel timeLabel;
    private JButton[][] buttons;
    private JPanel buttonPanel;
    private JMenuItem close;
    private JMenuItem newGame;
    private final int heightField;
    private final int widthField;
    private final int n;
    private final ImageIcon titleIcon = new ImageIcon("images\\title.png");

    private GameFrame(int n) {
        this.n = n;
        if (n == 9){
            widthField = n;
            heightField = n;
        }else if (n == 16){
            widthField = n;
            heightField = n;
        }
        else {
            widthField = 30;
            heightField = 16;
        }
        init();
    }

    private void init() {
        timeLabel = new JLabel();
        JPanel rootPanel = new JPanel(new BorderLayout(20, 20));
        JPanel southPanel = new JPanel(new GridLayout(1, 2));
        GridLayout layout = new GridLayout(heightField, widthField);
        layout.setVgap(1);
        layout.setHgap(1);
        buttonPanel = new JPanel(layout);
        buttonPanel.setBackground(Color.black);
        Dimension field;
        if (widthField == 16) {
            field = new Dimension(420,420);
        } else if (widthField == 9){
            field = new Dimension(200, 200);
        }else {
            field = new Dimension(788, 420);
        }

        buttonPanel.setPreferredSize(field);
        timeLabel.setPreferredSize(new Dimension(200, 20));


        buttons = new JButton[heightField][widthField];
        for (int x = 0; x < heightField; x++) {
            for (int y = 0; y < widthField; y++) {
                buttons[x][y] = new JButton();
                buttons[x][y].setFocusPainted(false);
                buttonPanel.add(buttons[x][y]);
            }
        }

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu game = new JMenu("Game");
        newGame = new JMenuItem("Новая игра");
        JMenuItem settings = new JMenuItem("Параметры");
        close = new JMenuItem("Выйти");
        JMenuItem info = new JMenuItem("О программе");
        JMenu about = new JMenu("About");
        game.add(newGame);
        game.add(new JPopupMenu.Separator());
        game.add(settings);
        game.add(new JPopupMenu.Separator());
        game.add(close);

        about.add(info);
        menuBar.add(game);
        menuBar.add(about);

        southPanel.add(timeLabel);
        rootPanel.add(new JPanel(), BorderLayout.EAST);
        rootPanel.add(new JPanel(), BorderLayout.WEST);
        rootPanel.add(new JPanel(), BorderLayout.NORTH);
        rootPanel.add(buttonPanel, BorderLayout.CENTER);
        rootPanel.add(southPanel, BorderLayout.SOUTH);
        add(rootPanel);

        pack();
        setResizable(false);
        setTitle("Mines v.1.0.1");
        setIconImage(titleIcon.getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JMenuItem getClose() {
        return close;
    }

    public JMenuItem getNewGame() {
        return newGame;
    }

    public int getN() {
        return n;
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public JPanel getButtonPanel() {
        return buttonPanel;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public int getHeightField() {
        return heightField;
    }

    public int getWidthField() {
        return widthField;
    }

    public static GameFrame getFrame(int n){
        if (frame == null){
            frame = new GameFrame(n);
        }
        return frame;
    }
}