package com.maxem.players;
import kotlin.Pair;

import java.util.Scanner;

public class HumanPlayer implements Player {
    String name;
    Scanner scanner;
    public HumanPlayer(String name) {
        this.name = name;
        scanner = new Scanner(System.in);
    }
    @Override
    public Pair<Integer, Integer> getNextMove() {
        Integer first = scanner.nextInt() - 1;
        char secondChar = scanner.next().charAt(0);
        if (secondChar < 'a' || secondChar > 'h') {
            throw new RuntimeException("странная буква");
        }
        Integer second = secondChar - 'a';
        return new Pair<Integer, Integer>(first, second);
    }

    @Override
    public String getName() {
        return name;
    }
}
