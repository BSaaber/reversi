package com.maxem;

import com.maxem.field.cell.CellBuilderImpl;
import com.maxem.field.cell.CellType;
import com.maxem.field.FieldImpl;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FieldImplTest {

    @DataProvider
    public Object[][] FieldInitializer() {
        return new Object[][] {{new FieldImpl(8, new CellBuilderImpl()), 8}};
    }

    @Test(dataProvider = "FieldInitializer")
    public void CreateField(FieldImpl field, int fieldSize) {
        Assert.assertEquals(field.getFieldSize(), fieldSize);
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                Assert.assertEquals(field.getCell(i, j).getCellType(), CellType.NONE);
            }
        }
    }

    @Test(dataProvider = "FieldInitializer")
    public void EditCellType(FieldImpl field, int fieldSize) {
        field.getCell(0, 0).setCellType(CellType.BLACK);
        for (int i = 0; i < fieldSize; i++) {
            for (int j = 0; j < fieldSize; j++) {
                if (i != 0 || j != 0) {
                    Assert.assertEquals(field.getCell(i, j).getCellType(), CellType.NONE);
                } else {
                    Assert.assertEquals(field.getCell(i, j).getCellType(), CellType.BLACK);
                }
            }
        }
    }
}
