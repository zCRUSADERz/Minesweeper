package ru.yakovlev.board.actions;

import ru.yakovlev.board.Board;
import ru.yakovlev.board.BoardCoordinate;
import ru.yakovlev.board.events.EventList;

/**
 * Действие пользователя с игровым полем.
 *
 * @since 0.1
 */
public interface BoardAction {

    BoardCoordinate coordinate();

    /**
     * Выполнение действия над игровым полем.
     * @param board игровое поле.
     * @return список игровых событий порожденных данным действием.
     */
    EventList perform(Board board);
}
