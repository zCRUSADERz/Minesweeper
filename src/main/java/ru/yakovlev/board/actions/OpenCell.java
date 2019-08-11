package ru.yakovlev.board.actions;

import ru.yakovlev.board.Board;
import ru.yakovlev.board.BoardCoordinate;
import ru.yakovlev.board.events.EventList;

/**
 * Открыть ячейку на игровом поле.
 *
 * @since 0.1
 */
public class OpenCell implements BoardAction {
    private final BoardCoordinate coordinate;

    public OpenCell(final BoardCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public BoardCoordinate coordinate() {
        return this.coordinate;
    }

    @Override
    public EventList perform(final Board board) {
        return board
            .cell(this.coordinate)
            .open(board);
    }
}
