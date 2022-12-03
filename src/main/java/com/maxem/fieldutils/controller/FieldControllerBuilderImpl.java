package com.maxem.fieldutils.controller;

import com.maxem.field.Field;
import com.maxem.fieldutils.GameHistoryImpl;
import com.maxem.fieldutils.analyzer.FieldAnalyzer;
import com.maxem.fieldutils.analyzer.FieldAnalyzerImpl;

public class FieldControllerBuilderImpl implements FieldControllerBuilder {
    @Override
    public FieldController buildFieldController(Field field) {
        return new FieldControllerImpl(field, new FieldAnalyzerImpl(field), new GameHistoryImpl(field));
    }

    public FieldController buildFieldController(FieldAnalyzer fieldAnalyzer) {
        return new FieldControllerImpl(fieldAnalyzer.getField(), fieldAnalyzer, new GameHistoryImpl(fieldAnalyzer.getField()));
    }
}
