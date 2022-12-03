package com.maxem.fieldutils.controller;

import com.maxem.fieldutils.MoveResult;
import com.maxem.game.PlayerType;

public interface FieldController {
    MoveResult makeMove(Integer i, Integer j);

    PlayerType getCurrentPlayerType();

    boolean checkMoveCorrectness(Integer i, Integer j);

    boolean undoMove(int undoAmount);

    void setUpStartPosition();

    int countPoints(PlayerType playerType);
}
