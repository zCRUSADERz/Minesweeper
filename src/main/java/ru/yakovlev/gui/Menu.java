package ru.yakovlev.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.yakovlev.InitializingComponent;

import javax.swing.*;

/**
 * Игровое меню.
 *
 * @since 0.1
 */
@Component
public class Menu extends JMenuBar implements InitializingComponent {
    private final JMenuItem newGame;
    private final JMenuItem newbie;
    private final JMenuItem fan;
    private final JMenuItem professional;
    private final JMenuItem special;

    @Autowired
    public Menu(
        final JMenuItem newGameItem, final JMenuItem newbieItem,
        final JMenuItem fanItem, final JMenuItem professionalItem,
        final JMenuItem specialItem
    ) {
        this.newGame = newGameItem;
        this.newbie = newbieItem;
        this.fan = fanItem;
        this.professional = professionalItem;
        this.special = specialItem;
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
