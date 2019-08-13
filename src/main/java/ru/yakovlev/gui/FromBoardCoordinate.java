package ru.yakovlev.gui;

import ru.yakovlev.Coordinate;
import ru.yakovlev.board.BoardCoordinate;

/**
 * Конвертер из координаты ячейки в координату пикселя на игровом поле.
 *
 * @since 0.1
 */
public class FromBoardCoordinate implements Coordinate {
    private final BoardCoordinate coordinate;
    private final int imageSize;

    public FromBoardCoordinate(
        final BoardCoordinate coordinate, final int size
    ) {
        this.coordinate = coordinate;
        imageSize = size;
    }

    @Override
    public final int getX() {
        return this.coordinate.getX() * this.imageSize;
    }

    @Override
    public final int getY() {
        return this.coordinate.getY() * this.imageSize;
    }
}
