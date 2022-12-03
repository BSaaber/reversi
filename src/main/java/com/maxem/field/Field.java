package com.maxem.field;

import com.maxem.field.cell.Cell;

public interface Field extends PrintableField {

    Cell getCell(Integer i, Integer j);

    void copyFrom(Field field);

    void clear();
}
