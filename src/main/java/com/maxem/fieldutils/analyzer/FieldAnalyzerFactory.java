package com.maxem.fieldutils.analyzer;

import com.maxem.field.Field;

public interface FieldAnalyzerFactory {
    FieldAnalyzer buildFieldAnalyzer(Field field);
}
