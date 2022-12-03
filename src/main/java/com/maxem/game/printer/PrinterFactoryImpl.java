package com.maxem.game.printer;

public class PrinterFactoryImpl implements PrinterFactory {
    @Override
    public Printer buildPrinter() {
        return new PrinterImpl();
    }
}
