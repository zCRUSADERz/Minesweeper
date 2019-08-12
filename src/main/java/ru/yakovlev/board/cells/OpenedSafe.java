package ru.yakovlev.board.cells;

import ru.yakovlev.Images;
import ru.yakovlev.board.Board;
import ru.yakovlev.board.BoardCoordinate;
import ru.yakovlev.board.events.EventList;

import javax.swing.*;
import java.util.Collections;
import java.util.Objects;

/**
 * Открытая безопасная ячейка.
 *
 * @since 0.1
 */
public class OpenedSafe implements Cell {
    private final BoardCoordinate coordinate;

    public OpenedSafe(final BoardCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public BoardCoordinate coordinate() {
        return this.coordinate;
    }

    @Override
    public boolean isNotOpen() {
        return false;
    }

    @Override
    public boolean isSafe() {
        return true;
    }

    @Override
    public boolean isFlag() {
        return false;
    }

    @Override
    public ImageIcon image(final Images images) {
        return images.image("zero");
    }

    @Override
    public EventList open(final Board board) {
        return new EventList(Collections.emptyList());
    }

    @Override
    public EventList setFlag(final Board board) {
        return new EventList(Collections.emptyList());
    }

    @Override
    public void checkBomb(final Board board) { }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OpenedSafe safe = (OpenedSafe) o;
        return coordinate.equals(safe.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
