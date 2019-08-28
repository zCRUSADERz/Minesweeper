package ru.yakovlev.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.yakovlev.Main;
import ru.yakovlev.InitializingComponent;
import ru.yakovlev.Observer;
import ru.yakovlev.board.BoardImpl;
import ru.yakovlev.board.BoardProperties;
import ru.yakovlev.board.BoardState;
import ru.yakovlev.board.events.DefeatListener;
import ru.yakovlev.board.events.FlagEventListener;
import ru.yakovlev.board.events.GameFinishedListener;
import ru.yakovlev.board.events.MouseEventOnBoardListener;
import ru.yakovlev.gui.*;
import ru.yakovlev.gui.listeners.BoardListener;
import ru.yakovlev.gui.listeners.NewGameListener;
import ru.yakovlev.gui.listeners.NewGameSpecialListener;
import ru.yakovlev.gui.listeners.NewGameWithPropsListener;
import ru.yakovlev.gui.timer.TimerField;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

/**
 * @since 0.1.1
 */
@Configuration
@ComponentScan(basePackages = "ru.yakovlev")
public class ApplicationConfig {
    @Autowired private Menu menu;
    @Autowired private TimerField timerField;
    @Autowired private ResetButton resetButton;
    @Autowired private BombsField bombsField;
    @Autowired private InfoPanel infoPanel;
    @Autowired private BoardImpl boardImpl;
    @Autowired private BoardState boardState;
    @Autowired private BoardPanel boardPanel;
    @Autowired private GameFrame gameFrame;
    @Autowired private BoardListener boardListener;
    @Autowired private NewGameListener newGameListener;
    @Autowired private NewGameSpecialListener newGameSpecialListener;
    @Autowired private Main main;

    @Bean
    public BoardProperties newbieBoardProperties() {
        return new BoardProperties(9, 9, 10);
    }

    @Bean
    public JMenuItem newGameMenuItem() {
        return new JMenuItem();
    }

    @Bean
    public JMenuItem newbieMenuItem() {
        return new JMenuItem();
    }

    @Bean
    public JMenuItem fanMenuItem() {
        return new JMenuItem();
    }

    @Bean
    public JMenuItem professionalMenuItem() {
        return new JMenuItem();
    }

    @Bean
    public JMenuItem specialMenuItem() {
        return new JMenuItem();
    }

    @Bean
    public Observer<DefeatListener> defeatObserver(
        final List<DefeatListener> listeners
    ) {
        return new Observer<>(listeners, DefeatListener.class);
    }

    @Bean
    public Observer<FlagEventListener> flagEventObserver(
        final List<FlagEventListener> listeners
    ) {
        return new Observer<>(listeners, FlagEventListener.class);
    }

    @Bean
    public Observer<GameFinishedListener> gameFinishedObserver(
        final List<GameFinishedListener> listeners
    ) {
        return new Observer<>(listeners, GameFinishedListener.class);
    }

    @Bean
    public Observer<MouseEventOnBoardListener> mouseEventOnBoardObserver(
        final List<MouseEventOnBoardListener> listeners
    ) {
        return new Observer<>(listeners, MouseEventOnBoardListener.class);
    }

    /**
     * Для данного наблюдателя важен порядок в котором слушатели будут получать
     * оповещения.
     * @return слушатель событий начала новой игры.
     */
    @Bean
    public Observer<ru.yakovlev.board.events.NewGameListener> newGameObserver() {
        return new Observer<>(
            Arrays.asList(
                this.boardImpl, this.boardState, this.resetButton,
                this.bombsField, this.boardPanel, this.timerField,
                this.gameFrame
            ),
            ru.yakovlev.board.events.NewGameListener.class
        );
    }

    @Bean
    public NewGameWithPropsListener newbieMenuItemListener() {
        return new NewGameWithPropsListener(
            this.newbieMenuItem(),
            this.newbieBoardProperties(),
            this.newGameObserver()
        );
    }

    @Bean
    public NewGameWithPropsListener fanMenuItemListener() {
        return new NewGameWithPropsListener(
            this.fanMenuItem(),
            new BoardProperties(16, 16, 40),
            this.newGameObserver()
        );
    }

    @Bean
    public NewGameWithPropsListener professionalMenuItemListener() {
        return new NewGameWithPropsListener(
            this.professionalMenuItem(),
            new BoardProperties(30, 16, 99),
            this.newGameObserver()
        );
    }

    /**
     * Важен порядок компонентов требующих инициализации.
     * В данном порядке выполнится инициализация.
     * @return список компонентов, которым необходима инициализация.
     */
    @Bean
    public List<InitializingComponent> initializingComponents() {
        return Arrays.asList(
            this.menu, this.resetButton, this.timerField, this.bombsField,
            this.infoPanel, this.gameFrame, this.newGameListener,
            this.newbieMenuItemListener(), this.fanMenuItemListener(),
            this.professionalMenuItemListener(), this.newGameSpecialListener,
            this.boardListener, this.main
        );
    }
}
