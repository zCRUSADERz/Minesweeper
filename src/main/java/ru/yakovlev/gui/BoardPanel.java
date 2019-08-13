package ru.yakovlev.gui;

import ru.yakovlev.Coordinate;
import ru.yakovlev.Images;
import ru.yakovlev.board.BoardProperties;
import ru.yakovlev.board.SimpleBoard;
import ru.yakovlev.board.events.MouseEventOnBoardListener;
import ru.yakovlev.board.events.NewGameListener;

import javax.swing.*;
import java.awt.*;

/**
 * @since 0.1
 */
public class BoardPanel extends JPanel
    implements NewGameListener, MouseEventOnBoardListener
{
    private final SimpleBoard board;
    private final Images images;
    private final int imageSize;

    public BoardPanel(final SimpleBoard board, final int imageSize) {
        this(board, new Images(), imageSize);
    }

    public BoardPanel(
        final SimpleBoard board, final Images images, final int imageSize
    ) {
        this.board = board;
        this.images = images;
        this.imageSize = imageSize;
    }

    @Override
    public final void newGame(final BoardProperties properties) {
        this.setPreferredSize(
            new Dimension(
                properties.width() * this.imageSize,
                properties.height() * this.imageSize
            )
        );
        this.repaint();
    }

    @Override
    protected final void paintComponent(final Graphics g) {
        super.paintComponent(g);
        this.board
            .cells()
            .forEach(
                cell -> {
                    final Coordinate coordinate = new FromBoardCoordinate(
                        cell.coordinate(), this.imageSize
                    );
                    g.drawImage(
                        cell.image(this.images).getImage(),
                        coordinate.getX(),
                        coordinate.getY(),
                        this.imageSize,
                        this.imageSize,
                        this
                    );
                }
        );
    }

    @Override
    public void mousePressedOnBoard() { }

    @Override
    public void mouseReleasedOnBoard() {
        this.repaint();
    }
}
