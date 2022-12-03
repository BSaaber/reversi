package com.maxem;

import com.maxem.field.cell.Cell;
import com.maxem.field.cell.CellImpl;
import com.maxem.field.cell.CellType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CellImplTest {
    CellImpl cell;
    @BeforeMethod
    public void init() {
        cell = new CellImpl(CellType.NONE);
    }

    @Test
    public void constructCellAndGetType() {
        Assert.assertEquals(cell.getCellType(), CellType.NONE);
    }

    @Test
    public void setCellType() {
        cell.setCellType(CellType.BLACK);
        Assert.assertEquals(cell.getCellType(), CellType.BLACK);
    }
}
