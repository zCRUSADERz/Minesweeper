package ru.yakovlev;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext(
            "spring/app-context.xml"
        );
        final Observer<InitializingComponent> newGameObserver =
            (Observer<InitializingComponent>) context.getBean("InitializingComponentObserver");
        newGameObserver.apply(InitializingComponent::init);
    }

    @Override
    public final void init() {
        this.observer.apply(listener -> listener.newGame(this.properties));
    }
}
