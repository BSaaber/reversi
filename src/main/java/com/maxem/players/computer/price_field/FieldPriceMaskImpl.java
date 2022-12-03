package com.maxem.players.computer.price_field;

import com.maxem.players.computer.price_field.price_mask_cell.PriceMaskCell;
import com.maxem.players.computer.price_field.price_mask_cell.PriceMaskCellFactory;

public class FieldPriceMaskImpl implements FieldPriceMask {
    protected PriceMaskCell[][] prices;

    public FieldPriceMaskImpl(int size, PriceMaskCellFactory priceMaskCellFactory) {
        prices = new PriceMaskCell[size][size];

        // middle
        for (int i = 1; i < size - 1; i++) {
            for (int j = 0; j < size - 1; j++) {
                prices[i][j] = priceMaskCellFactory.buildPriceMaskCell(1, 0);
            }
        }

        // corners
        prices[0][0] = priceMaskCellFactory.buildPriceMaskCell(-999, 0.8);
        prices[0][size - 1] = priceMaskCellFactory.buildPriceMaskCell(-999, 0.8);
        prices[size - 1][size - 1] = priceMaskCellFactory.buildPriceMaskCell(-999, 0.8);
        prices[size - 1][0] = priceMaskCellFactory.buildPriceMaskCell(-999, 0.8);

        // borders
        for (int i = 1; i < size - 1; i++) {
            prices[0][i] = priceMaskCellFactory.buildPriceMaskCell(2, 0.4);
            prices[i][0] = priceMaskCellFactory.buildPriceMaskCell(2, 0.4);
            prices[size - 1][i] = priceMaskCellFactory.buildPriceMaskCell(2, 0.4);
            prices[i][size - 1] = priceMaskCellFactory.buildPriceMaskCell(2, 0.4);
        }
    }

    @Override
    public PriceMaskCell getPriceMaskCell(Integer i, Integer j) {
        return prices[i][j];
    }
}
