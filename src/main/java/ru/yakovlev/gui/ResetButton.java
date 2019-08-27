package ru.yakovlev.gui;

import org.springframework.stereotype.Component;
import ru.yakovlev.Images;
import ru.yakovlev.InitializingComponent;
import ru.yakovlev.board.BoardProperties;
import ru.yakovlev.board.events.DefeatListener;
import ru.yakovlev.board.events.MouseEventOnBoardListener;
import ru.yakovlev.board.events.NewGameListener;

import javax.swing.*;

/**
 * Кнопка начала новой игры. Содержит иконку смайлик, который пугается при
 * зажатии кнопки мыши на игровом поле и может быть расстроен если игрок
 * проиграет.
 *
 * @since 0.1
 */
@Component
public class ResetButton extends JButton implements NewGameListener,
    DefeatListener, MouseEventOnBoardListener, InitializingComponent
{
    private final Images icons;
    private boolean gameDefeated = false;

    public ResetButton() {
        this(new Images());
    }

    public ResetButton(final Images icons) {
        this.icons = icons;
    }

    @Override
    public final void init() {
        this.setBorder(BorderFactory.createLoweredBevelBorder());
        this.updateIcon();
    }

    @Override
    public final void newGame(final BoardProperties properties) {
        this.gameDefeated = false;
        this.updateIcon();
    }

    @Override
    public final void mousePressedOnBoard() {
        this.setIcon(this.icons.image("afraid"));
    }

    @Override
    public final void mouseReleasedOnBoard() {
        this.updateIcon();
    }

    @Override
    public final void defeat() {
        this.gameDefeated = true;
    }

    private void updateIcon() {
        if (this.gameDefeated) {
            this.setIcon(this.icons.image("sad"));
        } else {
            this.setIcon(this.icons.image("happy"));
        }
    }
}
