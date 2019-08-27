package ru.yakovlev;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import ru.yakovlev.board.BoardProperties;
import ru.yakovlev.board.events.NewGameListener;

/**
 * Точка входа приложения.
 *
 * @since 0.1
 */
@Component
final class Main implements InitializingComponent {
    private final Observer<NewGameListener> observer;
    private final BoardProperties properties;

    public Main(
        @Qualifier(value = "newGameObserver")
        final Observer<NewGameListener> observer,
        @Qualifier(value = "newbieBoardProperties")
        final BoardProperties properties
    ) {
        this.observer = observer;
        this.properties = properties;
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        final ApplicationContext context = new ClassPathXmlApplicationContext(
            "spring/autowire-context.xml"
        );
        final Observer<InitializingComponent> initializingComponentObserver =
            (Observer<InitializingComponent>) context.getBean("initializingComponentObserver");
        initializingComponentObserver.apply(InitializingComponent::init);
    }

    @Override
    public final void init() {
        this.observer.apply(listener -> listener.newGame(this.properties));
    }
}
