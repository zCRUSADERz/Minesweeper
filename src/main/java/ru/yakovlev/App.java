package ru.yakovlev;

import ru.yakovlev.board.BoardProperties;
import ru.yakovlev.board.events.NewGameListener;

/**
 * Точка входа приложения.
 *
 * @since 0.1
 */
final class App implements InitializingComponent {
    private final Observer<NewGameListener> observer;
    private final BoardProperties properties;

    public App(
        final Observer<NewGameListener> observer,
        final BoardProperties properties
    ) {
        this.observer = observer;
        this.properties = properties;
    }

    @Override
    public final void init() {
        this.observer.apply(listener -> listener.newGame(this.properties));
    }
}
