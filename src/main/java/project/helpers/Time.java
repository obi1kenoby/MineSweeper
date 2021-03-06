package project.helpers;

import javax.swing.*;

/**
 * @author Alexander Naumov on 26.10.2017
 * @version 1.0.0
 */

public class Time implements Runnable {

    private final JLabel label;

    public Time(JLabel label) {
        this.label = label;
    }

    @Override
    public void run() {
        String string = label.getText();
        String[] array = string.split(":");
        int seconds = Integer.parseInt(array[1]);
        int minutes = Integer.parseInt(array[0]);
        try {
            while (true) {
                seconds++;
                Thread.sleep(1000);
                if (seconds == 60) {
                    minutes++;
                    if (minutes == 60) {
                        minutes = 0;
                    }
                    seconds = 0;
                }
                if (minutes < 10) {
                    if (seconds < 10) {
                        label.setText("0" + minutes + ":0" + seconds);
                    } else {
                        label.setText("0" + minutes + ":" + seconds);
                    }
                } else {
                    if (seconds < 10) {
                        label.setText(minutes + ":0" + seconds);
                    } else {
                        label.setText(minutes + ":" + seconds);
                    }
                }
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}