package com.maxem.field;

import com.maxem.field.cell.Cell;

public interface Field extends PrintableField {

    Cell getCell(Integer i, Integer j);


    default boolean checkCellExistence(Integer i, Integer j) {
        return i < getFieldSize() && j < getFieldSize() && i >= 0 && j >= 0;
    }

    void copyFrom(Field field);

    void clear();
}
