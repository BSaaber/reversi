package com.maxem.game.printer;

import com.maxem.fieldutils.MoveResult;
import com.maxem.game.PlayerType;
import com.maxem.players.Player;

public class PrinterImpl implements Printer {
    @Override
    public void startGameMessage() {
        System.out.println("Игра началась!");
    }

    @Override
    public void endGameMessage(MoveResult moveResult) {
        String result = switch (moveResult) {
            case DRAW -> "ничья";
            case BLACK_WINS -> "победил чёрный";
            case WHITE_WINS -> "победил белый";
            case CONTINUE -> throw new RuntimeException("Game can not end with continue result");
        };
        System.out.printf("Игра окончена! Результат: %s%n", result);
    }

    @Override
    public void introducingMoveMessage(Player player) {
        String color = switch (player.getPlayerType()) {
            case BLACK_PLAYER -> "черный";
            case WHITE_PLAYER -> "белый";
        };
        System.out.printf("Ход игрока: %s (%s)\nВведите ход в формате '<буква><цифра>' (например: 'a4')%n", player.getName(), color);
        System.out.println("Вы можете отменить предыдущий ход командой 'назад'");
    }

    @Override
    public void badInputMessage() {
        System.out.println("Некорректный ввод - попробуйте ещё раз");
    }

    @Override
    public void badMoveMessage() {
        System.out.println("В данную клетку нельзя сделать ход!");
    }

    @Override
    public void unableToUndoMove() {
        System.out.println("невозможно отменить ход");
    }

    @Override
    public void bestResult(String name, int result) {
        System.out.println("лучший результат игрока " + name + " " + result);
    }

    @Override
    public void gamePoints(PlayerType playerType, int result) {
        String playerTypeWord;
        if (playerType == PlayerType.WHITE_PLAYER) {
            playerTypeWord = "белого";
        } else if (playerType == PlayerType.BLACK_PLAYER) {
            playerTypeWord = "черного";
        } else {
            throw new RuntimeException("player type is not black and not white");
        }
        System.out.println("очки " + playerTypeWord + " игрока за эту игру: " + result);
    }

    @Override
    public void chooseGameMode() {
        System.out.println("""
                Выберите режим игры:
                1 - игрок против комьютера-новичка
                2 - игрок против компьютера-мастера
                3 - игрок против игрока
                4 - компьютер-новичок против компьютера-мастера
                5 - компьютер-новичок против компьютера-новичка
                6 - компьютер-мастер против компьютера-мастера""");
    }

    @Override
    public void numberIsNotBetween(int left, int right) {
        System.out.println("число должно быть от " + left + " до " + right + " включительно");
    }

    @Override
    public void signInOrSignUp() {
        System.out.println("Войти в аккаунт или создать новый?");
        System.out.println("1 - войти");
        System.out.println("2 - создать");
    }

    @Override
    public void enterPlayerNameMessage() {
        System.out.println("Введите имя игрока");
    }

    @Override
    public void playerMove(Player player, int i, int j) {
        System.out.printf("Игрок %s совершил ход на клетку %c%d%n",
                player.getName(), (char) ('a' + j), i + 1);
    }
}
