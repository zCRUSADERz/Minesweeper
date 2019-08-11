package ru.yakovlev.board.actions;

import ru.yakovlev.board.Board;
import ru.yakovlev.board.BoardCoordinate;
import ru.yakovlev.board.events.EventList;

/**
 * Установить (или снять) флаг в ячейке игрового поля.
 *
 * @since 0.1
 */
public class SetFlag implements BoardAction {
    private final BoardCoordinate coordinate;

    public SetFlag(final BoardCoordinate coordinate) {
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
            .setFlag(board);
    }
}
