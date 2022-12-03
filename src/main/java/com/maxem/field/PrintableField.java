package com.maxem.field;

import com.maxem.field.cell.ImmutableCell;

public interface PrintableField {
    Integer getFieldSize();

    ImmutableCell getPrintableCell(Integer i, Integer j);

    Field copy();
}
