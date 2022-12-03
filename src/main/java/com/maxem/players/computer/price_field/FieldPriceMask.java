package com.maxem.players.computer.price_field;

import com.maxem.players.computer.price_field.price_mask_cell.PriceMaskCell;

// помним, что Field не реализует этот интерфейс
public interface FieldPriceMask {
    PriceMaskCell getPriceMaskCell(Integer i, Integer j);

    int getPriceMaskSize();
}
