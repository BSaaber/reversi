package com.maxem.fieldutils;

import com.maxem.game.PlayerType;

public interface GameHistory {
    void appendFieldSnapshot(PlayerType playerType);

    PlayerType cancelMove();

    boolean haveOneMove();

    boolean haveTwoMoves();
}
