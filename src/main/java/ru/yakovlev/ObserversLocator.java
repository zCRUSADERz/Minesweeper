package ru.yakovlev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Инкапсулирует всех наблюдателей в одном месте. Observer необходимого типа
 * можно получить передав Class события о котором нужно оповестить подписчиков.
 * 
 * @since 0.1
 */
@Component
public class ObserversLocator {
    private final Map<Class, Observer> observers;

    @Autowired
    public ObserversLocator(final List<Observer> observers) {
        this(
            observers
                .stream()
                .map((Function<Observer, Map.Entry<Class, Observer>>) observer ->
                    new AbstractMap.SimpleEntry<>(
                        observer.listenersClass(), observer
                    )
                )
                .collect(
                    Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)
                )
        );
    }

    public ObserversLocator(final Map<Class, Observer> observers) {
        this.observers = observers;
    }

    @SuppressWarnings("unchecked")
    public final <T> Observer<T> observer(final Class<T> type) {
        return this.observers.get(type);
    }
}
