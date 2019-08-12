package ru.yakovlev.board.cells;

import ru.yakovlev.Images;
import ru.yakovlev.board.Board;
import ru.yakovlev.board.BoardCoordinate;
import ru.yakovlev.board.events.EventList;
import ru.yakovlev.board.events.FlagEventListener;

import javax.swing.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * Не открытая ячейка вокруг которой нет ни одной бомбы.
 *
 * @since 0.1
 */
public class UnopenedSafeCell implements Cell {
    private final BoardCoordinate coordinate;

    public UnopenedSafeCell(final BoardCoordinate coordinate) {
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
        return true;
    }

    @Override
    public boolean isFlag() {
        return false;
    }

    @Override
    public ImageIcon image(final Images images) {
        return images.image("closed");
    }

    /**
     * Открытие безопасной ячейки влечет за собой вскрытие всех находящихся
     * рядом ячеек, т.к. в них не может быть бомбы.
     * P.S. рекурсивный алгоритм в данном случае намного проще, но возникает
     * проблема с StackOverflowError, поэтому реализовано без использования
     * рекурсии.
     * @param board игровое поле.
     * @return список игровых событий возникших в результате открытия ячеек.
     */
    @Override
    public EventList open(final Board board) {
        final Set<Cell> toOpen = new HashSet<>();
        final Set<Cell> toOpenSafeCells = new HashSet<>();
        final Set<Cell> cellQueue = new HashSet<>();
        cellQueue.add(this);
        while (!cellQueue.isEmpty()) {
            final Iterator<Cell> it = cellQueue.iterator();
            final Cell next = it.next();
            it.remove();
            toOpenSafeCells.add(next);
            board
                .aroundCells(next.coordinate())
                .filter(Cell::isNotOpen)
                .forEach(cell -> {
                    if (cell.isSafe()) {
                        if (!toOpenSafeCells.contains(cell)) {
                            cellQueue.add(cell);
                        }
                    } else {
                        toOpen.add(cell);
                    }
                });
        }
        final EventList result = new EventList();
        toOpen.forEach(cell -> result.add(cell.open(board)));
        toOpenSafeCells
            .forEach(cell -> board.setType(cell.coordinate(), CellType.OPENED));
        final EventList boardEvent = board.cellsWasOpened(toOpenSafeCells.size());
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
        final UnopenedSafeCell cell = (UnopenedSafeCell) o;
        return coordinate.equals(cell.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
