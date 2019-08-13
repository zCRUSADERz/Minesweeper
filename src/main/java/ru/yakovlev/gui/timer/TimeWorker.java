package ru.yakovlev.gui.timer;

import javax.swing.*;

/**
 * Управляет текстовым полем показывающим время в секундах после начала игры.
 *
 * @since 0.1
 */
public class TimeWorker implements Runnable {
    private final JTextField timeField;

    public TimeWorker(final JTextField timeField) {
        this.timeField = timeField;
    }

    @Override
    public final void run() {
        this.timeField.setText("000");
        final long started = System.currentTimeMillis();
        while (!Thread.interrupted()) {
            final long passed = System.currentTimeMillis() - started;
            final long time = passed / 1000;
            if (time > 999) {
                this.timeField.setText("999");
                break;
            } else {
                this.timeField.setText(String.format("%03d", time));
                try {
                    Thread.sleep(1000 - (passed % 1000));
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}
