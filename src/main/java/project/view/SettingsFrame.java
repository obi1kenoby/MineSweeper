package project.view;


import project.controller.GameController;
import project.helpers.FieldSizeValidator;
import project.level.Level;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

/**
 * @author Alexander Naumov on 26.10.2017
 * @version 1.0.0
 */

public class SettingsFrame extends JDialog {

    private JRadioButton easyButton;
    private JRadioButton mediumButton;
    private JRadioButton hardButton;
    private JRadioButton specButton;
    private GameController gameController;
    private JTextField heightField;
    private JTextField widthField;
    private JTextField minesField;
    private JLabel heightLabel;
    private JLabel widthLabel;
    private JLabel minesLabel;
    private Level level;
    private static SettingsFrame settingsFrame;

    private SettingsFrame() {
        super();

        JPanel root = new JPanel(new BorderLayout());
        JPanel center = new JPanel(new GridLayout(1, 2));
        JPanel levelPanel = new JPanel(new GridLayout(3, 1));
        JPanel easy = new JPanel(new BorderLayout());
        JPanel medium = new JPanel(new BorderLayout());
        JPanel prof = new JPanel(new BorderLayout());
        JPanel special = new JPanel(new GridLayout(4, 1));
        special.setSize(new Dimension(200, 100));

        JPanel heightPanel = new JPanel();
        JPanel widthPanel = new JPanel();
        JPanel minePanel = new JPanel();

        heightLabel = new JLabel("Высота (9-24)");
        widthLabel = new JLabel("Ширина (9-30)");
        minesLabel = new JLabel("Мины (10-590)");

        heightField = new JTextField(5);
        heightField.setInputVerifier(new FieldSizeValidator(heightLabel));
        widthField = new JTextField(5);
        widthField.setInputVerifier(new FieldSizeValidator(widthLabel));
        minesField = new JTextField(5);
        //minesField.setInputVerifier(new FieldSizeValidator(minesLabel));

        heightField.setEnabled(false);
        widthField.setEnabled(false);
        minesField.setEnabled(false);
        heightLabel.setEnabled(false);
        widthLabel.setEnabled(false);
        minesLabel.setEnabled(false);

        heightPanel.add(heightLabel);
        heightPanel.add(heightField);
        widthPanel.add(widthLabel);
        widthPanel.add(widthField);
        minePanel.add(minesLabel);
        minePanel.add(minesField);

        ButtonGroup group = new ButtonGroup();
        easyButton = new JRadioButton("<html>Новичок<br>10 мин<br>поле 9 х 9</html>");
        easyButton.setFocusPainted(false);
        mediumButton = new JRadioButton("<html>Любитель<br>40 мин<br>поле 16 х 16</html>");
        mediumButton.setFocusPainted(false);
        hardButton = new JRadioButton("<html>Профессилнал<br>99 мин<br>поле 30 х 16</html>");
        hardButton.setFocusPainted(false);
        specButton = new JRadioButton("Особый");
        specButton.setFocusPainted(false);

        easyButton.addActionListener(e -> {
            level = Level.EASY;
            hideSpecialMode();
        });
        mediumButton.addActionListener(e -> {
            level = Level.MEDIUM;
            hideSpecialMode();
        });
        hardButton.addActionListener(e -> {
            level = Level.HARD;
            hideSpecialMode();
        });
        specButton.addActionListener(e -> {
            level = Level.SPECIAL;
            heightLabel.setForeground(Color.black);
            widthLabel.setForeground(Color.black);
            minesLabel.setForeground(Color.black);
            heightField.setEnabled(true);
            widthField.setEnabled(true);
            minesField.setEnabled(true);
            heightLabel.setEnabled(true);
            widthLabel.setEnabled(true);
            minesLabel.setEnabled(true);
        });

        group.add(easyButton);
        group.add(mediumButton);
        group.add(hardButton);
        group.add(specButton);

        center.setBorder(new TitledBorder("Уровень"));
        JPanel south = new JPanel(new GridLayout(1, 2));
        south.setBorder(new EmptyBorder(10, 10, 10, 10));

        JButton ok = new JButton("OK");
        JButton close = new JButton("Отмена");
        close.setFocusPainted(false);
        ok.setFocusPainted(false);

        ok.addActionListener(e -> {
            if (heightLabel.getForeground().equals(Color.black) && widthLabel.getForeground().equals(Color.black) && minesLabel.getForeground().equals(Color.black)) {
                if (level.equals(Level.SPECIAL)) {
                    try {
                        int h = Integer.parseInt(heightField.getText());
                        int w = Integer.parseInt(widthField.getText());
                        gameController.setSpecialParams(h, w);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Неверные или неполные параметры!");
                    }
                }
                gameController.setLevel(level);
                gameController.newGame();
                GameFrame gameFrame = GameFrame.getFrame();
                visibleControl();
                gameFrame.repaint();
                gameFrame.pack();
            } else {
                JOptionPane.showMessageDialog(null, "Неверные или неполные параметры!");
            }
        });
        close.addActionListener(e -> visibleControl());

        easy.add(easyButton, "Center");
        medium.add(mediumButton, "Center");
        prof.add(hardButton, "Center");

        levelPanel.add(easy);
        levelPanel.add(medium);
        levelPanel.add(prof);

        special.add(specButton);
        special.add(heightPanel);
        special.add(widthPanel);
        special.add(minePanel);

        center.add(levelPanel);
        center.add(special);
        south.add(new JPanel());

        south.add(ok);
        south.add(close);

        root.add(new JPanel(), "East");
        root.add(new JPanel(), "West");
        root.add(new JPanel(), "North");
        root.add(center, "Center");
        root.add(south, "South");

        add(root);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(320, 275);
        setTitle("Параметры");
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void hideSpecialMode() {
        heightField.setEnabled(false);
        widthField.setEnabled(false);
        minesField.setEnabled(false);
        heightLabel.setEnabled(false);
        widthLabel.setEnabled(false);
        minesLabel.setEnabled(false);
    }

    public void visibleControl() {
        if (isVisible()) {
            setVisible(false);
        } else {
            setVisible(true);
        }
    }

    public static SettingsFrame getSettingFrame() {
        if (settingsFrame == null) {
            settingsFrame = new SettingsFrame();
        }
        return settingsFrame;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public JTextField getMinesField() {
        return minesField;
    }

    public JLabel getHeightLabel() {
        return heightLabel;
    }

    public JLabel getWidthLabel() {
        return widthLabel;
    }

    public JLabel getMinesLabel() {
        return minesLabel;
    }

    public JTextField getHeightField() {
        return heightField;
    }

    public JTextField getWidthField() {
        return widthField;
    }

    public JRadioButton getEasyButton() {
        return easyButton;
    }

    public JRadioButton getMediumButton() {
        return mediumButton;
    }

    public JRadioButton getHardButton() {
        return hardButton;
    }

    public JRadioButton getSpecButton() {
        return specButton;
    }
}