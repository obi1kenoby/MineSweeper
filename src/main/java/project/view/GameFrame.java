package project.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Alexander Naumov on 26.10.2017
 * @version 1.0.0
 */

public final class GameFrame extends JFrame {

    private JPanel buttonPanel;
    private JPanel southPanel;
    private JPanel rootPanel;
    private JLabel timeLabel;
    private JLabel flagLabel;
    private JButton[][] buttons;
    private final int n;
    private final ImageIcon image = new ImageIcon("images\\default.png");

    public GameFrame(int n) {
        this.n = n;
        init();
    }

    public void init() {
        timeLabel = new JLabel();
        flagLabel = new JLabel();
        rootPanel = new JPanel(new BorderLayout(20, 20));
        southPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel = new JPanel(new GridLayout(n, n));
        if (n == 16) {
            buttonPanel.setPreferredSize(new Dimension(420, 420));
            timeLabel.setPreferredSize(new Dimension(175, 40));
            flagLabel.setPreferredSize(new Dimension(175, 40));
        } else {
            buttonPanel.setPreferredSize(new Dimension(200, 200));
            timeLabel.setPreferredSize(new Dimension(100, 20));
            flagLabel.setPreferredSize(new Dimension(100, 20));
        }
        buttons = new JButton[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setIcon(image);
                buttonPanel.add(buttons[i][j]);
            }
        }

        southPanel.add(timeLabel);
        southPanel.add(flagLabel);
        rootPanel.add(new JPanel(), BorderLayout.EAST);
        rootPanel.add(new JPanel(), BorderLayout.WEST);
        rootPanel.add(new JPanel(), BorderLayout.NORTH);
        rootPanel.add(buttonPanel, BorderLayout.CENTER);
        rootPanel.add(southPanel, BorderLayout.SOUTH);
        add(rootPanel);

        pack();
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JButton[][] getButtons() {
        return buttons;
    }

    public JLabel getTimeLabel() {
        return timeLabel;
    }

    public JLabel getFlagLabel() {
        return flagLabel;
    }
}
