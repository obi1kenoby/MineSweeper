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
        int seconds = 0;
        int minutes = 0;
        while (true) {
            seconds++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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
    }
}
