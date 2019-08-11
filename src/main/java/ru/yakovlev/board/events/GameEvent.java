package ru.yakovlev.board.events;

import ru.yakovlev.ObserversLocator;

/**
 * @since 0.1
 */
public interface GameEvent {

    /**
     * Выполнить сопутствующие действия для данного события.
     * @param locator сборник наблюдателей различных событий.
     */
    void perform(ObserversLocator locator);
}
