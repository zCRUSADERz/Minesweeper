package ru.yakovlev.gui;

import ru.yakovlev.InitializingComponent;

import javax.swing.*;
import java.awt.*;

/**
 * Информационная панель над игровым полем.
 *
 * @since 0.1
 */
@org.springframework.stereotype.Component
public class InfoPanel extends JPanel implements InitializingComponent {
    private final Component timerField;
    private final Component resetButton;
    private final Component bombsField;

    public InfoPanel(
        final Component timerField, final Component resetButton,
        final Component bombsField
    ) {
        super(new BorderLayout());
        this.timerField = timerField;
        this.resetButton = resetButton;
        this.bombsField = bombsField;
    }

    @Override
    public final void init() {
        this.add(this.timerField, BorderLayout.WEST);
        this.add(this.resetButton, BorderLayout.CENTER);
        this.add(this.bombsField, BorderLayout.EAST);
        this.setBorder(BorderFactory.createLoweredBevelBorder());
    }
}
