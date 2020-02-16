package project.view;


import javax.swing.*;

/**
 * @author Alexander Naumov.
 */
public abstract class ChildFrame extends JDialog {

    private void setPosition(int height, int width) {
        int pHeight = GameFrame.getFrame().getHeight();
        int pWidth = GameFrame.getFrame().getWidth();
        int x = GameFrame.getFrame().getX();
        int y = GameFrame.getFrame().getY();
        setLocation((x + pWidth/2) - width/2, (y + pHeight/2) - height/2);
    }

    public void visibleControl() {
        if (isVisible()) {
            setVisible(false);
        } else {
            setPosition(getHeight(), getWidth());
            setVisible(true);
        }
    }
}
