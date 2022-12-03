package com.maxem.players.computer.price_field.price_mask_cell;

public interface PriceMaskCellFactory {
    PriceMaskCell buildPriceMaskCell(int closingPrice, double placingPrice);
}
