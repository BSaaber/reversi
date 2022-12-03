package com.maxem.field;

import com.maxem.field.cell.Cell;
import com.maxem.field.cell.CellFactory;
import com.maxem.field.cell.CellType;
import com.maxem.field.cell.ImmutableCell;

public class FieldImpl implements Field {
    protected final Cell[][] cells;

    protected CellFactory cellFactory;

    public FieldImpl(Integer fieldSize, CellFactory cellFactory) {
        this.cellFactory = cellFactory;
        if (fieldSize < 4) {
            throw new RuntimeException("field size is too small");
        }
        if (fieldSize % 2 != 0) {
            throw new RuntimeException("field size is not even");
        }
        cells = new Cell[fieldSize][fieldSize];

        for (int i = 0; i < fieldSize; ++i) {
            for (int j = 0; j < fieldSize; j++) {
                cells[i][j] = cellFactory.buildCell(CellType.NONE);
            }
        }
    }

    @Override
    public Integer getFieldSize() {
        return cells.length;
    }

    @Override
    public ImmutableCell getPrintableCell(Integer i, Integer j) {
        return cells[i][j];
    }

    @Override
    public Cell getCell(Integer i, Integer j) {
        return cells[i][j];
    }

    @Override
    public Field copy() {
        Field ret = new FieldImpl(cells.length, cellFactory);
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                ret.getCell(i, j).setCellType(cells[i][j].getCellType());
            }
        }
        return ret;
    }

    @Override
    public void copyFrom(Field field) {
        if (cells.length != field.getFieldSize()) {
            throw new RuntimeException("tried to copy from field with another size");
        }
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j].setCellType(field.getCell(i, j).getCellType());
            }
        }
    }

    @Override
    public void clear() {
        for (Cell[] cell : cells) {
            for (int j = 0; j < cells.length; j++) {
                cell[j].setCellType(CellType.NONE);
            }
        }
    }
}
