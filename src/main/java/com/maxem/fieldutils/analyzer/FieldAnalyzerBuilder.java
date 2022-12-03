package com.maxem.fieldutils.analyzer;

import com.maxem.field.Field;

public interface FieldAnalyzerBuilder {
    FieldAnalyzer buildFieldAnalyzer(Field field);
}
