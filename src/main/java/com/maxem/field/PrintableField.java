package com.maxem.field;

import com.maxem.field.cell.PrintableCell;

public interface PrintableField {
    public Integer getFieldSize();

    public PrintableCell getPrintableCell(Integer i, Integer j);

    Field copy();
}
