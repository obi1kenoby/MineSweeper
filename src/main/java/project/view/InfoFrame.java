package project.view;


import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class InfoFrame extends JDialog {

    private final ImageIcon image = new ImageIcon("images\\info.png");
    private static InfoFrame infoFrame;

    private InfoFrame(){
        Font myFont = new Font("Arial", Font.BOLD, 12);

        JLabel label = new JLabel();
        label.setIcon(image);
        JPanel root = new JPanel(new BorderLayout());
        JPanel center = new JPanel();
        JPanel textPanel = new JPanel(new GridLayout(3,1));
        JPanel south = new JPanel();
        JPanel north = new JPanel();
        north.setBorder(new EmptyBorder(20, 88, 5, 88));
        JLabel name = new JLabel("\u00a9 Minesweeper v 1.0.1", SwingConstants.CENTER);
        name.setPreferredSize(new Dimension(200,25));
        JLabel author = new JLabel("author: Alexander Naumov", SwingConstants.CENTER);
        author.setPreferredSize(new Dimension(200,25));
        JLabel link = new JLabel("<html><a href=\"https://vk.com/vomuana\">https://vk.com/vomuana</a></html>", SwingConstants.CENTER);
        link.setFont(myFont);
        link.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://vk.com/vomuana"));
                } catch (URISyntaxException | IOException ex) {
                    System.out.println("can't open url in browser.");
                }
            }
        });
        textPanel.add(name);
        textPanel.add(author);
        textPanel.add(link);
        name.setFont(myFont);

        south.setBorder(new EmptyBorder(1, 120, 1, 120));
        JButton button = new JButton("Ok");
        button.addActionListener(e -> setVisible(false));
        button.setFocusPainted(false);

        center.add(textPanel);
        center.add(textPanel);
        north.add(label);
        south.add(button);
        root.add(center, "Center");
        root.add(south, "South");
        root.add(north, "North");
        add(root);

        setTitle("О программе \"Minesweeper\"");
        setSize(300, 300);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static InfoFrame getInfoFrame(){
        if (infoFrame == null){
            infoFrame = new InfoFrame();
        }
        return infoFrame;
    }
}
