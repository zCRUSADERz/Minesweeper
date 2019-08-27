package ru.yakovlev.gui;

import org.springframework.stereotype.Component;
import ru.yakovlev.InitializingComponent;
import ru.yakovlev.board.BoardProperties;
import ru.yakovlev.board.events.FlagEventListener;
import ru.yakovlev.board.events.GameFinishedListener;
import ru.yakovlev.board.events.NewGameListener;

import javax.swing.*;
import java.awt.*;

/**
 * Текстовое поле показывающее сколько бомб остались еще не помеченными.
 * Логика крайне проста: количество бомб - количество флагов.
 *
 * @since 0.1
 */
@Component
public class BombsField extends JTextField implements NewGameListener,
    FlagEventListener, InitializingComponent, GameFinishedListener
{

    @Override
    public final void init() {
        this.setColumns(3);
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        this.setFont(new Font("DigitalFont.TTF", Font.BOLD, 25));
        this.setBackground(Color.BLACK);
        this.setForeground(Color.RED);
        this.setEditable(false);
    }

    @Override
    public final void newGame(final BoardProperties properties) {
        this.setText(String.valueOf(properties.bombs()));
    }

    @Override
    public void flagWasSet() {
        final int unmarkedBombs = Integer.parseInt(this.getText());
        this.setText(String.valueOf(unmarkedBombs - 1));
    }

    @Override
    public void flagWasRemoved() {
        final int unmarkedBombs = Integer.parseInt(this.getText());
        this.setText(String.valueOf(unmarkedBombs + 1));
    }

    @Override
    public void gameFinished() {
        this.setText("0");
    }
}
