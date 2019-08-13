package ru.yakovlev.board;

import ru.yakovlev.Coordinate;

import java.util.Objects;

/**
 * Координаты ячейки на игровом поле.
 *
 * @since 0.1
 */
public class BoardCoordinate implements Coordinate {
    private final int x;
    private final int y;

    public BoardCoordinate(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public final int getX() {
        return this.x;
    }

    @Override
    public final int getY() {
        return this.y;
    }

    public final <T> T find(final T[][] board) {
        return board[this.x][this.y];
    }

    public final int find(final int[][] board) {
        return board[this.x][this.y];
    }

    public final <T> void set(final T[][] board, T value) {
        board[this.x][this.y] = value;
    }

    public final void set(final int[][] board, int value) {
        board[this.x][this.y] = value;
    }

    public final void increment(final int[][] board) {
        board[this.x][this.y]++;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final BoardCoordinate that = (BoardCoordinate) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
