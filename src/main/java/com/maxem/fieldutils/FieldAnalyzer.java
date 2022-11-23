package com.maxem.fieldutils;
import com.maxem.game.PlayerType;
import com.maxem.field.cell.Cell;

import java.util.ArrayList;

public interface FieldAnalyzer {
    public ArrayList<Cell> getClosingCells(Integer i, Integer j, PlayerType currentPlayerType);


}
