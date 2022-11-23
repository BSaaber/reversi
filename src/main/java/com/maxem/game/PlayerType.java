package com.maxem.game;

import com.maxem.field.cell.CellType;

public enum PlayerType {
    WHITE_PLAYER,
    BLACK_PLAYER;

    public CellType toCellType() {
        return switch (this) {
            case BLACK_PLAYER -> CellType.BLACK;
            case WHITE_PLAYER -> CellType.WHITE;
        };
    }

    public PlayerType another() {
        return switch (this) {
            case WHITE_PLAYER -> BLACK_PLAYER;
            case BLACK_PLAYER -> WHITE_PLAYER;
        };
    }
}
