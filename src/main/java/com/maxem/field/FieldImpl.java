package com.maxem.field;

import com.maxem.field.cell.Cell;
import com.maxem.field.cell.CellBuilder;
import com.maxem.field.cell.CellType;
import com.maxem.field.cell.PrintableCell;

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

        for (int i = 0; i < fieldSize; ++i) {
            for (int j = 0; j < fieldSize; j++) {
                cells[i][j] = cellBuilder.buildCell(CellType.NONE);
            }
        }
    }

    @Override
    public Integer getFieldSize() {
        return cells.length;
    }

    @Override
    public PrintableCell getPrintableCell(Integer i, Integer j) {
        return cells[i][j];
    }

    @Override
    public Cell getCell(Integer i, Integer j) {
        return cells[i][j];
    }
}
