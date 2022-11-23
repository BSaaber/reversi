package com.maxem;

import com.maxem.field.Field;
import com.maxem.field.cell.CellBuilderImpl;
import com.maxem.field.FieldImpl;
import com.maxem.field.cell.CellType;
import com.maxem.fieldutils.*;
import com.maxem.game.PlayerType;
import kotlin.Pair;
import org.testng.Assert;
import org.testng.annotations.*;

public class FieldControllerImplTest {

    private class TestCell {
        int i;
        int j;
        CellType cellType;
        TestCell(int i, int j, CellType cellType) {
            this.i = i;
            this.j = j;
            this.cellType = cellType;
        }
    }
    Field field;
    FieldAnalyzer fieldAnalyzer;
    FieldControllerImpl fieldController;

    Field testField;

    @BeforeMethod
    public void setUpController() {
        field = new FieldImpl(8, new CellBuilderImpl());
        fieldAnalyzer = new FieldAnalyzerImpl(field);
        fieldController = new FieldControllerImpl(field, fieldAnalyzer);
        testField = new FieldImpl(8, new CellBuilderImpl());
    }

    private void checkField(TestCell[] expected) {
        for (TestCell cell:
             expected) {
            testField.getCell(cell.i, cell.j).setCellType(cell.cellType);
        }
        for (int i = 0; i < testField.getFieldSize(); i++) {
            for (int j = 0; j < testField.getFieldSize(); j++) {
                Assert.assertEquals(field.getCell(i, j).getCellType(), testField.getCell(i, j).getCellType(), String.format("Problem: %d %d", i, j));
            }
        }
    }


    /*@DataProvider
    public Object[][] fieldControllerInitializer() {

        return new Object[][] {
                {}
        };
    }*/

    @Test
    public void fieldControllerCreation() {
        Assert.assertEquals(fieldController.getCurrentPlayerType(), PlayerType.BLACK_PLAYER);
        //PrinterImpl printer = new PrinterImpl(field);
        //printer.printField();
        checkField(new TestCell[]{
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

    //@Test(dataProvider = "fieldControllerInitializer")
    //public void makeSimpleMove(FieldController fieldController) {
    //    Assert.assertEquals(fieldController.getCurrentPlayerType(), PlayerType.BLACK_PLAYER);
    //}

}
