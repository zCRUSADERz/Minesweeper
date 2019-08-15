package ru.yakovlev.gui.listeners;

import ru.yakovlev.InitializingComponent;
import ru.yakovlev.Observer;
import ru.yakovlev.board.BoardProperties;
import ru.yakovlev.board.BoardPropertiesProvider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Слушатель событий абстрактной кнопки отвечающей за старт новой игры с
 * старыми параметрами игрового поля.
 *
 * @since 0.1
 */
public class NewGameListener implements ActionListener, InitializingComponent {
    private final AbstractButton component;
    private final BoardPropertiesProvider boardPropertiesProvider;
    private final Observer<ru.yakovlev.board.events.NewGameListener> observer;

    public NewGameListener(
        final AbstractButton component,
        final BoardPropertiesProvider boardPropertiesProvider,
        final Observer<ru.yakovlev.board.events.NewGameListener> observer
    ) {
        this.component = component;
        this.boardPropertiesProvider = boardPropertiesProvider;
        this.observer = observer;
    }

    @Override
    public void init() {
        this.component.addActionListener(this);
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        final BoardProperties properties =
            this.boardPropertiesProvider.boardProperties();
        this.observer.apply(listener -> listener.newGame(properties));
    }
}
