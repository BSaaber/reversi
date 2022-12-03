package com.maxem.game;

import com.maxem.field.cell.CellBuilder;
import com.maxem.field.cell.CellBuilderImpl;
import com.maxem.field.Field;
import com.maxem.field.FieldImpl;
import com.maxem.fieldutils.*;
import com.maxem.fieldutils.analyzer.FieldAnalyzer;
import com.maxem.fieldutils.analyzer.FieldAnalyzerBuilder;
import com.maxem.fieldutils.analyzer.FieldAnalyzerBuilderImpl;
import com.maxem.fieldutils.analyzer.FieldAnalyzerImpl;
import com.maxem.fieldutils.controller.FieldController;
import com.maxem.fieldutils.controller.FieldControllerBuilder;
import com.maxem.fieldutils.controller.FieldControllerBuilderImpl;
import com.maxem.fieldutils.controller.FieldControllerImpl;
import com.maxem.game.printer.Printer;
import com.maxem.game.printer.PrinterFactory;
import com.maxem.game.printer.PrinterFactoryImpl;
import com.maxem.players.computer.ComputerBeginnerPlayer;
import com.maxem.players.computer.ComputerMasterPlayer;
import com.maxem.players.HumanPlayer;
import com.maxem.players.Player;
import com.maxem.players.computer.price_field.FieldPriceMaskBuilder;
import com.maxem.players.computer.price_field.FieldPriceMaskBuilderImpl;
import com.maxem.players.account.PlayerAccount;
import com.maxem.players.account.PlayerAccountFactory;
import com.maxem.players.account.PlayerAccountFactoryImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;

public class AppManager {
    CellBuilder cellBuilder;

    Field field;
    FieldAnalyzer fieldAnalyzer;
    FieldController fieldController;
    GameManager gameManager;
    FieldPrinter fieldPrinter;
    Player whitePlayer, blackPlayer;
    Scanner scanner;
    BufferedReader br;

    FieldAnalyzerBuilder fieldAnalyzerBuilder;

    GameHistory gameHistory;

    FieldPriceMaskBuilder fieldPriceMaskBuilder;

    FieldControllerBuilder fieldControllerBuilder;

    PlayerAccountFactory playerAccountFactory;

    HashMap<String, PlayerAccount> playerAccountDB = new HashMap<>();

    PlayerAccount whitePlayerAccount, blackPlayerAccount;

    PrinterFactory printerFactory;

    Printer printer;

    public AppManager() {
        scanner = new Scanner(System.in);
        br = new BufferedReader(new InputStreamReader(System.in));
        cellBuilder = new CellBuilderImpl();
        fieldAnalyzerBuilder = new FieldAnalyzerBuilderImpl();
        fieldPriceMaskBuilder = new FieldPriceMaskBuilderImpl();
        fieldControllerBuilder = new FieldControllerBuilderImpl();
        playerAccountFactory = new PlayerAccountFactoryImpl();
        printerFactory = new PrinterFactoryImpl();
        printer = printerFactory.createPrinter();
        createComputerAccounts();
    }

    // TODO add choosing color
    public void start() throws IOException {
        GameMode gameMode = selectGameMode();
        buildEnvironment();
        configurePlayers(gameMode);
        buildGameManager(gameMode);
        GameStatistic gameStatistic = gameManager.Play();
        updateStatistic(gameStatistic);
        printGameStatistic(gameStatistic);
        printAccountStatistic();
    }

    private void printAccountStatistic() {
        printer.bestResult(whitePlayerAccount.getName(), whitePlayerAccount.getBestResult());
        if (whitePlayerAccount != blackPlayerAccount) {
            printer.bestResult(blackPlayerAccount.getName(), blackPlayerAccount.getBestResult());
        }
    }

    private void printGameStatistic(GameStatistic gameStatistic) {
        printer.gamePoints(PlayerType.WHITE_PLAYER, gameStatistic.whitePlayerPointAmount());
        printer.gamePoints(PlayerType.BLACK_PLAYER, gameStatistic.blackPlayerPointAmount());
    }

    private void updateStatistic(GameStatistic gameStatistic) {
        whitePlayerAccount.updateBestResult(gameStatistic.whitePlayerPointAmount());
        blackPlayerAccount.updateBestResult(gameStatistic.blackPlayerPointAmount());
    }

    private void createComputerAccounts() {
        PlayerAccount account = playerAccountFactory.buildPlayerAccount("новичок");
        playerAccountDB.put(account.getName(), account);
        PlayerAccount anotherAccount = playerAccountFactory.buildPlayerAccount("мастер");
        playerAccountDB.put(anotherAccount.getName(), anotherAccount);
    }
    private GameMode selectGameMode() {
        printer.chooseGameMode();
        while (true) {
            if (scanner.hasNextInt()) {
                int gm = scanner.nextInt();
                int maxGmSize = 6;
                int minGmSize = 1;
                if (gm >= minGmSize && gm <= maxGmSize) {
                    return switch (gm) {
                        case 1 -> GameMode.PLAYER_VS_EASY_COMPUTER;
                        case 2 -> GameMode.PLAYER_VS_HARD_COMPUTER;
                        case 3 -> GameMode.PLAYER_VS_PLAYER;
                        case 4 -> GameMode.EASY_COMPUTER_VS_HARD_COMPUTER;
                        case 5 -> GameMode.EASY_COMPUTER_VS_EASY_COMPUTER;
                        case 6 -> GameMode.HARD_COMPUTER_VS_HARD_COMPUTER;
                        default -> throw new IllegalStateException("Unexpected gameMode value: " + gm);
                    };
                } else {
                    printer.numberIsNotBetween(minGmSize, maxGmSize);
                }
            }
            scanner.skip(".*\\n");
            printer.badInputMessage();
        }
    }

