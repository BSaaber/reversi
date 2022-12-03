package com.maxem.players.computer.price_field;

import com.maxem.players.computer.price_field.price_mask_cell.PriceMaskCellBuilderImpl;

public class FieldPriceMaskBuilderImpl implements FieldPriceMaskBuilder {
    @Override
    public FieldPriceMask buildFieldPriceMask(int size) {
        return new FieldPriceMaskImpl(size, new PriceMaskCellBuilderImpl());
    }
}
