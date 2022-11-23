public class GameControllerImpl implements GameController {
    Field field;
    FieldAnalyzer fieldAnalyzer;
    PlayerType currentPlayerType;

    public GameControllerImpl(Field field, FieldAnalyzer fieldAnalyzer) {
        this.field = field;
        this.fieldAnalyzer = fieldAnalyzer;
        currentPlayerType = PlayerType.BLACK_PLAYER;
        updatePossibleMoves();
    }

    @Override
    public MoveResult makeMove(Integer i, Integer j) {
        makeMove_(i, j);
        removePossibleMoves();
        currentPlayerType = currentPlayerType.another();
        if (!updatePossibleMoves()) {
            currentPlayerType = currentPlayerType.another();
            if (!updatePossibleMoves()) {
                return findWinner();
            }
        }
        return MoveResult.CONTINUE;
    }

    public boolean checkMoveCorrectness(Integer i, Integer j) {
        return field.getCell(i, j).getCellType() == CellType.NEXT_MOVE;
    }

    public void makeMove_(Integer i, Integer j) {
        if (field.getCell(i, j).getCellType() != CellType.NEXT_MOVE) {
            throw new RuntimeException("Error: tried to make move to unexpected cell");
        }

        for (Cell cell : fieldAnalyzer.getClosingCells(i, j, currentPlayerType)) {
            cell.setCellType(currentPlayerType.toCellType());
        }
        field.getCell(i, j).setCellType(currentPlayerType.toCellType());
    }

    public boolean updatePossibleMoves() {
        boolean found = false;
        for (int i = 0; i < field.getFieldSize(); ++i) {
            for (int j = 0; j < field.getFieldSize(); ++j) {
                if (field.getCell(i, j).getCellType() == CellType.NONE) {
                    if (!fieldAnalyzer.getClosingCells(i, j, currentPlayerType).isEmpty()) {
                        field.getCell(i, j).setCellType(CellType.NEXT_MOVE);
                        found = true;
                    }
                }
            }
        }
        return found;
    }

    public void removePossibleMoves() {
        for (int i = 0; i < field.getFieldSize(); ++i) {
            for (int j = 0; j < field.getFieldSize(); ++j) {
                Cell cell = field.getCell(i, j);
                if (cell.getCellType() == CellType.NEXT_MOVE) {
                    cell.setCellType(CellType.NONE);
                }
            }
        }
    }

    public PlayerType getCurrentPlayerType() {
        return currentPlayerType;
    }

    public MoveResult findWinner() {
        int white_advantage = 0;
        for (int i = 0; i < field.getFieldSize(); ++i) {
            for (int j = 0; j < field.getFieldSize(); ++j) {
                Cell cell = field.getCell(i, j);
                if (cell.getCellType() == CellType.WHITE) {
                    white_advantage++;
                } else if (cell.getCellType() == CellType.BLACK) {
                    white_advantage--;
                }
            }
        }
        if (white_advantage > 0) {
            return MoveResult.WHITE_WINS;
        } else if (white_advantage < 0) {
            return MoveResult.BLACK_WINS;
        } else {
            return MoveResult.DRAW;
        }
    }


}
