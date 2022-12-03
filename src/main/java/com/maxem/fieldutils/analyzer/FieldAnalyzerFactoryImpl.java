package com.maxem.fieldutils.analyzer;

import com.maxem.field.Field;

public class FieldAnalyzerFactoryImpl implements FieldAnalyzerFactory {
    @Override
    public FieldAnalyzer buildFieldAnalyzer(Field field) {
        return new FieldAnalyzerImpl(field);
    }
}
