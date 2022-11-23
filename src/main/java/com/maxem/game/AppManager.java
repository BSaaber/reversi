package com.maxem.game;

import com.maxem.field.cell.CellBuilder;
import com.maxem.field.cell.CellBuilderImpl;
import com.maxem.field.Field;
import com.maxem.field.FieldImpl;
import com.maxem.fieldutils.*;
import com.maxem.players.computer.ComputerBeginnerPlayer;
import com.maxem.players.computer.ComputerMasterPlayer;
import com.maxem.players.HumanPlayer;
import com.maxem.players.Player;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class AppManager {
    CellBuilder cellBuilder;

    Field field;
    FieldAnalyzer fieldAnalyzer;
    FieldController fieldController;
    GameManager gameManager;
    Printer printer;
    Player whitePlayer, blackPlayer;
    Scanner scanner;
    BufferedReader br;

    int gameMode;
    AppManager() {
        scanner = new Scanner(System.in);
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    // TODO add choosing color
    public void start() throws IOException {
        selectGameMode();
        configurePlayers();
        buildEnvironment();
        gameManager.Play();
    }
    private void selectGameMode() {
        System.out.println("""
                Выберите режим игры:
                1 - игрок против комьютера-новичка
                2 - игрок против компьютера-мастера
                3 - игрок против игрока""");
        gameMode = scanner.nextInt();
    }

    private void configurePlayers() throws IOException {
        System.out.println("Введите имя игрока");
        String name = br.readLine();
        whitePlayer = new HumanPlayer(name);
        switch (gameMode) {
            case 1 -> blackPlayer = new ComputerBeginnerPlayer();
            case 2 -> blackPlayer = new ComputerMasterPlayer();
            case 3 -> {
                System.out.println("Введите имя второго игрока");
                String secondName = br.readLine();
                blackPlayer = new HumanPlayer(secondName);
            }
        }
    }

    public void buildEnvironment() {
        cellBuilder = new CellBuilderImpl();
        field = new FieldImpl(8, cellBuilder);
        fieldAnalyzer = new FieldAnalyzerImpl(field);
        fieldController = new FieldControllerImpl(field, fieldAnalyzer);
        printer = new PrinterImpl(field);
        gameManager = new GameManagerImpl(fieldController, printer, whitePlayer, blackPlayer);
    }
}
