package ru.yakovlev.gui;

import ru.yakovlev.InitializingComponent;
import ru.yakovlev.board.BoardProperties;
import ru.yakovlev.board.events.NewGameListener;

import javax.swing.*;
import java.awt.*;

/**
 * Основной фрейм приложения.
 *
 * @since 0.1
 */
public class GameFrame extends JFrame
    implements NewGameListener, InitializingComponent
{
    private final Menu menu;
    private final InfoPanel infoPanel;
    private final BoardPanel board;

    public GameFrame(
        final Menu menu, final InfoPanel panel, final BoardPanel board
    ) {
        this.menu = menu;
        this.infoPanel = panel;
        this.board = board;
    }

    @Override
    public final void init() {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setIconImage(
            new ImageIcon(
                getClass().getResource("/img/icon.png")
            ).getImage()
        );
        this.setLocationRelativeTo(null);
        this.setJMenuBar(this.menu);
        this.add(this.infoPanel, BorderLayout.NORTH);
        final JPanel border = new JPanel();
        border.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        border.setBorder(
            BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLoweredBevelBorder()
            )
        );
        border.add(this.board);
        this.add(border, BorderLayout.CENTER);
    }


    @Override
    public final void newGame(final BoardProperties properties) {
        this.pack();
        this.setVisible(true);
    }
}
