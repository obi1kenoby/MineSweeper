package project.view;

import project.controller.GameController;
import project.level.Level;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * @author Alexander Naumov on 26.10.2017
 * @version 1.0.0
 */

public final class GameFrame extends JFrame {

    private GameController mainController;
    private SettingsFrame settingsFrame;
    private static GameFrame frame;
    private JLabel timeLabel;
    private JButton[][] buttons;
    private JPanel buttonPanel;
    private JPanel rootPanel;
    private JPanel southPanel;
    private JMenuItem close;
    private JMenuItem newGame;
    private JMenuItem statistic;
    private JMenuItem settings;
    private int heightField;
    private int widthField;
    private Level level;
    private final ImageIcon titleIcon = new ImageIcon("images\\title.png");

    private GameFrame(Level level) {
        this.level = level;
        switch (level) {
            case EASY:
                widthField = 9;
                heightField = 9;
                break;
            case MEDIUM:
                widthField = 16;
                heightField = 16;
                break;
            case HARD:
                widthField = 30;
                heightField = 16;
                break;
        }
        init();
        mainController = new GameController(timeLabel, rootPanel, buttonPanel, buttons, level);
        settingsFrame = SettingsFrame.getSettingFrame();
        settingsFrame.setGameController(mainController);
    }

    private void init() {
        timeLabel = new JLabel();
        rootPanel = new JPanel(new BorderLayout(20, 20));
        southPanel = new JPanel(new GridLayout(1, 2));
        GridLayout layout = new GridLayout(heightField, widthField,1,1);
        buttonPanel = new JPanel(layout);
        buttonPanel.setBackground(Color.black);

        buttonPanel.setPreferredSize(new Dimension(200, 200));
        timeLabel.setPreferredSize(new Dimension(200, 20));
        timeLabel.setText("00:00");
        buttons = new JButton[widthField][heightField];
        for (int x = 0; x < widthField; x++) {
            for (int y = 0; y < heightField; y++) {
                buttons[x][y] = new JButton();
                buttons[x][y].setFocusPainted(false);
                buttonPanel.add(buttons[x][y]);
            }
        }

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu game = new JMenu("Игра");
        newGame = new JMenuItem("Новая игра");
        settings = new JMenuItem("Параметры");
        close = new JMenuItem("Выйти");
        JMenuItem info = new JMenuItem("О программе");
        JMenu about = new JMenu("Справка");
        statistic = new JMenuItem("Статистика");
        game.add(newGame);
        game.add(statistic);
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

        newGame.addActionListener(e -> {
            mainController.newGame();
            repaint();
        });
        KeyStroke f2 = KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0);
        newGame.setAccelerator(f2);


        settings.addActionListener(e -> settingsFrame.visibleControl());
        KeyStroke f5 = KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0);
        settings.setAccelerator(f5);

        KeyStroke f4 = KeyStroke.getKeyStroke(KeyEvent.VK_F4, 0);
        statistic.setAccelerator(f4);


        close.addActionListener(e -> System.exit(0));
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public JMenuItem getClose() {
        return close;
    }

    public JMenuItem getNewGame() {
        return newGame;
    }

    public Level getLevel() {
        return level;
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

    public static GameFrame getFrame(Level level) {
        if (frame == null) {
            frame = new GameFrame(level);
        }
        return frame;
    }

    public static GameFrame getFrame() {
        if (frame == null) {
            frame = new GameFrame(Level.EASY);
        }
        return frame;
    }
}