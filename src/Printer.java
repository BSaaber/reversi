import kotlin.Pair;

public interface Printer {
    public void printStartMessage();
    public void printEndGameMessage(MoveResult moveResult);

    public void printField();

    public void printIntroducingMoveMessage(Player player);

    public void printBadMoveMessage();

    public void printPlayerMove(Player player, Pair<Integer, Integer> move);
}
