package com.maxem;

import com.maxem.game.AppManager;

public class Main {

    public static void main(String[] args) {
        AppManager appManager = new AppManager();
        while (true) {
            try {
                appManager.start();
            } catch (Exception e) {
                System.out.println("Произошла ошибка:");
                System.out.println(e.getMessage());
                System.out.println("Пожалуйста, начните заново");
                e.printStackTrace();
                System.out.println("===================================================");
                System.out.println("\n\n\n");
            }
        }
    }
}