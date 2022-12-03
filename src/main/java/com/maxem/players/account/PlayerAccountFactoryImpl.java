package com.maxem.players.account;

public class PlayerAccountFactoryImpl implements PlayerAccountFactory {
    @Override
    public PlayerAccount buildPlayerAccount(String name) {
        return new PlayerAccountImpl(name);
    }
}
