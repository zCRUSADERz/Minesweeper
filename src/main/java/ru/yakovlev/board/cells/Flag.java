package ru.yakovlev.board.cells;

import ru.yakovlev.Images;
import ru.yakovlev.board.Board;
import ru.yakovlev.board.BoardCoordinate;
import ru.yakovlev.board.events.EventList;
import ru.yakovlev.board.events.FlagEventListener;

import javax.swing.*;
import java.util.Collections;
import java.util.Objects;

/**
 * Флаг.
 *
 * @since 0.1
 */
public class Flag implements Cell {
    private final BoardCoordinate coordinate;

    public Flag(final BoardCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public BoardCoordinate coordinate() {
        return this.coordinate;
    }

    @Override
    public boolean isNotOpen() {
        return true;
    }

    @Override
    public boolean isSafe() {
        return false;
    }

    @Override
    public boolean isFlag() {
        return true;
    }

    @Override
    public ImageIcon image(final Images images) {
        return images.image("flaged");
    }

    /**
     * Флаг невозможно открыть, сначала его нужно снять.
     * @param board игровое поле.
     * @return список игровых событий возникших в результате открытия ячейки.
     */
    @Override
    public EventList open(final Board board) {
        return new EventList(Collections.emptyList());
    }

    /**
     * Данное действие на установленном флаге приводит к его снятию.
     * @param board игровое поле
     * @return список игровых событий возникших в результате открытия ячейки.
     */
    @Override
    public EventList setFlag(final Board board) {
        board.setType(this.coordinate, CellType.UN_OPENED);
        return new EventList(
            locator -> locator
                .observer(FlagEventListener.class)
                .apply(FlagEventListener::flagWasRemoved)
        );
    }

    @Override
    public void checkBomb(final Board board) {
        board.setType(this.coordinate, CellType.NO_BOMB);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Flag flag = (Flag) o;
        return coordinate.equals(flag.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
