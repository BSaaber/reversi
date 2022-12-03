package com.maxem.fieldutils.controller;

import com.maxem.field.Field;
import com.maxem.fieldutils.analyzer.FieldAnalyzer;

public interface FieldControllerBuilder {
    FieldController buildFieldController(Field field);

    FieldController buildFieldController(FieldAnalyzer fieldAnalyzer);
}