    private Player buildHumanPlayer(String name, PlayerType playerType) throws IOException {
        return new HumanPlayer(name, playerType);
    }

    private Player buildBeginnerPlayer(PlayerType playerType) {
        return new ComputerBeginnerPlayer(playerType, field, fieldAnalyzerBuilder, fieldPriceMaskBuilder.buildFieldPriceMask(field.getFieldSize()));
    }

    private Player buildMasterPlayer(PlayerType playerType) {
        return new ComputerMasterPlayer(playerType, field, fieldAnalyzerBuilder, fieldPriceMaskBuilder.buildFieldPriceMask(field.getFieldSize()), fieldControllerBuilder);
    }

    private PlayerAccount enterPlayerAccount() throws IOException {
        printer.signInOrSignUp();
        boolean signInOrSignUpChoosed = false;
        int gm = -1;
        while (!signInOrSignUpChoosed) {
            if (scanner.hasNextInt()) {
                gm = scanner.nextInt();
                if (gm == 1 || gm == 2) {
                    signInOrSignUpChoosed = true;
                    continue;
                }
            }
            scanner.skip(".*\\n");
            printer.badInputMessage();
        }
        if (gm == 1) {
            printer.enterPlayerNameMessage();
            String name;
            while (true) {
                name = br.readLine();
                if (playerAccountDB.containsKey(name)) {
                    return playerAccountDB.get(name);
                } else {
                    printer.badInputMessage();
                }
            }
        } else {
            printer.enterPlayerNameMessage();
            String name = br.readLine();
            PlayerAccount playerAccount = playerAccountFactory.buildPlayerAccount(name);
            playerAccountDB.put(playerAccount.getName(), playerAccount);
            return playerAccount;
        }
    }

    // it is possible when both players will be one person
    // not a bug, but a feature
    // it is needed, for example, if client will want to play with himself
    // otherwise he would need to create a copy of his account
    private void configurePlayers(GameMode gameMode) throws IOException {
        switch (gameMode) {
            case PLAYER_VS_PLAYER -> {
                whitePlayerAccount = enterPlayerAccount();
                blackPlayerAccount = enterPlayerAccount();
                whitePlayer = buildHumanPlayer(whitePlayerAccount.getName(), PlayerType.WHITE_PLAYER);
                blackPlayer = buildHumanPlayer(blackPlayerAccount.getName(), PlayerType.BLACK_PLAYER);
            }
            case PLAYER_VS_EASY_COMPUTER -> {
                whitePlayerAccount = enterPlayerAccount();
                whitePlayer = buildHumanPlayer(whitePlayerAccount.getName(), PlayerType.WHITE_PLAYER);
                blackPlayerAccount = playerAccountDB.get("новичок");
                blackPlayer = buildBeginnerPlayer(PlayerType.BLACK_PLAYER);
            }
            case PLAYER_VS_HARD_COMPUTER -> {
                whitePlayerAccount = enterPlayerAccount();
                whitePlayer = buildHumanPlayer(whitePlayerAccount.getName(), PlayerType.WHITE_PLAYER);
                blackPlayerAccount = playerAccountDB.get("мастер");
                blackPlayer = buildMasterPlayer(PlayerType.BLACK_PLAYER);

            }
            case EASY_COMPUTER_VS_EASY_COMPUTER -> {
                whitePlayerAccount = playerAccountDB.get("новичок");
                whitePlayer = buildBeginnerPlayer(PlayerType.WHITE_PLAYER);
                blackPlayerAccount = playerAccountDB.get("новичок");
                blackPlayer = buildBeginnerPlayer(PlayerType.BLACK_PLAYER);
            }
            case EASY_COMPUTER_VS_HARD_COMPUTER -> {
                whitePlayerAccount = playerAccountDB.get("новичок");
                whitePlayer = buildBeginnerPlayer(PlayerType.WHITE_PLAYER);
                blackPlayerAccount = playerAccountDB.get("мастер");
                blackPlayer = buildMasterPlayer(PlayerType.BLACK_PLAYER);
            }
            case HARD_COMPUTER_VS_HARD_COMPUTER -> {
                whitePlayerAccount = playerAccountDB.get("мастер");
                whitePlayer = buildMasterPlayer(PlayerType.WHITE_PLAYER);
                blackPlayerAccount = playerAccountDB.get("мастер");
                blackPlayer = buildMasterPlayer(PlayerType.BLACK_PLAYER);
            }
        }
    }

    private void buildEnvironment() {
        field = new FieldImpl(8, cellBuilder);
        fieldAnalyzer = new FieldAnalyzerImpl(field);
        gameHistory = new GameHistoryImpl(field);
        fieldController = new FieldControllerImpl(field, fieldAnalyzer, gameHistory);
        fieldPrinter = new FieldPrinterImpl(field);
    }

    private void buildGameManager(GameMode gameMode) {
        gameManager = new GameManagerImpl(fieldController, fieldPrinter, whitePlayer, blackPlayer, gameMode, printer);
    }
}
