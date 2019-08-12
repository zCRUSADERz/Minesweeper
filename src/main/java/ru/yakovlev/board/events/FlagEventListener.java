package ru.yakovlev.board.events;

/**
 * @since 0.1
 */
public interface FlagEventListener {

    /**
     * На игровом поле был установлен флаг.
     */
    void flagWasSet();

    /**
     * Флаг был убран с игрового поля.
     */
    void flagWasRemoved();
}
