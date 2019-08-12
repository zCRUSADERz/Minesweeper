package ru.yakovlev.board.cells;

import ru.yakovlev.Images;
import ru.yakovlev.board.Board;
import ru.yakovlev.board.BoardCoordinate;
import ru.yakovlev.board.events.EventList;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Открытая ячейка находящаяся рядом с одной или несколькими бомбами.
 *
 * @since 0.1
 */
public class OpenedCellNearTheBomb implements Cell {
    private final BoardCoordinate coordinate;
    private final int bombsAround;

    public OpenedCellNearTheBomb(
        final BoardCoordinate coordinate, final int bombsAround
    ) {
        this.coordinate = coordinate;
        this.bombsAround = bombsAround;
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
        return false;
    }

    @Override
    public boolean isFlag() {
        return false;
    }

    @Override
    public ImageIcon image(final Images images) {
        return images.image(String.format("num%d", this.bombsAround));
    }

    /**
     * В случае если число флагов вокруг данной ячейки совпадает с количеством
     * бомб вокруг ячейки, то будут открыты все закрытые ячейки находящиеся рядом.
     * @param board игровое поле.
     * @return список игровых событий возникших в результате открытия ячеек.
     */
    @Override
    public EventList open(final Board board) {
        final List<Cell> cellsToOpen = new ArrayList<>();
        final AtomicInteger flagCounter = new AtomicInteger(0);
        board
            .aroundCells(this.coordinate)
            .forEach(cell -> {
                if (cell.isFlag()) {
                    flagCounter.incrementAndGet();
                } else if (cell.isNotOpen()) {
                    cellsToOpen.add(cell);
                }
            });
        final EventList result = new EventList();
        if (this.bombsAround == flagCounter.get()) {
            cellsToOpen.forEach(cell -> result.add(cell.open(board)));
        }
        return result;
    }

    @Override
    public EventList setFlag(final Board board) {
        return new EventList();
    }

    @Override
    public void checkBomb(final Board board) { }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OpenedCellNearTheBomb bomb = (OpenedCellNearTheBomb) o;
        return coordinate.equals(bomb.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
