public interface GameController {
    public MoveResult makeMove(Integer i, Integer j);

    public PlayerType getCurrentPlayerType();

    public boolean checkMoveCorrectness(Integer i, Integer j);
}
