package com.maxem.fieldutils;
import com.maxem.players.Player;
import com.maxem.field.PrintableField;
import com.maxem.field.Field;
import kotlin.Pair;

public class PrinterImpl implements Printer {
    PrintableField field;
    public PrinterImpl(Field field) {
        this.field = field;
    }

    @Override
    public void printStartMessage() {
        System.out.println("Игра началась! Ход");
    }

    @Override
    public void printEndGameMessage(MoveResult moveResult) {
        String result = switch (moveResult) {
            case DRAW -> "ничья";
            case BLACK_WINS -> "победил чёрный";
            case WHITE_WINS -> "победил белый";
            case CONTINUE -> throw new RuntimeException("Game can not end with continue result");
        };
        System.out.println(String.format("Игра окончена! Результат: %s", result));
    }

    // TODO
    @Override
    public void printField() {
        System.out.print("  | ");
        for (int i = 0; i < field.getFieldSize(); i++) {
            System.out.print((char) ('a' + i));
            System.out.print(" | ");
        }
        System.out.println();
        System.out.println("-----------------------------------");
        for (int i = 0; i < field.getFieldSize(); i++) {
            System.out.print(i + 1);
            System.out.print(" | ");
            for (int j = 0; j < field.getFieldSize(); j++) {
                System.out.print(field.getPrintableCell(i, j).getCellType().getValue());
                System.out.print(" | ");
            }
            System.out.println();
            System.out.println("-----------------------------------");
        }

    }

    @Override
    public void printIntroducingMoveMessage(Player player) {
        System.out.println(String.format("Ход игрока: %s\nВведите букву и число", player.getName()));
    }

    @Override
    public void printBadMoveMessage() {
        System.out.println("В данную клетку нельзя сделать ход!");
    }

    @Override
    public void printPlayerMove(Player player, Pair<Integer, Integer> move) {
        System.out.println(String.format("Игрок %s совершил ход на клетку (%d, %d)",
                player.getName(), move.component1() + 1, move.component2() + 1));
    }
}
