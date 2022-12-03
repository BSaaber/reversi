package com.maxem;

import com.maxem.field.Field;
import com.maxem.field.FieldImpl;
import com.maxem.field.cell.Cell;
import com.maxem.field.cell.CellBuilderImpl;
import com.maxem.field.cell.CellType;
import com.maxem.fieldutils.analyzer.FieldAnalyzerImpl;
import com.maxem.game.PlayerType;
import com.maxem.testutils.FieldChecker;
import com.maxem.testutils.TestCell;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class FieldAnalyzerImplTest {
    FieldAnalyzerImpl fieldAnalyzer;
    Field field;
    FieldChecker fieldChecker;

    @BeforeClass
    public void build() {
        field = new FieldImpl(8, new CellBuilderImpl());
        fieldChecker = new FieldChecker(field);
    }

    @BeforeMethod
    public void setUp() {
        fieldChecker.setUp();
        field.clear();
        fieldAnalyzer = new FieldAnalyzerImpl(field);
    }

    @Test
    public void filledField() {
        for (int i = 0; i < field.getFieldSize(); i++) {
            for (int j = 0; j < field.getFieldSize(); j++) {
                field.getCell(i, j).setCellType(CellType.BLACK);
            }
        }

        for (int i = 0; i < field.getFieldSize(); i++) {
            for (int j = 0; j < field.getFieldSize(); j++) {
                Assert.assertTrue(fieldAnalyzer.getClosingCells(i, j, PlayerType.BLACK_PLAYER).isEmpty());
                Assert.assertTrue(fieldAnalyzer.getClosingCells(i, j, PlayerType.WHITE_PLAYER).isEmpty());
            }
        }
    }

    @Test
    public void rightClosure() {
        field.getCell(4,3).setCellType(CellType.BLACK);
        field.getCell(4,2).setCellType(CellType.WHITE);
        field.getCell(4,1).setCellType(CellType.WHITE);
        for (Cell cell: fieldAnalyzer.getClosingCells(4, 0, PlayerType.BLACK_PLAYER)) {
            cell.setCellType(CellType.BLACK);
        }
        fieldChecker.checkField(new TestCell[]{
                new TestCell(4,2, CellType.BLACK),
                new TestCell(4, 1, CellType.BLACK),
                new TestCell(4, 3, CellType.BLACK)
        });
    }


    @Test
    public void leftClosure() {
        field.getCell(4,3).setCellType(CellType.BLACK);
        field.getCell(4,4).setCellType(CellType.WHITE);
        field.getCell(4,5).setCellType(CellType.WHITE);
        field.getCell(4,6).setCellType(CellType.WHITE);
        for (Cell cell: fieldAnalyzer.getClosingCells(4, 7, PlayerType.BLACK_PLAYER)) {
            cell.setCellType(CellType.BLACK);
        }
        fieldChecker.checkField(new TestCell[]{
                new TestCell(4,4, CellType.BLACK),
                new TestCell(4, 5, CellType.BLACK),
                new TestCell(4, 3, CellType.BLACK),
                new TestCell(4, 6, CellType.BLACK)
        });
    }


    @Test
    public void downClosure() {
        field.getCell(4,3).setCellType(CellType.BLACK);
        field.getCell(3,3).setCellType(CellType.WHITE);
        field.getCell(2,3).setCellType(CellType.WHITE);
        field.getCell(1,3).setCellType(CellType.WHITE);
        for (Cell cell: fieldAnalyzer.getClosingCells(0, 3, PlayerType.BLACK_PLAYER)) {
            cell.setCellType(CellType.BLACK);
        }
        fieldChecker.checkField(new TestCell[]{
                new TestCell(4,3, CellType.BLACK),
                new TestCell(3, 3, CellType.BLACK),
                new TestCell(2, 3, CellType.BLACK),
                new TestCell(1, 3, CellType.BLACK)
        });
    }


    @Test
    public void upClosure() {
        field.getCell(4,3).setCellType(CellType.BLACK);
        field.getCell(5,3).setCellType(CellType.WHITE);
        field.getCell(6,3).setCellType(CellType.WHITE);
        for (Cell cell: fieldAnalyzer.getClosingCells(7, 3, PlayerType.BLACK_PLAYER)) {
            cell.setCellType(CellType.BLACK);
        }
        fieldChecker.checkField(new TestCell[]{
                new TestCell(4,3, CellType.BLACK),
                new TestCell(5, 3, CellType.BLACK),
                new TestCell(6, 3, CellType.BLACK),
        });
    }


    @Test
    public void rightDownClosure() {
        field.getCell(4,3).setCellType(CellType.BLACK);
        field.getCell(3,2).setCellType(CellType.WHITE);
        field.getCell(2,1).setCellType(CellType.WHITE);
        for (Cell cell: fieldAnalyzer.getClosingCells(1, 0, PlayerType.BLACK_PLAYER)) {
            cell.setCellType(CellType.BLACK);
        }
        fieldChecker.checkField(new TestCell[]{
                new TestCell(4,3, CellType.BLACK),
                new TestCell(3, 2, CellType.BLACK),
                new TestCell(2, 1, CellType.BLACK),
        });
    }


    @Test
    public void leftDownClosure() {
        field.getCell(4,3).setCellType(CellType.BLACK);
        field.getCell(3,4).setCellType(CellType.WHITE);
        field.getCell(2,5).setCellType(CellType.WHITE);
        field.getCell(1,6).setCellType(CellType.WHITE);
        for (Cell cell: fieldAnalyzer.getClosingCells(0, 7, PlayerType.BLACK_PLAYER)) {
            cell.setCellType(CellType.BLACK);
        }
        fieldChecker.checkField(new TestCell[]{
                new TestCell(4,3, CellType.BLACK),
                new TestCell(3, 4, CellType.BLACK),
                new TestCell(2, 5, CellType.BLACK),
                new TestCell(1, 6, CellType.BLACK),
        });
    }


    @Test
    public void leftUpClosure() {
        field.getCell(4,3).setCellType(CellType.BLACK);
        field.getCell(5,4).setCellType(CellType.WHITE);
        field.getCell(6,5).setCellType(CellType.WHITE);
        for (Cell cell: fieldAnalyzer.getClosingCells(7, 6, PlayerType.BLACK_PLAYER)) {
            cell.setCellType(CellType.BLACK);
        }
        fieldChecker.checkField(new TestCell[]{
                new TestCell(4,3, CellType.BLACK),
                new TestCell(5, 4, CellType.BLACK),
                new TestCell(6, 5, CellType.BLACK),
        });
    }


    @Test
    public void rightUpClosure() {
        field.getCell(4,3).setCellType(CellType.BLACK);
        field.getCell(5,2).setCellType(CellType.WHITE);
        field.getCell(6,1).setCellType(CellType.WHITE);
        for (Cell cell: fieldAnalyzer.getClosingCells(7, 0, PlayerType.BLACK_PLAYER)) {
            cell.setCellType(CellType.BLACK);
        }
        fieldChecker.checkField(new TestCell[]{
                new TestCell(4,3, CellType.BLACK),
                new TestCell(5, 2, CellType.BLACK),
                new TestCell(6, 1, CellType.BLACK),
        });
    }


    @Test
    public void allSidesClosure() {
        field.getCell(2,1).setCellType(CellType.WHITE);
        field.getCell(3,2).setCellType(CellType.WHITE);
        field.getCell(4,1).setCellType(CellType.WHITE);
        field.getCell(4,2).setCellType(CellType.WHITE);
        field.getCell(5,2).setCellType(CellType.WHITE);
        field.getCell(6,1).setCellType(CellType.WHITE);
        field.getCell(1,3).setCellType(CellType.WHITE);
        field.getCell(2,3).setCellType(CellType.WHITE);
        field.getCell(3,3).setCellType(CellType.WHITE);
        field.getCell(2,5).setCellType(CellType.WHITE);
        field.getCell(5,3).setCellType(CellType.WHITE);
        field.getCell(6,3).setCellType(CellType.WHITE);
        field.getCell(3,4).setCellType(CellType.WHITE);
        field.getCell(4,4).setCellType(CellType.WHITE);
        field.getCell(5,4).setCellType(CellType.WHITE);
        field.getCell(2,5).setCellType(CellType.WHITE);
        field.getCell(4,5).setCellType(CellType.WHITE);
        field.getCell(6,5).setCellType(CellType.WHITE);
        field.getCell(1,6).setCellType(CellType.WHITE);
        field.getCell(4,6).setCellType(CellType.WHITE);

        field.getCell(1,0).setCellType(CellType.BLACK);
        field.getCell(4,0).setCellType(CellType.BLACK);
        field.getCell(7,0).setCellType(CellType.BLACK);
        field.getCell(0,3).setCellType(CellType.BLACK);
        field.getCell(7,3).setCellType(CellType.BLACK);
        field.getCell(0,7).setCellType(CellType.BLACK);
        field.getCell(4,7).setCellType(CellType.BLACK);
        field.getCell(7,6).setCellType(CellType.BLACK);


        for (Cell cell: fieldAnalyzer.getClosingCells(4, 3, PlayerType.BLACK_PLAYER)) {
            cell.setCellType(CellType.BLACK);
        }
        fieldChecker.checkField(new TestCell[]{
                new TestCell(2,1, CellType.BLACK),
                new TestCell(3,2, CellType.BLACK),
                new TestCell(4,1, CellType.BLACK),
                new TestCell(4,2, CellType.BLACK),
                new TestCell(5,2, CellType.BLACK),
                new TestCell(6,1, CellType.BLACK),
                new TestCell(1,3, CellType.BLACK),
                new TestCell(2,3, CellType.BLACK),
                new TestCell(3,3, CellType.BLACK),
                new TestCell(2,5, CellType.BLACK),
                new TestCell(5,3, CellType.BLACK),
                new TestCell(6,3, CellType.BLACK),
                new TestCell(3,4, CellType.BLACK),
                new TestCell(4,4, CellType.BLACK),
                new TestCell(5,4, CellType.BLACK),
                new TestCell(2,5, CellType.BLACK),
                new TestCell(4,5, CellType.BLACK),
                new TestCell(6,5, CellType.BLACK),
                new TestCell(1,6, CellType.BLACK),
                new TestCell(4,6, CellType.BLACK),

                new TestCell(1,0, CellType.BLACK),
                new TestCell(4,0, CellType.BLACK),
                new TestCell(7,0, CellType.BLACK),
                new TestCell(0,3, CellType.BLACK),
                new TestCell(7,3, CellType.BLACK),
                new TestCell(0,7, CellType.BLACK),
                new TestCell(4,7, CellType.BLACK),
                new TestCell(7,6, CellType.BLACK),
        });
    }
}
