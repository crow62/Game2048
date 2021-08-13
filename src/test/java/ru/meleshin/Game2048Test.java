package ru.meleshin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class Game2048Test {

    @Mock
    private SquareBoard<Integer> board = new SquareBoard<>(4);

    @Spy
    private GameHelper gameHelper;

    @InjectMocks
    private Game2048 game2048;

    @Captor
    ArgumentCaptor<List<Integer>> captor;


    @Test
    void initShouldFillBoardNullValues() {
        doNothing().when(board).fillBoard(anyList());
        game2048.init();
        verify(board).fillBoard(captor.capture());
        assertEquals(Arrays.asList(null, null, null, null, null, null, null, null,
                null, null, null, null, null, null, null, null), captor.getValue());
    }

    @Test
    void canMoveShouldBeTrue(){
        when(board.availableSpace()).thenReturn(Arrays.asList(new Key(0,0)));
        boolean move = game2048.canMove();
        assertTrue(move);
    }

    @Test
    void canMoveShouldBeFalse() {
        Random random = new Random();
        List<Integer> list = Arrays.asList(random.nextInt(1000), random.nextInt(1000),
                random.nextInt(1000), random.nextInt(1000));

        when(board.getValues(anyList())).thenReturn(list);

        boolean move = game2048.canMove();
        assertFalse(move);
        verify(board, times(8)).getValues(anyList());
    }


    @Test
    void move() {
    }


    @Test
    void moveLineShouldBeTrue() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        when(board.getValues(anyList())).thenReturn(Arrays.asList(4,null,2,2));

        //получаем доступ к приватному методу @moveLine
        Method moveLineMethod = game2048.getClass().getDeclaredMethod("moveLine", List.class, Direction.class);
        moveLineMethod.setAccessible(true);

        boolean returnValueFromMoveLine = (boolean) moveLineMethod.invoke(game2048, anyList(), Direction.UP);

        assertTrue(returnValueFromMoveLine);
    }

    @Test
    void moveLineShouldBeFalse() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

        when(board.getValues(anyList())).thenReturn(Arrays.asList(2,null,2,null));
        doReturn(Arrays.asList(2,null,2,null)).when(gameHelper).moveAndMergeEqual(anyList());

        //получаем доступ к приватному методу @moveLine
        Method moveLineMethod = game2048.getClass().getDeclaredMethod("moveLine", List.class, Direction.class);
        moveLineMethod.setAccessible(true);

        boolean returnValueFromMoveLine = (boolean) moveLineMethod.invoke(game2048, anyList(), Direction.UP);

        assertFalse(returnValueFromMoveLine);
    }

    @Test
    void addItem() {
        when(board.availableSpace()).thenReturn(Collections.emptyList());
        assertThrows(IllegalArgumentException.class, () -> game2048.addItem());
    }

    @Test
    void getGameBoard() {
    }

    @Test
    void hasWin() {
    }
}