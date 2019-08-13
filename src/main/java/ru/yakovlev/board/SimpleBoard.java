package ru.yakovlev.board;

import ru.yakovlev.board.actions.BoardAction;
import ru.yakovlev.board.cells.SimpleCell;
import ru.yakovlev.board.events.GameEvent;

import java.util.stream.Stream;

/**
 * Игровая доска с которой можно взаимодействовать только через объекты
 * BoardAction. Это фасад перед графом объектов, отвечающих за игровое поле,
 * имеющий единственную точку входа.
 *
 * @since 0.1
 */
public interface SimpleBoard {

    Stream<SimpleCell> cells();

    /**
     * Выполнить действие пользователя.
     * @param action действие над игровой доской.
     * @return игровое событие порожденное данным действием.
     */
    GameEvent perform(BoardAction action);
}
