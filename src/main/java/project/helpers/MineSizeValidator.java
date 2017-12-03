package project.helpers;


import project.view.SettingsFrame;

import javax.swing.*;
import java.awt.*;

/**
 * @author Alexander Naumov on 26.10.2017
 * @version 1.0.0
 */

public class MineSizeValidator extends InputVerifier {

    private JLabel label;

    public MineSizeValidator(JLabel label) {
        this.label = label;
    }

    @Override
    public boolean verify(JComponent input) {
        SettingsFrame settingsFrame = SettingsFrame.getSettingFrame();
        if (((JTextField) input).getText().equals("")) {
            return true;
        }
        int[] range = getRange(settingsFrame.getMinesLabel().getText());
        int value = Integer.parseInt(((JTextField) input).getText());
        if (value > range[0] && value < range[1]) {
            return true;
        }

        label.setForeground(Color.red);
        return false;
    }

    private int[] getRange(String string) {
        String substring = string.substring(6, string.length() - 1);
        String[] array = substring.split("-");
        int min = Integer.parseInt(array[0]);
        int max = Integer.parseInt(array[1]);
        return new int[]{min, max};
    }
}
