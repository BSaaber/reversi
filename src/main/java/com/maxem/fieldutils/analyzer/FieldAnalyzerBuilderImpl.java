package com.maxem.fieldutils.analyzer;

import com.maxem.field.Field;

public class FieldAnalyzerBuilderImpl implements FieldAnalyzerBuilder {
    @Override
    public FieldAnalyzer buildFieldAnalyzer(Field field) {
        return new FieldAnalyzerImpl(field);
    }
}
