package ru.yakovlev.board;

import ru.yakovlev.board.cells.CellType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Игровое поле.
 *
 * @since 0.1
 */
public class BoardImpl implements BoardPropertiesProvider {

    /**
     * Хранит состояние игровых ячеек.
     */
    private CellType[][] cells;

    /**
     * Количество бомб вокруг ячейки.
     */
    private int[][] bombsInCell;

    /**
     * Количество бомб на игрвом поле.
     */
    private int bombs;

    /**
     * Возвращает параметры текущей игровой доски.
     * @return параметры текущей игрвой доски.
     */
    @Override
    public final BoardProperties boardProperties() {
        return new BoardProperties(
            this.cells.length, this.cells[0].length, this.bombs
        );
    }

    /**
     * Устанавливает новое значение типа ячейки по переданным координатам.
     * @param coordinate координата ячейки.
     * @param type новый тип ячейки.
     */
    public final void setType(
        final BoardCoordinate coordinate, final CellType type
    ) {
        coordinate.set(this.cells, type);
    }

    /**
     * Приводит все внутренее состояние в соответствие с параметрами новой
     * игровой доски.
     * @param properties параметры новой игровой доски.
     */
    public final void newBoard(final BoardProperties properties) {
        final int width = properties.width();
        final int height = properties.height();
        this.cells = new CellType[width][height];
        this.bombsInCell = new int[width][height];
        this.bombs = properties.bombs();
        for (CellType[] arr : this.cells) {
            Arrays.fill(arr, CellType.UN_OPENED);
        }
    }

    /**
     * Расставляет необходимое количество бомб по игровому полю и высчитывает
     * количество бомб вокруг для каждой ячейки.
     *
     * В ячейке по переданной координате не должна попасть бомба, иначе первое
     * действие пользователя может приводит к окончанию игры с проигрышем.
     * @param coordinate координаты ячейки, которая будет открыта (или помечена)
     *                   после инициализации игрового поля.
     */
    public final void initBombs(final BoardCoordinate coordinate) {
        final Random random = new Random();
        final Set<BoardCoordinate> coordinates = new HashSet<>();
        final int width = this.cells.length;
        final int height = this.cells[0].length;
        int counter = 0;
        while (counter < this.bombs) {
            final int randomX = random.nextInt(width);
            final int randomY;
            if (randomX == coordinate.getX()) {
                int nextY;
                do {
                    nextY = random.nextInt(height);
                } while (nextY == coordinate.getY());
                randomY = nextY;
            } else {
                randomY = random.nextInt(height);
            }
            if (coordinates.add(new BoardCoordinate(randomX, randomY))) {
                counter += 1;
            }
        }
        final BoardProperties properties = this.boardProperties();
        for (BoardCoordinate coord : coordinates) {
            coord.set(this.cells, CellType.UN_OPENED_BOMB);
            properties
                .coordinatesAround(coord)
                .forEach(
                    aroundCoordinate -> aroundCoordinate.increment(this.bombsInCell)
                );
        }
    }
}
