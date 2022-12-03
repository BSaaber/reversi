package com.maxem.players.computer.price_field;

import com.maxem.players.computer.price_field.price_mask_cell.PriceMaskCellFactoryImpl;

public class FieldPriceMaskFactoryImpl implements FieldPriceMaskFactory {
    @Override
    public FieldPriceMask buildFieldPriceMask(int size) {
        return new FieldPriceMaskImpl(size, new PriceMaskCellFactoryImpl());
    }
}
