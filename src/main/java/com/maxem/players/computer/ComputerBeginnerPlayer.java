package com.maxem.players.computer;

import com.maxem.field.PrintableField;
import com.maxem.fieldutils.analyzer.FieldAnalyzerBuilder;
import com.maxem.game.PlayerType;
import com.maxem.players.computer.price_field.FieldPriceMask;

public class ComputerBeginnerPlayer extends ComputerPlayer {


    public ComputerBeginnerPlayer(PlayerType playerType, PrintableField field, FieldAnalyzerBuilder builder, FieldPriceMask fieldPriceMask) {
        super(playerType, field, builder, fieldPriceMask);
    }

    @Override
    public ActionInfo getNextAction() {
        setUpCopy();
        return findBestMove(playerType).component1();
    }
}
