package ru.yakovlev.board.cells;

import ru.yakovlev.Images;
import ru.yakovlev.board.Board;
import ru.yakovlev.board.BoardCoordinate;
import ru.yakovlev.board.events.EventList;

import javax.swing.*;

/**
 * Ячейка игрвого поля.
 *
 * @since 0.1
 */
public interface Cell {

    BoardCoordinate coordinate();

    ImageIcon image(Images images);

    boolean isNotOpen();

    /**
     * Ячейка является безопасной, если вокруг нее нет ни одной бомбы.
     * @return true, если ячейка безопасная.
     */
    boolean isSafe();

    boolean isFlag();

    /**
     * Открыть данную ячейку.
     * @param board игровое поле.
     * @return список игровых событий.
     */
    EventList open(Board board);

    /**
     * Установить(или снять) флаг в данной ячейке.
     * @param board игровое поле
     * @return список игровых событий.
     */
    EventList setFlag(Board board);

    /**
     * Проверить на наличие бомбы.
     * @param board игрвое поле.
     */
    void checkBomb(Board board);
}
