import kotlin.Pair;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HumanPlayer implements Player {
    String name;
    Scanner scanner;
    HumanPlayer(String name) {
        this.name = name;
        scanner = new Scanner(System.in);
    }
    @Override
    public Pair<Integer, Integer> getNextMove() {
        Integer first = scanner.nextInt() - 1;
        Integer second = scanner.nextInt() - 1;
        return new Pair<Integer, Integer>(first, second);
    }

    @Override
    public String getName() {
        return null;
    }
}
