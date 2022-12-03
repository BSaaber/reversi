package com.maxem.players.computer.price_field;

import com.maxem.players.computer.price_field.price_mask_cell.PriceMaskCell;

public interface FieldPriceMask {
    PriceMaskCell getPriceMaskCell(Integer i, Integer j);
}
