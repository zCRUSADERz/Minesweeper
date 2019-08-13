package ru.yakovlev.board.cells;

import ru.yakovlev.Images;
import ru.yakovlev.board.Board;
import ru.yakovlev.board.BoardCoordinate;
import ru.yakovlev.board.events.EventList;
import ru.yakovlev.board.events.FlagEventListener;

import javax.swing.*;
import java.util.Objects;

/**
 * Не открытая ячейка рядом с которой находится одна или несколько бомб.
 *
 * @since 0.1
 */
public class UnOpenedCellNearTheBomb implements Cell {
    private final BoardCoordinate coordinate;

    public UnOpenedCellNearTheBomb(final BoardCoordinate coordinate) {
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
        return false;
    }

    @Override
    public ImageIcon image(final Images images) {
        return images.image("closed");
    }

    @Override
    public EventList open(final Board board) {
        final EventList result = new EventList();
        board.setType(this.coordinate, CellType.OPENED);
        final EventList boardEvent = board.cellsWasOpened(1);
        result.add(boardEvent);
        return result;
    }

    @Override
    public EventList setFlag(final Board board) {
        board.setType(this.coordinate, CellType.FLAG);
        return new EventList(
            locator -> locator
                .observer(FlagEventListener.class)
                .apply(FlagEventListener::flagWasSet)
        );
    }

    @Override
    public void checkBomb(final Board board) { }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final UnOpenedCellNearTheBomb bomb = (UnOpenedCellNearTheBomb) o;
        return coordinate.equals(bomb.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
