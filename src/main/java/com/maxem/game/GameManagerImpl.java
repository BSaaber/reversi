package com.maxem.game;
import com.maxem.fieldutils.FieldController;
import com.maxem.fieldutils.MoveResult;
import com.maxem.fieldutils.Printer;
import com.maxem.players.Player;
import kotlin.Pair;

public class GameManagerImpl implements GameManager {
    FieldController fieldController;
    Printer printer;
    Player whitePlayer;
    Player blackPlayer;
    GameManagerImpl(FieldController fieldController, Printer printer, Player whitePlayer, Player blackPlayer) {
        this.fieldController = fieldController;
        this.printer = printer;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    @Override
    public void Play() {
        MoveResult moveResult = MoveResult.CONTINUE;
        PlayerType playerType = fieldController.getCurrentPlayerType();
        Player currentPlayer;
        Pair<Integer, Integer> nextMove;
        printer.printStartMessage();
        printer.printField();
        while (moveResult == MoveResult.CONTINUE) {
            currentPlayer =  switch (playerType) {
                case WHITE_PLAYER -> whitePlayer;
                case BLACK_PLAYER -> blackPlayer;
            };
            printer.printIntroducingMoveMessage(currentPlayer);
            nextMove = currentPlayer.getNextMove();
            if (!fieldController.checkMoveCorrectness(nextMove.component1(), nextMove.component2())) {
                printer.printBadMoveMessage();
            }
            printer.printPlayerMove(currentPlayer, nextMove);
            moveResult = fieldController.makeMove(nextMove.component1(), nextMove.component2());
            printer.printField();
        }
        printer.printEndGameMessage(moveResult); // need player?
    }
}
