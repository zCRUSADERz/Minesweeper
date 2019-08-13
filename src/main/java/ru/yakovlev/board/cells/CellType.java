package ru.yakovlev.board.cells;

import ru.yakovlev.board.BoardCoordinate;

/**
 * Все возможные типы ячеек.
 *
 * @since 0.1
 */
public enum CellType implements CellFactory {

    UN_OPENED {
        @Override
        public final Cell cell(final BoardCoordinate coordinate, final int bombsAround) {
            final Cell result;
            if (bombsAround == 0) {
                result = new UnopenedSafeCell(coordinate);
            } else {
                result = new UnOpenedCellNearTheBomb(coordinate);
            }
            return result;
        }
    },

    UN_OPENED_BOMB {
        @Override
        public Cell cell(final BoardCoordinate coordinate, final int bombsAround) {
            return new UnOpenedBomb(coordinate);
        }
    },

    FLAG {
        @Override
        public Cell cell(final BoardCoordinate coordinate, final int bombsAround) {
            return new Flag(coordinate);
        }
    },

    FLAG_ON_BOMB {
        @Override
        public Cell cell(final BoardCoordinate coordinate, final int bombsAround) {
            return new FlagOnBomb(coordinate);
        }
    },

    OPENED {
        @Override
        public Cell cell(final BoardCoordinate coordinate, final int bombsAround) {
            final Cell result;
            if (bombsAround == 0) {
                result = new OpenedSafe(coordinate);
            } else {
                result = new OpenedCellNearTheBomb(coordinate, bombsAround);
            }
            return result;
        }
    },

    BOMB {
        @Override
        public Cell cell(final BoardCoordinate coordinate, final int bombsAround) {
            return new Bomb(coordinate);
        }
    },

    /**
     * Ошибочно поставленный флаг.
     */
    NO_BOMB {
        @Override
        public Cell cell(final BoardCoordinate coordinate, final int bombsAround) {
            return new NoBomb(coordinate);
        }
    },

    EXPLODED_BOMB {
        @Override
        public Cell cell(final BoardCoordinate coordinate, final int bombsAround) {
            return new ExplodedBomb(coordinate);
        }
    }
}
