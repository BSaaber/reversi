package com.maxem.fieldutils.controller;

import com.maxem.fieldutils.analyzer.FieldAnalyzer;

public interface FieldControllerFactory {
    FieldController buildFieldController(FieldAnalyzer fieldAnalyzer);
}
