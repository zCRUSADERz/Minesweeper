package ru.yakovlev;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Ищет необходимые иконки в classpath.
 *
 * @since 0.1
 */
public class Images {
    private Map<String, ImageIcon> images = new HashMap<>();

    public final ImageIcon image(final String imageName) {
        return this.images.computeIfAbsent(
            imageName,
            new Function<String, ImageIcon>() {
                @Override
                public ImageIcon apply(final String s) {
                    return new ImageIcon(
                        getClass().getResource(String.format("/img/%s.png", s))
                    );
                }
            }
        );
    }
}
