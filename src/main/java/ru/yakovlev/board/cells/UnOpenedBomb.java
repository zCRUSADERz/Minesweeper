package ru.yakovlev.board.cells;

import ru.yakovlev.Images;
import ru.yakovlev.board.Board;
import ru.yakovlev.board.BoardCoordinate;
import ru.yakovlev.board.events.DefeatListener;
import ru.yakovlev.board.events.EventList;
import ru.yakovlev.board.events.FlagEventListener;
import ru.yakovlev.board.events.GameFinishedListener;

import javax.swing.*;
import java.util.Objects;

/**
 * Не открытая бомба.
 *
 * @since 0.1
 */
public class UnOpenedBomb implements Cell {
    private final BoardCoordinate coordinate;

    public UnOpenedBomb(final BoardCoordinate coordinate) {
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
        board.setType(this.coordinate, CellType.EXPLODED_BOMB);
        return new EventList(
            locator -> locator
                .observer(DefeatListener.class)
                .apply(DefeatListener::defeat),
            locator -> locator
                .observer(GameFinishedListener.class)
                .apply(GameFinishedListener::gameFinished)
        );
    }

    @Override
    public EventList setFlag(final Board board) {
        board.setType(this.coordinate, CellType.FLAG_ON_BOMB);
        return new EventList(
            locator -> locator
                .observer(FlagEventListener.class)
                .apply(FlagEventListener::flagWasSet)
        );
    }

    @Override
    public void checkBomb(final Board board) {
        board.setType(this.coordinate, CellType.BOMB);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final UnOpenedBomb bomb = (UnOpenedBomb) o;
        return coordinate.equals(bomb.coordinate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coordinate);
    }
}
