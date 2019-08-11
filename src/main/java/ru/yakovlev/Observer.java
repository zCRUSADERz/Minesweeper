package ru.yakovlev;

import java.util.List;
import java.util.function.Consumer;

/**
 * Обобщенный Observer - позволяет оповестить о событии типа T всех подписчиков.
 *
 * @param <T> тип события
 * @since 0.1
 */
public class Observer<T> {
    /**
     * Список подписчиков на событие T.
     */
    private final List<T> observables;
    private final Class<T> listenerClass;

    public Observer(final List<T> observables, final Class<T> listenerClass) {
        this.observables = observables;
        this.listenerClass = listenerClass;
    }

    public final void apply(final Consumer<T> consumer) {
        this.observables.forEach(consumer);
    }

    public final Class<T> listenersClass() {
        return this.listenerClass;
    }
}
