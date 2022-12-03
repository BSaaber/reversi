package com.maxem.players.account;

public class PlayerAccountImpl implements PlayerAccount {
    Integer bestResult;

    String name;

    public PlayerAccountImpl(String name) {
        bestResult = null;
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void updateBestResult(int result) {
        if (bestResult == null || result > bestResult) {
            bestResult = result;
        }
    }

    @Override
    public Integer getBestResult() {
        return bestResult;
    }
}
