import kotlin.Pair;

public class GameManagerImpl implements GameManager {
    GameController gameController;
    Printer printer;
    Player whitePlayer;
    Player blackPlayer;
    GameManagerImpl(GameController gameController, Printer printer, Player whitePlayer, Player blackPlayer) {
        this.gameController = gameController;
        this.printer = printer;
        this.whitePlayer = whitePlayer;
        this.blackPlayer = blackPlayer;
    }

    @Override
    public void Play() {
        MoveResult moveResult = MoveResult.CONTINUE;
        PlayerType playerType = gameController.getCurrentPlayerType();
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
            if (!gameController.checkMoveCorrectness(nextMove.component1(), nextMove.component2())) {
                printer.printBadMoveMessage();
            }
            printer.printPlayerMove(currentPlayer, nextMove);
            moveResult = gameController.makeMove(nextMove.component1(), nextMove.component2());
            printer.printField();
        }
        printer.printEndGameMessage(moveResult); // need player?
    }
}
