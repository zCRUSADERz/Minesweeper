package ru.yakovlev.gui;

import ru.yakovlev.InitializingComponent;

import javax.swing.*;

/**
 * Игровое меню.
 *
 * @since 0.1
 */
public class Menu extends JMenuBar implements InitializingComponent {
    private final JMenuItem newGame;
    private final JMenuItem newbie;
    private final JMenuItem fan;
    private final JMenuItem professional;
    private final JMenuItem special;

    public Menu(
        final JMenuItem newGame, final JMenuItem newbie, final JMenuItem fan,
        final JMenuItem professional, final JMenuItem special
    ) {
        this.newGame = newGame;
        this.newbie = newbie;
        this.fan = fan;
        this.professional = professional;
        this.special = special;
    }

    public final void init() {
        final JMenu menu = this.add(new JMenu("Игра"));
        this.newGame.setText("Начать новую игру");
        this.newbie.setText("Новичок");
        this.fan.setText("Любитель");
        this.professional.setText("Профессионал");
        this.special.setText("Особый");
        menu.add(this.newGame);
        menu.addSeparator();
        menu.add(this.newbie);
        menu.add(this.fan);
        menu.add(this.professional);
        menu.add(this.special);
        menu.addSeparator();
        menu
            .add("Выход")
            .addActionListener(event -> System.exit(0));
    }
}
