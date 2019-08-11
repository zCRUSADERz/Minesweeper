package ru.yakovlev.board.cells;

import ru.yakovlev.board.BoardCoordinate;

/**
 * @since 0.1
 */
public interface CellFactory {

    Cell cell(final BoardCoordinate coordinate, final int bombsAround);
}
