package ru.yakovlev.gui.listeners;

import ru.yakovlev.InitializingComponent;
import ru.yakovlev.Observer;
import ru.yakovlev.board.BoardProperties;
import ru.yakovlev.board.BoardPropertiesProvider;
import ru.yakovlev.board.events.NewGameListener;
import ru.yakovlev.gui.BoardOptions;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Слушатель событий абстрактной кнопки отвечающей за создание фрейма для
 * ввода новых параметров игрового поля и старта новой игры.
 *
 * @since 0.1
 */
public class NewGameSpecialListener
    implements ActionListener, InitializingComponent
{
    private final AbstractButton component;
    private final BoardPropertiesProvider boardPropertiesProvider;
    private final Observer<NewGameListener> observer;

    public NewGameSpecialListener(
        final AbstractButton component,
        final BoardPropertiesProvider boardPropertiesProvider,
        final Observer<NewGameListener> observer
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
        new BoardOptions(this.observer).init(properties);
    }
}
