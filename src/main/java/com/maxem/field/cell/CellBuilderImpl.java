package com.maxem.field.cell;

public class CellBuilderImpl implements CellBuilder {

    @Override
    public Cell buildCell(CellType type) {
        return new CellImpl(type);
    }
}
