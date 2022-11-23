package com.maxem.fieldutils;

import com.maxem.game.PlayerType;

public interface FieldController {
    public MoveResult makeMove(Integer i, Integer j);

    public PlayerType getCurrentPlayerType();

    public boolean checkMoveCorrectness(Integer i, Integer j);
}
