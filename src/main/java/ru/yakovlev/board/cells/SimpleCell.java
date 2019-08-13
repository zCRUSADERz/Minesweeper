package ru.yakovlev.board.cells;

import ru.yakovlev.Images;
import ru.yakovlev.board.BoardCoordinate;

import javax.swing.*;

/**
 * Простая ячейка с которой не возможно выполнить никаких действий,
 * но можно получить некоторые данные.
 *
 * @since 0.1
 */
public interface SimpleCell {

    BoardCoordinate coordinate();

    ImageIcon image(Images images);
}
