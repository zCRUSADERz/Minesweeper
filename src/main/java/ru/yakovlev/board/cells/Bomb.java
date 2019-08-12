package ru.yakovlev.board.cells;

import ru.yakovlev.Images;
import ru.yakovlev.board.Board;
import ru.yakovlev.board.BoardCoordinate;
import ru.yakovlev.board.events.EventList;

import javax.swing.*;
import java.util.Collections;
import java.util.Objects;

/**
 * Открытая бомба.
 *
 * @since 0.1
 */
public class Bomb implements Cell {
    private final BoardCoordinate coordinate;

    public Bomb(final BoardCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public final BoardCoordinate coordinate() {
        return this.coordinate;
    }

    @Override
    public final boolean isNotOpen() {
        return false;
    }

    @Override
    public final boolean isSafe() {
        return false;
    }

    @Override
    public final boolean isFlag() {
        return false;
    }

    @Override
    public final ImageIcon image(final Images images) {
        return images.image("bomb");
    }

    @Override
    public final EventList open(final Board board) {
        return new EventList(Collections.emptyList());
    }

    @Override
    public final EventList setFlag(final Board board) {
        return new EventList(Collections.emptyList());
    }

    @Override
    public final void checkBomb(final Board board) { }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Bomb bomb = (Bomb) o;
        return coordinate.equals(bomb.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
