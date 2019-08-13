package ru.yakovlev.board;

import ru.yakovlev.board.cells.Cell;
import ru.yakovlev.board.cells.CellType;
import ru.yakovlev.board.events.EventList;

import java.util.stream.Stream;

/**
 * Интерфейс игрового поля.
 *
 * @since 0.1
 */
public interface Board extends BoardPropertiesProvider {

    Cell cell(BoardCoordinate coordinate);

    /**
     * @param coordinate координата ячейки.
     * @return ячейки находящиеся рядом с переданной координатой.
     */
    Stream<Cell> aroundCells(BoardCoordinate coordinate);

    /**
     * Устанавливает новое значение типа ячейки по переданным координатам.
     * @param coordinate координата ячейки.
     * @param type новый тип ячейки.
     */
    void setType(BoardCoordinate coordinate, CellType type);

    /**
     * Оповещение игрового поля, что было открыто переданное количество ячеек.
     * @param amount количество открытых ячеек на игрвом поле.
     * @return список событий возникших из-за данного действия.
     */
    EventList cellsWasOpened(int amount);
}
