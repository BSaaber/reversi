package com.maxem.game.printer;

import com.maxem.fieldutils.MoveResult;
import com.maxem.game.PlayerType;
import com.maxem.players.Player;

public interface Printer {
    void startGameMessage();

    void endGameMessage(MoveResult moveResult);

    void introducingMoveMessage(Player player);

    void badInputMessage();

    void playerMove(Player player, int i, int j);

    void badMoveMessage();

    void unableToUndoMove();

    void bestResult(String name, int result);

    void gamePoints(PlayerType playerType, int result);

    void chooseGameMode();

    void numberIsNotBetween(int left, int right);

    void signInOrSignUp();

    void enterPlayerNameMessage();
}
