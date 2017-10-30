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
        int minuts = 0;
        while (true) {
            seconds++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (seconds == 60) {
                minuts++;
                if (minuts == 60) {
                    minuts = 0;
                }
                seconds = 0;
            }
            if (minuts < 10) {
                if (seconds < 10) {
                   label.setText("0" + minuts + ":0" + seconds);
                } else {
                    label.setText("0" + minuts + ":" + seconds);
                }
            } else {
                if (seconds < 10) {
                    label.setText(minuts + ":0" + seconds);
                } else {
                    label.setText(minuts + ":" + seconds);
                }
            }
        }
    }
}
