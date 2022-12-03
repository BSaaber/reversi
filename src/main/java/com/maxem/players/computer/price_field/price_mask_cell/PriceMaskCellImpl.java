package com.maxem.players.computer.price_field.price_mask_cell;

public class PriceMaskCellImpl implements PriceMaskCell {
    protected final int closingPrice;
    protected final double placingPrice;

    public PriceMaskCellImpl(int closingPrice, double placingPrice) {
        this.closingPrice = closingPrice;
        this.placingPrice = placingPrice;
    }


    @Override
    public Integer getClosingPrice() {
        return closingPrice;
    }

    @Override
    public double getPlacingPrice() {
        return placingPrice;
    }
}
