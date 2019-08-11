package ru.yakovlev.board.events;

import ru.yakovlev.ObserversLocator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Список игровых событий.
 *
 * @since 0.1
 */
public class EventList implements GameEvent {
    private final List<GameEvent> events;

    public EventList() {
        this(new ArrayList<>());
    }

    public EventList(final GameEvent...gameEvents) {
        this(Arrays.asList(gameEvents));
    }

    public EventList(final List<GameEvent> events) {
        this.events = events;
    }

    @Override
    public void perform(final ObserversLocator locator) {
        this.events.forEach(event -> event.perform(locator));
    }

    public final void add(final EventList otherList) {
        this.events.addAll(otherList.events);
    }
}
