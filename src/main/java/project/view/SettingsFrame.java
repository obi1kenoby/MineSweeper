package project.view;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class SettingsFrame extends JDialog {

    private JTextField height;
    private JTextField width;
    private JTextField mines;
    private JLabel heightLabel;
    private JLabel widthLabel;
    private JLabel minesLabel;
    private JRadioButton specButton;

    public SettingsFrame() {
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

        height = new JTextField(5);
        width = new JTextField(5);
        mines = new JTextField(5);

        heightLabel = new JLabel("Высота (9-24)");
        widthLabel = new JLabel("Ширина (9-30)");
        minesLabel = new JLabel("Мины (10-688)");

        height.setEnabled(false);
        width.setEnabled(false);
        mines.setEnabled(false);
        heightLabel.setEnabled(false);
        widthLabel.setEnabled(false);
        minesLabel.setEnabled(false);

        heightPanel.add(heightLabel);
        heightPanel.add(height);
        widthPanel.add(widthLabel);
        widthPanel.add(width);
        minePanel.add(minesLabel);
        minePanel.add(mines);

        ButtonGroup group = new ButtonGroup();
        JRadioButton easyButton = new JRadioButton("<html>Новичок<br>10 мин<br>поле 9 х 9</html>", true);
        easyButton.setFocusPainted(false);
        JRadioButton mediumButton = new JRadioButton("<html>Любитель<br>40 мин<br>поле 16 х 16</html>", false);
        mediumButton.setFocusPainted(false);
        JRadioButton hardButton = new JRadioButton("<html>Профессилнал<br>99 мин<br>поле 30 х 16</html>", false);
        hardButton.setFocusPainted(false);
        specButton = new JRadioButton("Особый", false);
        specButton.setFocusPainted(false);

        easyButton.addActionListener(e -> hideSpecialMode());
        mediumButton.addActionListener(e -> hideSpecialMode());
        hardButton.addActionListener(e -> hideSpecialMode());
        specButton.addActionListener(e -> {
            height.setEnabled(true);
            width.setEnabled(true);
            mines.setEnabled(true);
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
        setVisible(true);
    }

    private void hideSpecialMode() {
        height.setEnabled(false);
        width.setEnabled(false);
        mines.setEnabled(false);
        heightLabel.setEnabled(false);
        widthLabel.setEnabled(false);
        minesLabel.setEnabled(false);
    }

    public void visobleControll(){
        if (isVisible()){
            setVisible(false);
        }
        else {
            setVisible(true);
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new SettingsFrame();
    }
}