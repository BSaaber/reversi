public class FieldImpl implements Field {
    private Cell[][] cells;

    public FieldImpl(Integer fieldSize, CellBuilder cellBuilder) {
        if (fieldSize < 4) {
            throw new RuntimeException("field size is too small");
        }
        if (fieldSize % 2 != 0) {
            throw new RuntimeException("field size is not even");
        }
        cells = new Cell[fieldSize][fieldSize];

        // corners
        cells[0][0] = cellBuilder.buildCell(CellType.NONE, -1);
        cells[fieldSize - 1][fieldSize - 1] = cellBuilder.buildCell(CellType.NONE, -1);
        cells[0][fieldSize - 1] = cellBuilder.buildCell(CellType.NONE, -1);
        cells[fieldSize - 1][0] = cellBuilder.buildCell(CellType.NONE, -1);

        // middle
        for (int i = 1; i < fieldSize - 1; ++i) {
            for (int j = 1; j < fieldSize - 1; ++j) {
                cells[i][j] = cellBuilder.buildCell(CellType.NONE, 1);
            }
        }

        // borders
        for (int i = 1; i < fieldSize - 1; ++i) {
            cells[0][i] = cellBuilder.buildCell(CellType.NONE, 2);
            cells[fieldSize - 1][i] = cellBuilder.buildCell(CellType.NONE, 2);
            cells[i][0] = cellBuilder.buildCell(CellType.NONE, 2);
            cells[i][fieldSize - 1] = cellBuilder.buildCell(CellType.NONE, 2);
        }

        // starting pieces
        cells[fieldSize / 2][fieldSize / 2].setCellType(CellType.WHITE);
        cells[-1 + fieldSize / 2][-1 + fieldSize / 2].setCellType(CellType.WHITE);
        cells[-1 + fieldSize / 2][fieldSize / 2].setCellType(CellType.BLACK);
        cells[fieldSize / 2][-1 + fieldSize / 2].setCellType(CellType.BLACK);
    }

    @Override
    public Integer getFieldSize() {
        return cells.length;
    }

    @Override
    public Cell getCell(Integer i, Integer j) {
        return null;
    }
}
