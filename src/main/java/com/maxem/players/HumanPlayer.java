package com.maxem.players;

import com.maxem.game.PlayerType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class HumanPlayer extends AbstractPlayer {
    protected Scanner scanner;

    protected BufferedReader br;

    public HumanPlayer(String name, PlayerType playerType) {
        super(name, playerType);
        scanner = new Scanner(System.in);
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public ActionInfo getNextAction() {
        boolean correctInput = false;
        int first = -1, second = -1;
        while (!correctInput) {
            try {
                String inp = br.readLine();
                if (inp.equals("назад")) { // TODO printer get func
                    return new ActionInfo(ActionInfo.ActionType.CANCEL_MOVE);
                }
                if (inp.length() != 2) {
                    System.out.println("Необходимо ввести два символа. Попробуйте ещё раз");
                    continue;
                }
                first = inp.charAt(1) - '0';
                if (first < 1 || first > 8) {
                    System.out.println("Цифра должна быть в диапазоне от  1 до 8 включительно. Попробуйте ещё раз");
                    continue;
                }
                first--;
                char secondChar = inp.charAt(0);
                if (secondChar < 'a' || secondChar > 'h') {
                    System.out.println("Буква должна быть в диапазоне от 'a' до 'h' включительно. Попробуйте ещё раз");
                    continue;
                }
                second = secondChar - 'a';
                correctInput = true;
            } catch (Exception e) {
                System.out.println("неправильный ввод. Попробуйте ещё раз");
            }
        }
        return new ActionInfo(ActionInfo.ActionType.MOVE, first, second);
    }
}
