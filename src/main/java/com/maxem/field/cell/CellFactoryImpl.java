package com.maxem.field.cell;

public class CellFactoryImpl implements CellFactory {

    @Override
    public Cell buildCell(CellType type) {
        return new CellImpl(type);
    }
}
