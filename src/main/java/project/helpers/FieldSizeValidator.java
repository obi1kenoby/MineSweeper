package project.helpers;


import project.view.SettingsFrame;

import javax.swing.*;
import java.awt.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FieldSizeValidator extends InputVerifier {

    private int mines = 590;
    private JLabel label;

    public FieldSizeValidator(JLabel label) {
        this.label = label;

    }

    private boolean regExpValid(JComponent input) {
        SettingsFrame settingsFrame = SettingsFrame.getSettingFrame();
        String expression = "";
        switch (label.getText()) {
            case "Высота (9-24)":
                expression = "(9|1[0-9]|2[0-4])";
                break;
            case "Ширина (9-30)":
                expression = "(9|1[0-9]|2[0-9]|30)";
                break;
        }
        if (!settingsFrame.getHeightField().getText().equals("") && !settingsFrame.getWidthField().getText().equals("")){
            int height = Integer.parseInt(settingsFrame.getHeightField().getText());
            int width = Integer.parseInt(settingsFrame.getWidthField().getText());
            mines = (int)(height * width  * 0.82);
        }
        settingsFrame.getMinesLabel().setText("Мины (10-" + mines +")");
        Pattern p = Pattern.compile(expression);
        Matcher m = p.matcher(((JTextField) input).getText());
        return m.matches();
    }

    @Override
    public boolean verify(JComponent input) {
        SettingsFrame settingsFrame = SettingsFrame.getSettingFrame();
        if (regExpValid(input)) {
            settingsFrame.getHeightLabel().setForeground(Color.black);
            settingsFrame.getWidthLabel().setForeground(Color.black);
            settingsFrame.getMinesLabel().setForeground(Color.black);
            return true;
        }
        label.setForeground(Color.red);
        settingsFrame.getMinesLabel().setText("Мины (10-590)");
        return false;
    }
}
