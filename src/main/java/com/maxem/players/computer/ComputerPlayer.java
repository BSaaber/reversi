package com.maxem.players.computer;

import com.maxem.players.Player;
import kotlin.Pair;

public class ComputerPlayer implements Player {
    @Override
    public Pair<Integer, Integer> getNextMove() {
        return null;
    }

    @Override
    public String getName() {
        return "компьютер";
    }
}
