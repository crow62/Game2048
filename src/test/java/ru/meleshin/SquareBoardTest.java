package ru.meleshin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SquareBoardTest {
    SquareBoard<Integer> squareBoard;

    @BeforeEach
    void setUp() {
        squareBoard = new SquareBoard<>(2);
    }

    @Test
    void fillBoard() {
        squareBoard.fillBoard(Arrays.asList(1,2,3,4));
        Integer value = squareBoard.board.get(new Key(1, 0));
        assertEquals(3,value);
    }

    @Test
    void fillBoardShouldThrowsRuntimeEx() {
        assertThrows(RuntimeException.class, () -> squareBoard.fillBoard(Arrays.asList(1,2,3,4,5)));
    }


    @Test
    void availableSpaceShouldBeEmpty() {
        squareBoard.fillBoard(Arrays.asList(1,2,3,4));
        List<Key> keys = squareBoard.availableSpace();
        assertTrue(keys.isEmpty());
    }

    @Test
    void addItem() {
        squareBoard.addItem(new Key(1,1), 10);
        Integer value = squareBoard.board.get(new Key(1, 1));
        assertEquals(10, value);

    }

    @Test
    void getKey() {
        squareBoard.fillBoard(Arrays.asList(1,2,3,4));
        Key key = squareBoard.getKey(0, 1);
        assertEquals(new Key(0,1), key);

    }

    @Test
    void getValue() {
    }

    @Test
    void getColumn() {
        squareBoard.fillBoard(Arrays.asList(1,2,3,4));
        List<Key> column = squareBoard.getColumn(1);
        assertEquals(Arrays.asList(new Key(0,1), new Key(1,1)), column);
    }

    @Test
    void getRow() {
        squareBoard.fillBoard(Arrays.asList(1,2,3,4));
        List<Key> row = squareBoard.getRow(1);
        assertEquals(Arrays.asList(new Key(1,0), new Key(1,1)), row);
    }

    @Test
    void hasValue() {
        squareBoard.fillBoard(Arrays.asList(1,2,3,4));
        boolean valueIsPresent = squareBoard.hasValue(2);
        assertTrue(valueIsPresent);
    }

    @Test
    void getValues() {
        squareBoard.fillBoard(Arrays.asList(1,2,3,4));
        List<Integer> values = squareBoard.getValues(Arrays.asList(new Key(1, 0), new Key(1, 1)));
        assertEquals(Arrays.asList(3,4), values);
    }
}