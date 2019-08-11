package ru.yakovlev.board;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Свойства игрового поля.
 *
 * @since 0.1
 */
public class BoardProperties {

    private final int width;

    private final int height;

    private final int bombs;

    public BoardProperties(final int width, final int height, final int bombs) {
        this.width = width;
        this.height = height;
        this.bombs = bombs;
    }

    public final int width() {
        return this.width;
    }

    public final int height() {
        return this.height;
    }

    public final int bombs() {
        return this.bombs;
    }

    /**
     * @return общее количество ячеек на игровом поле.
     */
    public final int cells() {
        return this.width * this.height;
    }

    /**
     * @return Stream координат всех ячеек игрвого поля.
     */
    public final Stream<BoardCoordinate> boardCoordinates() {
        return IntStream.iterate(0, x -> x + 1)
            .limit(this.width)
            .mapToObj(x -> IntStream.iterate(0, y -> y + 1)
                .limit(this.height)
                .mapToObj(y -> new BoardCoordinate(x, y)))
            .flatMap(coordinateStream -> coordinateStream);
    }

    /**
     * @param coordinate координата ячейки.
     * @return координаты всех ячеек находящихся рядом с ячейкой.
     */
    public final Stream<BoardCoordinate> coordinatesAround(
        final BoardCoordinate coordinate
    ) {
        return IntStream.iterate(coordinate.getX() - 1, x -> x + 1)
            .limit(3)
            .mapToObj(x -> IntStream
                .iterate(coordinate.getY() - 1, y -> y + 1)
                .limit(3)
                .filter(y -> x >= 0 && x < this.width && y >= 0 && y < this.height)
                .filter(y -> !(x == coordinate.getX() && y == coordinate.getY()))
                .mapToObj(y -> new BoardCoordinate(x, y))
            ).flatMap(coordinateStream -> coordinateStream);
    }

    /**
     * Проверяет может ли ячейка с такими координатами находится на игровом поле.
     * P.S. Координата может указывать за пределы игрвого поля.
     * @param coordinate координата.
     * @return true, если координата указывает на ячейку игрового поля.
     */
    public final boolean onBoard(final BoardCoordinate coordinate) {
        final int x = coordinate.getX();
        final int y = coordinate.getY();
        return x >= 0 && x < this.width && y >=0 && y < this.height;
    }
}
