package com.maxem.game.printer;

public class PrinterFactoryImpl implements PrinterFactory {
    @Override
    public Printer createPrinter() {
        return new PrinterImpl();
    }
}
