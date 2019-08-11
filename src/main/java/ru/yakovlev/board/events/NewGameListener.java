package ru.yakovlev.board.events;

import ru.yakovlev.board.BoardProperties;

/**
 * @since 0.1
 */
public interface NewGameListener {

    /**
     * Создать новое игровое поле с переданными параметрами.
     * @param properties свойства нового игрового поля.
     */
    void newGame(BoardProperties properties);
}
