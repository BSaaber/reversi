package com.maxem.players.computer;

import com.maxem.field.cell.PriceMaskCell;

// todo помним, что Field не реализует этот интерфейс
public interface FieldPriceMask {
    public PriceMaskCell getPriceMaskCell(Integer i, Integer j);
}
