package ru.yakovlev.board.cells;

/**
 * Все возможные типы ячеек.
 *
 * @since 0.1
 */
public enum CellType {

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

    EXPLODED_BOMB
}
