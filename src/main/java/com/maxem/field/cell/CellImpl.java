package com.maxem.field.cell;

public class CellImpl implements Cell {
    private CellType cellType;

    public CellImpl(CellType cellType) {
        this.cellType = cellType;
    }

    @Override
    public CellType getCellType() {
        return cellType;
    }

    public void setCellType(CellType cellType) {
        this.cellType = cellType;
    }

}
