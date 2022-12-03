package com.maxem.fieldutils.analyzer;

import com.maxem.field.cell.CellType;
import com.maxem.game.PlayerType;
import com.maxem.field.cell.Cell;
import com.maxem.field.Field;
import kotlin.Pair;

import java.util.ArrayList;

public class FieldAnalyzerImpl implements FieldAnalyzer {
    protected Field field;

    public FieldAnalyzerImpl(Field field) {
        this.field = field;
    }

    public ArrayList<Cell> getClosingCells(Integer i, Integer j, PlayerType currentPlayerType) {
        ArrayList<Cell> cellsForChange = new ArrayList<>();
        for (Pair<Integer, Integer> pair :
                getClosingCellsIndexes(i, j, currentPlayerType)) {
            cellsForChange.add(field.getCell(pair.component1(), pair.component2()));
        }
        return cellsForChange;
    }

    @Override
    public ArrayList<Pair<Integer, Integer>> getClosingCellsIndexes(Integer i, Integer j, PlayerType currentPlayerType) {
        ArrayList<Pair<Integer, Integer>> indexes = new ArrayList<>();

        if (field.getCell(i, j).getCellType() != CellType.NONE && field.getCell(i, j).getCellType() != CellType.NEXT_MOVE) {
            return indexes;
        }

        // right closure
        for (int jj = j + 1; jj < field.getFieldSize(); ++jj) {
            if (field.getCell(i, jj).getCellType() == currentPlayerType.toCellType()) {
                for (int jjj = j + 1; jjj < jj; ++jjj) {
                    indexes.add(new Pair<>(i, jjj));
                }
                break;
            } else if (field.getCell(i, jj).getCellType() != currentPlayerType.another().toCellType()) {
                break;
            }
        }

        // left closure
        for (int jj = j - 1; jj >= 0; --jj) {
            if (field.getCell(i, jj).getCellType() == currentPlayerType.toCellType()) {
                for (int jjj = j - 1; jjj > jj; --jjj) {
                    indexes.add(new Pair<>(i, jjj));
                }
                break;
            } else if (field.getCell(i, jj).getCellType() != currentPlayerType.another().toCellType()) {
                break;
            }
        }

        // up closure
        for (int ii = i - 1; ii >= 0; --ii) {
            if (field.getCell(ii, j).getCellType() == currentPlayerType.toCellType()) {
                for (int iii = i - 1; iii > ii; --iii) {
                    indexes.add(new Pair<>(iii, j));
                }
                break;
            } else if (field.getCell(ii, j).getCellType() != currentPlayerType.another().toCellType()) {
                break;
            }
        }

        // down closure
        for (int ii = i + 1; ii < field.getFieldSize(); ++ii) {
            if (field.getCell(ii, j).getCellType() == currentPlayerType.toCellType()) {
                for (int iii = i + 1; iii < ii; ++iii) {
                    indexes.add(new Pair<>(iii, j));
                }
                break;
            } else if (field.getCell(ii, j).getCellType() != currentPlayerType.another().toCellType()) {
                break;
            }
        }

        // right down closure
        for (int ii = i + 1; ii < field.getFieldSize() && ii - i + j < field.getFieldSize(); ++ii) {
            if (field.getCell(ii, ii - i + j).getCellType() == currentPlayerType.toCellType()) {
                for (int iii = i + 1; iii < ii; ++iii) {
                    indexes.add(new Pair<>(iii, iii - i + j));
                }
                break;
            } else if (field.getCell(ii, ii - i + j).getCellType() != currentPlayerType.another().toCellType()) {
                break;
            }
        }

        // left down closure
        for (int ii = i + 1; ii < field.getFieldSize() && j - (ii - i) >= 0; ++ii) {
            if (field.getCell(ii, j - (ii - i)).getCellType() == currentPlayerType.toCellType()) {
                for (int iii = i + 1; iii < ii; ++iii) {
                    indexes.add(new Pair<>(iii, j - (iii - i)));
                }
                break;
            } else if (field.getCell(ii, j - (ii - i)).getCellType() != currentPlayerType.another().toCellType()) {
                break;
            }
        }

        // left up closure
        for (int ii = i - 1; ii >= 0 && j - (i - ii) >= 0; --ii) {
            if (field.getCell(ii, j - (i - ii)).getCellType() == currentPlayerType.toCellType()) {
                for (int iii = i - 1; iii > ii; --iii) {
                    indexes.add(new Pair<>(iii, j - (i - iii)));
                }
                break;
            } else if (field.getCell(ii, j - (i - ii)).getCellType() != currentPlayerType.another().toCellType()) {
                break;
            }
        }

        // right up closure
        for (int ii = i - 1; ii >= 0 && j + (i - ii) < field.getFieldSize(); --ii) {
            if (field.getCell(ii, j + (i - ii)).getCellType() == currentPlayerType.toCellType()) {
                for (int iii = i - 1; iii > ii; --iii) {
                    indexes.add(new Pair<>(iii, j + (i - iii)));
                }
                break;
            } else if (field.getCell(ii, j + (i - ii)).getCellType() != currentPlayerType.another().toCellType()) {
                break;
            }
        }

        return indexes;
    }

    @Override
    public Field getField() {
        return field;
    }
}
