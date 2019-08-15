package ru.yakovlev.gui.listeners;

import ru.yakovlev.InitializingComponent;
import ru.yakovlev.Observer;
import ru.yakovlev.board.BoardProperties;
import ru.yakovlev.board.events.NewGameListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Слушатель событий абстрактной кнопки отвечающей за старт новой игры с
 * заданными параметрами.
 *
 * @since 0.1
 */
public class NewGameWithPropsListener
    implements ActionListener, InitializingComponent
{
    private final AbstractButton component;
    private final BoardProperties properties;
    private final Observer<NewGameListener> observer;

    public NewGameWithPropsListener(
        final AbstractButton component, final BoardProperties properties,
        final Observer<NewGameListener> observer
    ) {
        this.component = component;
        this.properties = properties;
        this.observer = observer;
    }

    @Override
    public void init() {
        this.component.addActionListener(this);
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        this.observer.apply(
            observable -> observable.newGame(this.properties)
        );
    }
}
