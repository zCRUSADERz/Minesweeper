package ru.yakovlev.gui;

import ru.yakovlev.InitializingComponent;
import ru.yakovlev.gui.timer.TimerField;

import javax.swing.*;
import java.awt.*;

/**
 * Информационная панель над игровым полем.
 *
 * @since 0.1
 */
public class InfoPanel extends JPanel implements InitializingComponent {
    private final TimerField timeField;
    private final ResetButton reset;
    private final BombsField bombsField;

    public InfoPanel(
        final TimerField timeField, final ResetButton reset,
        final BombsField bombsField
    ) {
        super(new BorderLayout());
        this.timeField = timeField;
        this.reset = reset;
        this.bombsField = bombsField;
    }

    @Override
    public final void init() {
        this.add(this.timeField, BorderLayout.WEST);
        this.add(this.reset, BorderLayout.CENTER);
        this.add(this.bombsField, BorderLayout.EAST);
        this.setBorder(BorderFactory.createLoweredBevelBorder());
    }
}
