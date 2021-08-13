package ru.meleshin;

import java.util.*;

import static java.util.Arrays.asList;

public class Game2048 implements Game {

    public static int GAME_SIZE = 4;
    //private final Board<Key, Integer> board;
    private Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);

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

    //проверяем на наличие равных соседей в строках и столбцах
    @Override
    public boolean canMove() {
        if (board.availableSpace().isEmpty()) {
            for (int i = 0; i < GAME_SIZE; i++) {
                List<Integer> valuesRow = board.getValues(board.getRow(i));
                List<Integer> valuesColumn = board.getValues(board.getColumn(i));

                for (int j = 0; j < GAME_SIZE - 1; j++) {
                    if (valuesRow.get(j).equals(valuesRow.get(j + 1)) ||
                            valuesColumn.get(j).equals(valuesColumn.get(j + 1))) return true;
                }
            }
            return false;
        }
        return true;
    }

    @Override
    public boolean move(Direction direction) {
        boolean factMoveLine;
        boolean factMove = false;
        for (int i = 0; i < GAME_SIZE; i++) {
            switch (direction) {
                case UP:
                case DOWN:
                    List<Key> column = board.getColumn(i);
                    factMoveLine = moveLine(column, direction);
                    if (factMoveLine) factMove = true;
                    break;
                case LEFT:
                case RIGHT:
                    List<Key> row = board.getRow(i);
                    factMoveLine = moveLine(row, direction);
                    if (factMoveLine) factMove = true;
                    break;
                default:
                    throw new RuntimeException("Direction not valid");
            }
        }
        if (factMove) addItem();

        return canMove();

    }

    //делаем перемещение в строке\столбце и получаем факт изменения состояния линии
    private boolean moveLine(List<Key> line, Direction direction) {
        boolean factMove = false;
        List<Integer> valuesBeforeMove = board.getValues(line);
        if (direction == Direction.DOWN || direction == Direction.RIGHT) Collections.reverse(valuesBeforeMove);
        List<Integer> valuesAfterMove = helper.moveAndMergeEqual(valuesBeforeMove);
        if (!valuesAfterMove.equals(valuesBeforeMove)) factMove = true;
        if (direction == Direction.DOWN || direction == Direction.RIGHT) Collections.reverse(valuesAfterMove);
        Iterator<Integer> iterator = valuesAfterMove.iterator();
        for (Key k : line) {
            if (iterator.hasNext()) board.addItem(k, iterator.next());
        }
        return factMove;
    }

    @Override
    public void addItem() {
        if (board.availableSpace().isEmpty())
            try {
                throw new NotEnoughSpace("Not enough space");
            } catch (NotEnoughSpace notEnoughSpace) {
                notEnoughSpace.printStackTrace();
            }
        List<Key> keysNullValues = board.availableSpace();
        int randomIndexKeysNullValues = random.nextInt(board.availableSpace().size());

        board.addItem(keysNullValues.get(randomIndexKeysNullValues), random.nextInt(10) == 1 ? 4 : 2);
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
