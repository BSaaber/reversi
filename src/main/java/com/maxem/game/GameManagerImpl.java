package com.maxem.game;

import com.maxem.fieldutils.controller.FieldController;
import com.maxem.fieldutils.MoveResult;
import com.maxem.fieldutils.FieldPrinter;
import com.maxem.game.printer.Printer;
import com.maxem.players.Player;

public class GameManagerImpl implements GameManager {
    FieldController fieldController;
    FieldPrinter fieldPrinter;
    Player whitePlayer;
    Player blackPlayer;
    GameMode gameMode;

    Printer printer;

    GameManagerImpl(FieldController fieldController, FieldPrinter fieldPrinter, Player whitePlayer, Player blackPlayer, GameMode gameMode, Printer printer) {
        this.fieldController = fieldController;
        this.fieldPrinter = fieldPrinter;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
        this.printer = printer;
        this.gameMode = gameMode;
    }

    @Override
    public GameStatistic Play() {
        MoveResult moveResult = MoveResult.CONTINUE;
        PlayerType playerType;
        Player currentPlayer;
        Player.ActionInfo nextAction;
        printer.startGameMessage();
        fieldController.setUpStartPosition();
        fieldPrinter.printField();
        while (moveResult == MoveResult.CONTINUE) {
            playerType = fieldController.getCurrentPlayerType();
            currentPlayer = switch (playerType) {
                case WHITE_PLAYER -> whitePlayer;
                case BLACK_PLAYER -> blackPlayer;
            };
            printer.introducingMoveMessage(currentPlayer);
            while (true) {
                nextAction = currentPlayer.getNextAction();
                if (nextAction.getActionType() == Player.ActionInfo.ActionType.CANCEL_MOVE) {
                    int undoAmount = -1;
                    switch (gameMode) {
                        case PLAYER_VS_PLAYER -> undoAmount = 1;
                        case PLAYER_VS_EASY_COMPUTER,
                                PLAYER_VS_HARD_COMPUTER -> undoAmount = 2;
                        case EASY_COMPUTER_VS_HARD_COMPUTER,
                                EASY_COMPUTER_VS_EASY_COMPUTER,
                                HARD_COMPUTER_VS_HARD_COMPUTER -> {
                            throw new RuntimeException("unexpected: computer tried to undo move. Skynet ?)");
                        }

                    }
                    if (fieldController.undoMove(undoAmount)) {
                        break;
                    } else {
                        printer.unableToUndoMove();
                    }

                } else {
                    if (fieldController.checkMoveCorrectness(nextAction.getI(), nextAction.getJ())) {
                        printer.playerMove(currentPlayer, nextAction.getI(), nextAction.getJ());
                        moveResult = fieldController.makeMove(nextAction.getI(), nextAction.getJ());
                        break;
                    } else {
                        printer.badMoveMessage();
                    }
                }
            }
            fieldPrinter.printField();
        }
        printer.endGameMessage(moveResult);
        return new GameStatistic(
                fieldController.countPoints(PlayerType.WHITE_PLAYER),
                fieldController.countPoints(PlayerType.BLACK_PLAYER)
        );
    }
}
