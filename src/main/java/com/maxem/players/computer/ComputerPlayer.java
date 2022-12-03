package com.maxem.players.computer;

import com.maxem.field.Field;
import com.maxem.field.PrintableField;
import com.maxem.fieldutils.analyzer.FieldAnalyzer;
import com.maxem.fieldutils.analyzer.FieldAnalyzerBuilder;
import com.maxem.game.PlayerType;
import com.maxem.players.AbstractPlayer;
import com.maxem.players.computer.price_field.FieldPriceMask;
import kotlin.Pair;

import java.util.ArrayList;

public abstract class ComputerPlayer extends AbstractPlayer {
    protected PrintableField field;
    protected Field fieldCopy;
    protected FieldAnalyzer fieldAnalyzer;
    protected FieldAnalyzerBuilder fieldAnalyzerBuilder;

    FieldPriceMask fieldPriceMask;
    public ComputerPlayer(PlayerType playerType, PrintableField field, FieldAnalyzerBuilder builder, FieldPriceMask fieldPriceMask) {
        super("компьютер", playerType);
        this.field = field;
        this.fieldAnalyzerBuilder = builder;
        this.fieldPriceMask = fieldPriceMask;
    }

    protected void setUpCopy() {
        this.fieldCopy = field.copy();
        this.fieldAnalyzer = fieldAnalyzerBuilder.buildFieldAnalyzer(fieldCopy);
    }

    protected double priceEstimation(ArrayList<Pair<Integer, Integer>> closingCellsIndexes, int moveI, int moveJ) {
        if (closingCellsIndexes.isEmpty()) {
            return -999;
        }
        double ans = 0;
        for (Pair<Integer, Integer> pair :
                closingCellsIndexes) {
            ans += fieldPriceMask.getPriceMaskCell(pair.component1(), pair.component2()).getClosingPrice();
        }
        ans += fieldPriceMask.getPriceMaskCell(moveI, moveJ).getPlacingPrice();
        return ans;
    }

    protected Pair<ActionInfo, Double> findBestMove(PlayerType playerType) {
        int bestMoveI = -1, bestMoveJ = -1;
        double maximum = -1;
        double tempMaximum;
        for (int i = 0; i < field.getFieldSize(); i++) {
            for (int j = 0; j < field.getFieldSize(); j++) {
                tempMaximum = priceEstimation(fieldAnalyzer.getClosingCellsIndexes(i, j, playerType),i, j);
                if (tempMaximum > maximum) {
                    maximum = tempMaximum;
                    bestMoveI = i;
                    bestMoveJ = j;
                }
            }
        }
        return new Pair<>(new ActionInfo(ActionInfo.ActionType.MOVE, bestMoveI, bestMoveJ), maximum);
    }
}
