package com.maxem.fieldutils;

import com.maxem.field.Field;
import com.maxem.game.PlayerType;
import kotlin.Pair;

import java.util.ArrayList;

public class GameHistoryImpl implements GameHistory {
    protected Field field;
    protected ArrayList<Pair<Field, PlayerType>> snapshots;

    public GameHistoryImpl(Field field) {
        this.field = field;
        snapshots = new ArrayList<>();
    }

    @Override
    public void appendFieldSnapshot(PlayerType playerType) {
        snapshots.add(new Pair<>(field.copy(), playerType));
    }

    @Override
    public PlayerType cancelMove() {
        Pair<Field, PlayerType> lastSnapshot = snapshots.get(snapshots.size() - 2);
        field.copyFrom(lastSnapshot.component1());
        snapshots.remove(snapshots.size() - 1);
        return lastSnapshot.component2();
    }

    @Override
    public boolean haveOneMove() {
        return snapshots.size() >= 2;
    }

    public boolean haveTwoMoves() {
        return snapshots.size() >= 3;
    }
}
