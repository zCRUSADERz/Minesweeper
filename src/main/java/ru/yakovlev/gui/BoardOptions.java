package ru.yakovlev.gui;

import ru.yakovlev.Observer;
import ru.yakovlev.board.BoardProperties;
import ru.yakovlev.board.events.NewGameListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.StringJoiner;
import java.util.function.Predicate;

/**
 * Фрейм для ввода новых параметров игрового поля.
 *
 * @since 0.1
 */
public class BoardOptions extends JFrame implements ActionListener {
    private final Observer<NewGameListener> observer;
    private final JTextField widthField = new JTextField();
    private final JTextField heightField = new JTextField();
    private final JTextField bombsField = new JTextField();

    public BoardOptions(final Observer<NewGameListener> observer) {
        this.observer = observer;
    }

    public final void init(final BoardProperties properties) {
        this.setLayout(new GridLayout(0, 2));
        this.add(new JLabel("Ширина"));
        this.widthField.setText(String.valueOf(properties.width()));
        this.add(this.widthField);
        this.add(new JLabel("Высота"));
        this.heightField.setText(String.valueOf(properties.height()));
        this.add(this.heightField);
        this.add(new JLabel("Число мин"));
        this.bombsField.setText(String.valueOf(properties.bombs()));
        this.add(this.bombsField);
        final JButton ok = new JButton("Ок");
        ok.addActionListener(this);
        final JButton cancel = new JButton("Отмена");
        cancel.addActionListener(event -> this.dispose());
        this.add(ok);
        this.add(cancel);
        pack();
        this.setVisible(true);
    }

    @Override
    public final void actionPerformed(final ActionEvent e) {
        final Predicate<String> digitValidator =
            text -> text.matches("^\\d{1,9}$");
        boolean error = true;
        if (digitValidator.test(this.widthField.getText())
            && digitValidator.test(this.heightField.getText())
            && digitValidator.test(this.bombsField.getText())) {
            final int newWidth = Integer.parseInt(this.widthField.getText());
            final int newHeight = Integer.parseInt(this.heightField.getText());
            final int newBombs = Integer.parseInt(this.bombsField.getText());
            if (
                newWidth >= 5 && newHeight >= 1 && newBombs >= 0
                    && (newWidth * newHeight) > newBombs
            ) {
                error = false;
                final BoardProperties newProperties =
                    new BoardProperties(newWidth, newHeight, newBombs);
                this.observer.apply(listener -> listener.newGame(newProperties));
                this.dispose();
            }
        }
        if (error) {
            showInfo();
        }
    }

    private void showInfo() {
        final StringJoiner builder = new StringJoiner(System.lineSeparator())
            .add("Условия:")
            .add("Ширина не менее 5")
            .add("Высота не менее 1")
            .add("Количество бомб не менее 0")
            .add("Количество бомб не должно превышать количество ячеек");
        JOptionPane.showMessageDialog(BoardOptions.this, builder.toString());
    }
}
