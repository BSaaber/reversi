package com.maxem.players.computer;

import com.maxem.field.PrintableField;
import com.maxem.fieldutils.controller.FieldController;
import com.maxem.fieldutils.analyzer.FieldAnalyzerBuilder;
import com.maxem.fieldutils.controller.FieldControllerBuilder;
import com.maxem.game.GameMode;
import com.maxem.game.PlayerType;
import com.maxem.players.computer.price_field.FieldPriceMask;

public class ComputerMasterPlayer extends ComputerPlayer {
    FieldControllerBuilder fieldControllerBuilder;
    FieldController fieldController;

    public ComputerMasterPlayer(PlayerType playerType, PrintableField field, FieldAnalyzerBuilder builder, FieldPriceMask fieldPriceMask, FieldControllerBuilder fieldControllerBuilder) {
        super(playerType, field, builder, fieldPriceMask);
        this.fieldControllerBuilder = fieldControllerBuilder;
    }

    @Override
    protected void setUpCopy() {
        super.setUpCopy();
        fieldController = fieldControllerBuilder.buildFieldController(fieldAnalyzer);
    }

    @Override
    public ActionInfo getNextAction() {
        setUpCopy();
        int bestMoveI = -1, bestMoveJ = -1;
        double maximum = -9999;
        double tempMaximum;
        for (int i = 0; i < field.getFieldSize(); i++) {
            for (int j = 0; j < field.getFieldSize(); j++) {
                tempMaximum = priceEstimation(fieldAnalyzer.getClosingCellsIndexes(i, j, playerType),i, j);
                if (tempMaximum >= 1) {
                    fieldController.makeMove(i, j);
                    tempMaximum -= findBestMove(playerType.another()).component2();
                    fieldController.undoMove(1);
                }
                if (tempMaximum > maximum) {
                    maximum = tempMaximum;
                    bestMoveI = i;
                    bestMoveJ = j;
                }
            }
        }
        return new ActionInfo(ActionInfo.ActionType.MOVE, bestMoveI, bestMoveJ);
    }
}
