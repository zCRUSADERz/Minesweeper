package ru.yakovlev.gui;

import ru.yakovlev.board.BoardCoordinate;

/**
 * Координата игрового поля. Указывает на определенный пиксель игрвого поля.
 *
 * @since 0.1
 */
public class Coordinate {
    private final int x;
    private final int y;

    public Coordinate(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Преобразует координату с пиксельной точностью в координату ячейки
     * игрового поля.
     * @param imageSize размер иконки ячейки.
     * @return координата ячейки на игровом поле.
     */
    public final BoardCoordinate toBoardCoordinate(final int imageSize) {
        return new BoardCoordinate(this.x / imageSize, this.y / imageSize);
    }
}
