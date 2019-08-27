package ru.yakovlev.gui.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
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
@Component
public class NewGameSpecialListener
    implements ActionListener, InitializingComponent
{
    private final AbstractButton component;
    private final BoardPropertiesProvider boardPropertiesProvider;
    private final Observer<NewGameListener> observer;

    @Autowired
    public NewGameSpecialListener(
        @Qualifier(value = "specialMenuItem") final AbstractButton component,
        @Qualifier(value = "boardImpl")
        final BoardPropertiesProvider boardPropertiesProvider,
        @Qualifier(value = "newGameObserver")
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
