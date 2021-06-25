package ru.meleshin;

import java.util.Scanner;

public class InputDirection {
    Game game2048 = new Game2048();
    Board board = game2048.getGameBoard();

    public static void main(String[] args) {
        InputDirection inputDirection = new InputDirection();
        inputDirection.game2048.init();
        for (int i = 0; i < inputDirection.board.width; i++) {
            System.out.println(inputDirection.game2048.getGameBoard().getValues(inputDirection.game2048.getGameBoard().getRow(i)));
        }
        while (inputDirection.game2048.canMove()) {
            inputDirection.output();
        }
    }

    void input() {
        Scanner scanner = new Scanner(System.in);
        switch (scanner.next()) {
            case "l":
                game2048.move(Direction.LEFT);
                break;
            case "r":
                game2048.move(Direction.RIGHT);
                break;
            case "d":
                game2048.move(Direction.DOWN);
                break;
            case "u":
                game2048.move(Direction.UP);
                break;
        }
    }

    void output() {
        input();
        System.out.println();
        for (int i = 0; i < board.width; i++) {
            System.out.println(game2048.getGameBoard().getValues(game2048.getGameBoard().getRow(i)));
        }
    }

}
