package ru.yakovlev.board.cells;

import com.sun.xml.internal.bind.v2.TODO;
import ru.yakovlev.board.BoardCoordinate;

/**
 * Все возможные типы ячеек.
 *
 * @since 0.1
 */
public enum CellType implements CellFactory {

    UN_OPENED,

    UN_OPENED_BOMB,

    FLAG,

    FLAG_ON_BOMB,

    OPENED,

    BOMB,

    /**
     * Ошибочно поставленный флаг.
     */
    NO_BOMB,

    EXPLODED_BOMB;

    //TODO Реализовать для каждого типа свой экземпляр ячейки.
    @Override
    public Cell cell(final BoardCoordinate coordinate, final int bombsAround) {
        return null;
    }
}
