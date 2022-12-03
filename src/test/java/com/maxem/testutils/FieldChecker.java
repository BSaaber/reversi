package com.maxem.testutils;

import com.maxem.field.Field;
import com.maxem.field.FieldImpl;
import com.maxem.field.cell.CellFactoryImpl;
import org.testng.Assert;

public class FieldChecker {
    Field field, testField;
    public FieldChecker(Field field) {
        this.field = field;
        this.testField = new FieldImpl(8, new CellFactoryImpl());
    }

    public void checkField(TestCell[] expected) {
        for (TestCell cell:
                expected) {
            testField.getCell(cell.i(), cell.j()).setCellType(cell.cellType());
        }
        for (int i = 0; i < testField.getFieldSize(); i++) {
            for (int j = 0; j < testField.getFieldSize(); j++) {
                Assert.assertEquals(field.getCell(i, j).getCellType(), testField.getCell(i, j).getCellType(), String.format("Problem: %d %d", i, j));
            }
        }
    }

    public void setUp() {
        testField.clear();
    }

    public Field getTestField() {
        return testField;
    }

}
