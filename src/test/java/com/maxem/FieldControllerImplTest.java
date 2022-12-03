package com.maxem;

import com.maxem.field.Field;
import com.maxem.field.cell.CellFactoryImpl;
import com.maxem.field.FieldImpl;
import com.maxem.field.cell.CellType;
import com.maxem.fieldutils.*;
import com.maxem.fieldutils.analyzer.FieldAnalyzer;
import com.maxem.fieldutils.analyzer.FieldAnalyzerImpl;
import com.maxem.fieldutils.controller.FieldControllerImpl;
import com.maxem.game.PlayerType;
import com.maxem.testutils.FieldChecker;
import com.maxem.testutils.TestCell;
import org.testng.Assert;
import org.testng.annotations.*;

public class FieldControllerImplTest {
    Field field;
    FieldAnalyzer fieldAnalyzer;
    FieldControllerImpl fieldController;

    GameHistory gameHistory;

    FieldChecker fieldChecker;

    @BeforeClass
    public void build() {
        field = new FieldImpl(8, new CellFactoryImpl());
        fieldChecker = new FieldChecker(field);
    }

    @BeforeMethod
    public void setUp() {
        field.clear();
        fieldChecker.setUp();
        fieldAnalyzer = new FieldAnalyzerImpl(field);
        gameHistory = new GameHistoryImpl(field);
        fieldController = new FieldControllerImpl(field, fieldAnalyzer, gameHistory);
    }

    @Test
    public void setUpStartPosition() {
        Assert.assertEquals(fieldController.getCurrentPlayerType(), PlayerType.BLACK_PLAYER);
        fieldController.setUpStartPosition();
        fieldChecker.checkField(new TestCell[]{
                new TestCell(4, 4, CellType.WHITE),
                new TestCell(3, 3, CellType.WHITE),
                new TestCell(4, 3, CellType.BLACK),
                new TestCell(3, 4, CellType.BLACK),
                new TestCell(2, 3, CellType.NEXT_MOVE),
                new TestCell(3, 2, CellType.NEXT_MOVE),
                new TestCell(5, 4, CellType.NEXT_MOVE),
                new TestCell(4, 5, CellType.NEXT_MOVE),
        });
    }
}
