package ru.yakovlev.gui.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.yakovlev.InitializingComponent;
import ru.yakovlev.ObserversLocator;
import ru.yakovlev.board.BoardCoordinate;
import ru.yakovlev.board.SimpleBoard;
import ru.yakovlev.board.actions.OpenCell;
import ru.yakovlev.board.actions.SetFlag;
import ru.yakovlev.board.events.GameEvent;
import ru.yakovlev.board.events.MouseEventOnBoardListener;
import ru.yakovlev.gui.BoardPanel;
import ru.yakovlev.gui.GuiCoordinate;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Слушатель событий игровой панели.
 *
 * @since 0.1
 */
@Component
public class BoardListener extends MouseAdapter implements InitializingComponent {
    private final BoardPanel boardPanel;
    private final SimpleBoard board;
    private final ObserversLocator locator;

    @Autowired
    public BoardListener(
        final BoardPanel boardPanel,
        @Qualifier(value = "boardState") final SimpleBoard board,
        final ObserversLocator observersLocator
    ) {
        this.boardPanel = boardPanel;
        this.board = board;
        this.locator = observersLocator;
    }

    @Override
    public void init() {
        this.boardPanel.addMouseListener(this);
    }

    @Override
    public void mousePressed(final MouseEvent event) {
        this.locator.observer(MouseEventOnBoardListener.class)
            .apply(MouseEventOnBoardListener::mousePressedOnBoard);
    }

    @Override
    public void mouseReleased(final MouseEvent event) {
        final BoardCoordinate coordinate = new GuiCoordinate(
            event.getX(), event.getY()
        ).toBoardCoordinate(this.boardPanel.imageSize());
        if (
            event.getButton() == MouseEvent.BUTTON1
                || event.getButton() == MouseEvent.BUTTON2
        ) {
            final GameEvent gameEvent = this.board
                .perform(new OpenCell(coordinate));
            gameEvent.perform(this.locator);
        } else if (event.getButton() == MouseEvent.BUTTON3) {
            final GameEvent gameEvent = this.board
                .perform(new SetFlag(coordinate));
            gameEvent.perform(this.locator);
        }
        this.locator.observer(MouseEventOnBoardListener.class)
            .apply(MouseEventOnBoardListener::mouseReleasedOnBoard);
    }
}
