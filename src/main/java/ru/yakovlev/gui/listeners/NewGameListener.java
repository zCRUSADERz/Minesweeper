package ru.yakovlev.gui.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yakovlev.InitializingComponent;
import ru.yakovlev.Observer;
import ru.yakovlev.board.BoardProperties;
import ru.yakovlev.board.BoardPropertiesProvider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

/**
 * Слушатель событий абстрактной кнопки отвечающей за старт новой игры с
 * старыми параметрами игрового поля.
 *
 * @since 0.1
 */
@Component
public class NewGameListener implements ActionListener, InitializingComponent {
    private final List<AbstractButton> components;
    private final BoardPropertiesProvider boardPropertiesProvider;
    private final Observer<ru.yakovlev.board.events.NewGameListener> observer;

    @Autowired
    public NewGameListener(
        @Qualifier(value = "boardImpl")
        final BoardPropertiesProvider boardPropertiesProvider,
        @Qualifier(value = "newGameObserver")
        final Observer<ru.yakovlev.board.events.NewGameListener> observer,
        @Qualifier(value = "resetButton") final AbstractButton resetButton,
        @Qualifier(value = "newGameMenuItem") final AbstractButton newGameMenuItem
    ) {
        this(
            boardPropertiesProvider, observer,
            Arrays.asList(resetButton, newGameMenuItem)
        );
    }

    public NewGameListener(
        final BoardPropertiesProvider boardPropertiesProvider,
        final Observer<ru.yakovlev.board.events.NewGameListener> observer,
        final List<AbstractButton> components
    ) {
        this.components = components;
        this.boardPropertiesProvider = boardPropertiesProvider;
        this.observer = observer;
    }

    @Override
    public void init() {
        this.components.forEach(
            component -> component.addActionListener(this)
        );
    }

    @Override
    public void actionPerformed(final ActionEvent event) {
        final BoardProperties properties =
            this.boardPropertiesProvider.boardProperties();
        this.observer.apply(listener -> listener.newGame(properties));
    }
}
