package com.maxem.fieldutils;
import com.maxem.players.Player;
import com.maxem.field.PrintableField;
import com.maxem.field.Field;

public class FieldPrinterImpl implements FieldPrinter {
    PrintableField field;
    public FieldPrinterImpl(Field field) {
        this.field = field;
    }

    @Override
    public void printField() {
        System.out.print("  | ");
        for (int i = 0; i < field.getFieldSize(); i++) {
            System.out.print((char) ('a' + i));
            System.out.print(" | ");
        }
        System.out.println();
        System.out.println("-----------------------------------");
        for (int i = 0; i < field.getFieldSize(); i++) {
            System.out.print(i + 1);
            System.out.print(" | ");
            for (int j = 0; j < field.getFieldSize(); j++) {
                System.out.print(field.getPrintableCell(i, j).getCellType().getValue());
                System.out.print(" | ");
            }
            System.out.println();
            System.out.println("-----------------------------------");
        }

    }
}
