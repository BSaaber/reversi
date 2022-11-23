package com.maxem.game;
import com.maxem.game.AppManager;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        AppManager appManager = new AppManager();
        appManager.start();
    }
}