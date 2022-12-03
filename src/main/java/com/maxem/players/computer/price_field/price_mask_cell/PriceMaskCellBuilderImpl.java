package com.maxem.players.computer.price_field.price_mask_cell;

public class PriceMaskCellBuilderImpl implements PriceMaskCellBuilder {
    @Override
    public PriceMaskCell buildPriceMaskCell(int closingPrice, double placingPrice) {
        return new PriceMaskCellImpl(closingPrice, placingPrice);
    }
}
