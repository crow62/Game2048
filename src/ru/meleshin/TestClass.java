package ru.meleshin;

import java.util.*;

import static java.util.Arrays.asList;

public class TestClass {

    public static void main(String[] args) {
        //Board board = new SquareBoard(4);
        Game game2048 = new Game2048();
        Board board = game2048.getGameBoard();
        System.out.println(game2048.canMove());

        //board.fillBoard(asList(2,null,null,8, 2,2,8,8, 2,null,2,2, 4,2,4,2));


        game2048.init();

        for (int i = 0; i < board.width; i++) {
            System.out.println(game2048.getGameBoard().getValues(game2048.getGameBoard().getRow(i)));
        }
        System.out.println();

        game2048.move(Direction.LEFT);
        System.out.println();
        for (int i = 0; i < board.width; i++) {
            System.out.println(game2048.getGameBoard().getValues(game2048.getGameBoard().getRow(i)));
        }


        game2048.move(Direction.DOWN);
        System.out.println();
        for (int i = 0; i < board.height; i++) {
            System.out.println(game2048.getGameBoard().getValues(game2048.getGameBoard().getRow(i)));
        }


        game2048.move(Direction.RIGHT);
        System.out.println();
        for (int i = 0; i < board.height; i++) {
            System.out.println(game2048.getGameBoard().getValues(game2048.getGameBoard().getRow(i)));
        }

        System.out.println();

    }
}
