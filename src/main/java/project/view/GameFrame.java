package project.view;

import project.controller.GameController;
import project.level.Level;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.*;

/**
 * @author Alexander Naumov on 26.10.2017
 * @version 1.0.0
 */

public final class GameFrame extends JFrame {

    private GameController mainController;
    private SettingsFrame settingsFrame;
    private InfoFrame infoFrame;
    private static GameFrame frame;
    private JLabel timeLabel;
    private JLabel flagLabel;
    private JButton[][] buttons;
    private JPanel buttonPanel;
    private int heightField;
    private int widthField;
    private final ImageIcon titleIcon = new ImageIcon("images\\title.png");
    private final ImageIcon alarm = new ImageIcon("images\\alarm.png");
    private final ImageIcon flagImage = new ImageIcon("images\\flagicon.png");

    private GameFrame(Level level) {
        Level level1 = level;
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
        mainController = GameController.gameController(timeLabel, flagLabel, buttonPanel, buttons, level);
        settingsFrame = SettingsFrame.getSettingFrame();
        settingsFrame.setGameController(mainController);
        infoFrame = InfoFrame.getInfoFrame();
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
    }

    private void init() {
        Font myFont = new Font("Arial", Font.BOLD, 12);
        JPanel rootPanel = new JPanel(new BorderLayout());
        JPanel southPanel = new JPanel();

        GridLayout layout = new GridLayout(heightField, widthField,1,1);
        buttonPanel = new JPanel(layout);
        buttonPanel.setBackground(Color.black);

        buttonPanel.setPreferredSize(new Dimension(200, 200));
        timeLabel = new JLabel("00:00", SwingConstants.CENTER);
        timeLabel.setPreferredSize(new Dimension(40, 20));
        timeLabel.setOpaque(true);
        timeLabel.setForeground(Color.white);
        timeLabel.setBackground(new Color(60, 90, 255));
        timeLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        timeLabel.setFont(myFont);

        JLabel timeIcon = new JLabel();
        timeIcon.setPreferredSize(new Dimension(20, 20));
        timeIcon.setIcon(alarm);

        JLabel empty = new JLabel();
        empty.setPreferredSize(new Dimension(25, 20));

        flagLabel = new JLabel("0", SwingConstants.CENTER);
        flagLabel.setPreferredSize(new Dimension(40, 20));
        flagLabel.setOpaque(true);
        flagLabel.setForeground(Color.white);
        flagLabel.setBackground(new Color(60, 90, 255));
        flagLabel.setBorder(BorderFactory.createLineBorder(Color.black));
        flagLabel.setFont(myFont);

        JLabel flag = new JLabel();
        flag.setPreferredSize(new Dimension(20, 20));
        flag.setIcon(flagImage);

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
        JMenu about = new JMenu("Справка");
        JMenuItem newGame = new JMenuItem("Новая игра");
        JMenuItem settings = new JMenuItem("Параметры");
        JMenuItem close = new JMenuItem("Выйти");
        JMenuItem info = new JMenuItem("О программе");
        JMenuItem statistic = new JMenuItem("Статистика");
        game.add(newGame);
        game.add(statistic);
        game.add(settings);
        game.add(new JPopupMenu.Separator());
        game.add(close);

        about.add(info);
        menuBar.add(game);
        menuBar.add(about);

        southPanel.add(timeIcon);
        southPanel.add(timeLabel);
        southPanel.add(empty);
        southPanel.add(flagLabel);
        southPanel.add(flag);
        rootPanel.add(new JPanel(), "East");
        rootPanel.add(new JPanel(), "West");
        rootPanel.add(new JPanel(), "North");
        rootPanel.add(buttonPanel, "Center");
        rootPanel.add(southPanel, "South");
        add(rootPanel);

        setResizable(false);
        setTitle("Mines v.1.0.1");
        setIconImage(titleIcon.getImage());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

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

        KeyStroke f1 = KeyStroke.getKeyStroke(KeyEvent.VK_F1, 0);
        info.setAccelerator(f1);
        info.addActionListener(e -> infoFrame.setVisible(true));

        close.addActionListener((ActionEvent e) -> {

            Object[] options = {"Сохранить", "Не сохранять", "Отмена"};
            int dialogResult = JOptionPane.showOptionDialog(frame,
                    "Текущая игра не закончена. Что нужно сделать?",
                    "Выход из игры", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                    null, options, options[0]);
            if(dialogResult == JOptionPane.YES_OPTION) {
                mainController.storeGame();
                System.exit(0);
            }
            if (dialogResult == JOptionPane.NO_OPTION){
                System.exit(0);
            }
        });
    }

    public GameController getMainController() {
        return mainController;
    }

    public static GameFrame getFrame(Level level) {
        if (frame == null) {
            frame = new GameFrame(level);
        }
        return frame;
    }

    public static GameFrame getFrame() {
        return frame;
    }
}