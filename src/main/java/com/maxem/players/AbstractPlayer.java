package com.maxem.players;

import com.maxem.game.PlayerType;

public abstract class AbstractPlayer implements Player {
    protected String name;

    protected Integer bestResult;
    protected PlayerType playerType;

    public AbstractPlayer(String name, PlayerType playerType) {
        this.name = name;
        this.playerType = playerType;
        this.bestResult = null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public PlayerType getPlayerType() {
        return playerType;
    }

    @Override
    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }
}
