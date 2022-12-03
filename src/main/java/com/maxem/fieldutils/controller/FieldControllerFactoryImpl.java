package com.maxem.fieldutils.controller;

import com.maxem.fieldutils.GameHistoryImpl;
import com.maxem.fieldutils.analyzer.FieldAnalyzer;

public class FieldControllerFactoryImpl implements FieldControllerFactory {

    public FieldController buildFieldController(FieldAnalyzer fieldAnalyzer) {
        return new FieldControllerImpl(fieldAnalyzer.getField(), fieldAnalyzer, new GameHistoryImpl(fieldAnalyzer.getField()));
    }
}
