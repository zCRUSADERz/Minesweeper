package ru.yakovlev.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yakovlev.board.actions.BoardAction;
import ru.yakovlev.board.cells.Cell;
import ru.yakovlev.board.cells.CellType;
import ru.yakovlev.board.cells.SimpleCell;
import ru.yakovlev.board.events.EventList;
import ru.yakovlev.board.events.GameEvent;
import ru.yakovlev.board.events.GameFinishedListener;
import ru.yakovlev.board.events.NewGameListener;

import java.util.Collections;
import java.util.stream.Stream;

/**
 * Инкапсулирует часть состояния игрового поля.
 * Управляет инициализацией игрового поля при старте игры.
 * Первое открытие ячейки или установка флага на новом игровом поле приводит к
 * расстановке бомб.
 *
 * @since 0.1
 */
@Component
public class BoardState
    implements Board, SimpleBoard, NewGameListener, GameFinishedListener
{
    private final BoardImpl board;
    /**
     * Игра считается начавшейся, когда пользователь выполнит первое действие
     * с игровым полем.
     */
    private boolean started = false;
    private int openedCounter = 0;
    private boolean gameFinished = false;

    @Autowired
    public BoardState(final BoardImpl board) {
        this.board = board;
    }

    @Override
    public BoardProperties boardProperties() {
        return this.board.boardProperties();
    }

    @Override
    public Cell cell(final BoardCoordinate coordinate) {
        return this.board.cell(coordinate);
    }

    @Override
    public Stream<SimpleCell> cells() {
        final BoardProperties properties = this.boardProperties();
        final Stream<BoardCoordinate> coordinates = properties.boardCoordinates();
        return this.cells(coordinates)
            .map(cell -> cell);
    }

    public final Stream<Cell> cells(final Stream<BoardCoordinate> coordinates) {
        return coordinates.map(this::cell);
    }

    @Override
    public Stream<Cell> aroundCells(final BoardCoordinate coordinate) {
        final BoardProperties properties = this.boardProperties();
        final Stream<BoardCoordinate> aroundCoordinates =
            properties.coordinatesAround(coordinate);
        return this.cells(aroundCoordinates);
    }

    /**
     * Если игра была завершена, то любое последующее действие ведет
     * к старту новой игры.
     * Первое действие с игровым полем расставляет бомбы учитывая ту ячейку над
     * которой выполнится действие пользователя - в эту ячейку не попадет бомба.
     * @param action действие над игровой доской.
     * @return игрвое событие порожденное данным действием.
     */
    @Override
    public GameEvent perform(final BoardAction action) {
        final BoardProperties properties = this.boardProperties();
        final BoardCoordinate coordinate = action.coordinate();
        final GameEvent result;
        if (properties.onBoard(coordinate)) {
            if (this.gameFinished) {
                result = locator -> locator
                    .observer(NewGameListener.class)
                    .apply(listener -> listener.newGame(properties));
            } else {
                this.checkStart(coordinate);
                result = action.perform(this);
            }
        } else {
            result = locator -> { };
        }
        return result;
    }

    @Override
    public void setType(final BoardCoordinate coordinate, final CellType type) {
        this.board.setType(coordinate, type);
    }

    @Override
    public final void newGame(final BoardProperties boardProperties) {
        this.started = false;
        this.openedCounter = 0;
        this.gameFinished = false;
    }

    @Override
    public void gameFinished() {
        this.gameFinished = true;
        final BoardProperties properties = this.boardProperties();
        final Stream<BoardCoordinate> coordinates = properties.boardCoordinates();
        this.cells(coordinates).forEach(cell -> cell.checkBomb(this));
    }

    @Override
    public final EventList cellsWasOpened(final int amount) {
        this.openedCounter += amount;
        return checkVictory();
    }

    private void checkStart(final BoardCoordinate coordinate) {
        if (!this.started) {
            this.board.initBombs(coordinate);
            this.started = true;
        }
    }

    private EventList checkVictory() {
        final EventList result;
        final BoardProperties properties = this.boardProperties();
        if ((properties.cells() - properties.bombs()) == this.openedCounter) {
            result = new EventList(
                locator -> locator
                    .observer(GameFinishedListener.class)
                    .apply(GameFinishedListener::gameFinished)
            );
        } else {
            result = new EventList(Collections.emptyList());
        }
        return result;
    }
}
