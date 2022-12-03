package com.maxem.fieldutils.controller;

import com.maxem.fieldutils.MoveResult;
import com.maxem.game.GameMode;
import com.maxem.game.PlayerType;

public interface FieldController {
    public MoveResult makeMove(Integer i, Integer j);

    public PlayerType getCurrentPlayerType();

    public boolean checkMoveCorrectness(Integer i, Integer j);

    public boolean undoMove(int undoAmount);

    public void setUpStartPosition();

    public int countPoints(PlayerType playerType);
}
