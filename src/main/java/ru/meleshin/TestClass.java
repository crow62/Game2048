package ru.meleshin;

import java.util.*;

import static java.util.Arrays.asList;

public class TestClass {

    public static void main(String[] args) {
        Game game2048 = new Game2048();
        Board board = game2048.getGameBoard();
        System.out.println(game2048.canMove());


        List<Integer> list = Arrays.asList(1,2,3);
        Collections.reverse(list);
        System.out.println(list);


    }
}
