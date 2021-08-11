package ru.meleshin;

import java.util.*;

import static java.util.Arrays.asList;

public class Game2048 implements Game {


    public static final int GAME_SIZE = 4;
    //private final Board<Key, Integer> board;
    private final Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);

    GameHelper helper = new GameHelper();
    Random random = new Random();


//    public Game2048(Board<Key, Integer> board) {
//        this.board = board;
//    }

    @Override
    public void init() {
        board.fillBoard(asList(null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null));
        addItem();
        addItem();
    }

    @Override
    public boolean canMove() {
        return !board.availableSpace().isEmpty();
    }

    @Override
    public boolean move(Direction direction) {
        switch (direction) {
            case UP:
                for (int i = 0; i < board.width; i++) {
                    List<Key> column = board.getColumn(i);
                    List<Integer> valuesBeforeMove = board.getValues(column);
                    List<Integer> valuesAfterMove = helper.moveAndMergeEqual(valuesBeforeMove);
                    Iterator<Integer> iterator = valuesAfterMove.iterator();
                    for (Key k : column) {
                        if (iterator.hasNext()) board.addItem(k, iterator.next());
                    }
                }
                break;
            case DOWN:
                for (int i = 0; i < board.width; i++) {
                    List<Key> column = board.getColumn(i);
                    List<Integer> valuesBeforeMove = board.getValues(column);
                    List<Integer> reverseValuesBeforeMove = new ArrayList<>(valuesBeforeMove.size());
                    ListIterator<Integer> iterator = valuesBeforeMove.listIterator(valuesBeforeMove.size());
                    while (iterator.hasPrevious()) {
                        reverseValuesBeforeMove.add(iterator.previous());
                    }

                    List<Integer> valuesAfterMove = helper.moveAndMergeEqual(reverseValuesBeforeMove);

                    ListIterator<Integer> listIterator = valuesAfterMove.listIterator(valuesAfterMove.size());
                    for (Key k : column) {
                        if (listIterator.hasPrevious()) board.addItem(k, listIterator.previous());
                    }
                }
                break;

            case LEFT:
                for (int i = 0; i < board.height; i++) {
                    List<Key> row = board.getRow(i);
                    List<Integer> valuesBeforeMove = board.getValues(row);
                    List<Integer> valuesAfterMove = helper.moveAndMergeEqual(valuesBeforeMove);
                    Iterator<Integer> iterator = valuesAfterMove.iterator();
                    for (Key k : row) {
                        if (iterator.hasNext()) board.addItem(k, iterator.next());
                    }
                }
                break;

            case RIGHT:
                for (int i = 0; i < board.height; i++) {
                    List<Key> row = board.getRow(i);
                    List<Integer> valuesBeforeMove = board.getValues(row);
                    List<Integer> reverseValuesBeforeMove = new ArrayList<>(valuesBeforeMove.size());
                    ListIterator<Integer> iterator = valuesBeforeMove.listIterator(valuesBeforeMove.size());
                    while (iterator.hasPrevious()) {
                        reverseValuesBeforeMove.add(iterator.previous());
                    }

                    List<Integer> valuesAfterMove = helper.moveAndMergeEqual(reverseValuesBeforeMove);
                    ListIterator<Integer> listIterator = valuesAfterMove.listIterator(valuesAfterMove.size());
                    for (Key k : row) {
                        if (listIterator.hasPrevious()) board.addItem(k, listIterator.previous());
                    }
                }
                break;
        }

        addItem();
        return !board.availableSpace().isEmpty();
    }

    @Override
    public void addItem() {
        if (board.availableSpace().isEmpty()) try {
            throw new NotEnoughSpace("Not enough space");
        } catch (NotEnoughSpace notEnoughSpace) {
            notEnoughSpace.printStackTrace();
        }

        int[] twoOrFour = {2, 4};
        int randomIndexTwoOrFour = random.nextInt(twoOrFour.length);

        List<Key> keysNullValues = board.availableSpace();
        int randomIndexKeysNullValues = random.nextInt(board.availableSpace().size());

        board.addItem(keysNullValues.get(randomIndexKeysNullValues), twoOrFour[randomIndexTwoOrFour]);
    }

    @Override
    public Board getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return board.hasValue(2048);
    }
}
