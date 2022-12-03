package com.maxem.fieldutils.analyzer;
import com.maxem.field.Field;
import com.maxem.game.PlayerType;
import com.maxem.field.cell.Cell;
import kotlin.Pair;

import java.util.ArrayList;

public interface FieldAnalyzer {
    public ArrayList<Cell> getClosingCells(Integer i, Integer j, PlayerType currentPlayerType);

    public ArrayList<Pair<Integer, Integer>> getClosingCellsIndexes(Integer i, Integer j, PlayerType currentPlayerType);

    public Field getField();

}
