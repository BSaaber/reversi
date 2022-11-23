package com.maxem.players;
import kotlin.Pair;

public interface Player {

    public Pair<Integer, Integer> getNextMove();

    public String getName();
}
