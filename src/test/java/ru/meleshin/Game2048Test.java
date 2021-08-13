package ru.meleshin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        when(board.availableSpace()).thenReturn(Arrays.asList(new Key(1, 1)));
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
        List<Integer> list = Arrays.asList(random.nextInt(100), random.nextInt(100),
                random.nextInt(100), random.nextInt(100));

        when(board.availableSpace()).thenReturn(Arrays.asList(new Key(1, 1)));
        when(board.getValues(board.getRow(0))).thenReturn(list);
        when(board.getValues(board.getColumn(0))).thenReturn(list);
        when(board.getValues(board.getRow(1))).thenReturn(list);
        when(board.getValues(board.getColumn(1))).thenReturn(list);
        when(board.getValues(board.getRow(2))).thenReturn(list);
        when(board.getValues(board.getColumn(2))).thenReturn(list);
        when(board.getValues(board.getRow(3))).thenReturn(list);
        when(board.getValues(board.getColumn(3))).thenReturn(list);

        boolean move = game2048.canMove();
        assertTrue(move);
        verify(board,times(4)).getValues(board.getRow(anyInt()));
        verify(board,times(4)).getValues(board.getColumn(anyInt()));

    }


    @Test
    void move() {
    }

    @Test
    void addItem() {
    }

    @Test
    void getGameBoard() {
    }

    @Test
    void hasWin() {
    }
}