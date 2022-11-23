package com.maxem.fieldutils;
import com.maxem.game.PlayerType;
import com.maxem.field.cell.Cell;
import com.maxem.field.Field;

import java.util.ArrayList;

public class FieldAnalyzerImpl implements FieldAnalyzer {
    Field field;
    public FieldAnalyzerImpl(Field field) {
        this.field = field;
    }

    // reuse data
    public ArrayList<Cell> getClosingCells(Integer i, Integer j, PlayerType currentPlayerType) {
        Cell cell = field.getCell(i, j);
        //cell.setCellType(currentPlayerType.toCellType());
        ArrayList<Cell> cellsForChange = new ArrayList<>();

        // right closure
        for (int jj = j + 1; jj < field.getFieldSize(); ++jj) {
            if (field.getCell(i, jj).getCellType() == currentPlayerType.toCellType()) {
                for (int jjj = j + 1; jjj < jj; ++jjj) {
                    cellsForChange.add(field.getCell(i, jjj));
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
                    cellsForChange.add(field.getCell(i, jjj));
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
                    cellsForChange.add(field.getCell(iii, j));
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
                    cellsForChange.add(field.getCell(iii, j));
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
                    cellsForChange.add(field.getCell(iii, iii - i + j));
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
                    cellsForChange.add(field.getCell(iii, j - (ii - i)));
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
                    cellsForChange.add(field.getCell(iii, j - (i - ii)));
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
                    cellsForChange.add(field.getCell(iii, j + (i - ii)));
                }
                break;
            } else if (field.getCell(ii, j + (i - ii)).getCellType() != currentPlayerType.another().toCellType()) {
                break;
            }
        }

        return cellsForChange;
    }
}
