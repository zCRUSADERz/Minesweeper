package ru.yakovlev.gui.timer;

import org.springframework.stereotype.Component;
import ru.yakovlev.InitializingComponent;
import ru.yakovlev.board.BoardProperties;
import ru.yakovlev.board.events.GameFinishedListener;
import ru.yakovlev.board.events.NewGameListener;

import javax.swing.*;
import java.awt.*;

/**
 * Текстовое поле с таймером, отсчитывающим время от начала игры в секундах.
 * При старте новой игры убивает старый поток, отсчитывающий время, и создает
 * новый.
 *
 * @since 0.1
 */
@Component
public class TimerField extends JTextField
    implements NewGameListener, GameFinishedListener, InitializingComponent
{
    private Thread worker = new Thread();

    public TimerField() {
        super("000", 3);
    }

    @Override
    public final void init() {
        this.setEditable(false);
        this.setFont(new Font("DigitalFont.TTF", Font.BOLD, 25));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.RED);
        this.setBorder(BorderFactory.createLoweredBevelBorder());
    }

    @Override
    public final void newGame(final BoardProperties properties) {
        this.worker.interrupt();
        final TimeWorker newWorker = new TimeWorker(this);
        this.worker = new Thread(newWorker);
        this.worker.start();
    }

    @Override
    public final void gameFinished() {
        this.worker.interrupt();
    }
}
