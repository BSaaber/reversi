package com.maxem.field;

import com.maxem.field.cell.ImmutableCell;

public interface PrintableField {
    public Integer getFieldSize();

    public ImmutableCell getPrintableCell(Integer i, Integer j);

    Field copy();
}
