package ru.meleshin;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameHelperTest {

    @Test
    void moveAndMergeEqual() {
        GameHelper gameHelper = new GameHelper();
        List<Integer> list1 = gameHelper.moveAndMergeEqual(Arrays.asList(2, 2, 2, 2, 4));
        List<Integer> list2 = gameHelper.moveAndMergeEqual(Arrays.asList(4, 2, 2, 2, 4));
        List<Integer> list3 = gameHelper.moveAndMergeEqual(Arrays.asList(null, null, 2, 2, 4));
        List<Integer> list4 = gameHelper.moveAndMergeEqual(Arrays.asList(null, null, 2, 4, null));
        List<Integer> list5 = gameHelper.moveAndMergeEqual(Arrays.asList(64, null, null, null, 64));
        List<Integer> list6 = gameHelper.moveAndMergeEqual(Arrays.asList(null, null, null, null, null));
        List<Integer> list7 = gameHelper.moveAndMergeEqual(Arrays.asList(4, null, 4, 4, 4));
        List<Integer> list8 = gameHelper.moveAndMergeEqual(Arrays.asList(2048, null, 2048, 4, 4));
        List<Integer> list9 = gameHelper.moveAndMergeEqual(Arrays.asList(null, null, null, 4096, 4096));

        assertEquals(Arrays.asList(4, 4, 4, null, null), list1);
        assertEquals(Arrays.asList(4, 4, 2, 4, null), list2);
        assertEquals(Arrays.asList(4, 4, null, null, null), list3);
        assertEquals(Arrays.asList(2, 4, null, null, null), list4);
        assertEquals(Arrays.asList(128, null, null, null, null), list5);
        assertEquals(Arrays.asList(null, null, null, null, null), list6);
        assertEquals(Arrays.asList(8, 8, null, null, null), list7);
        assertEquals(Arrays.asList(4096, 8, null, null, null), list8);
        assertEquals(Arrays.asList(8192, null, null, null, null), list9);
    }
}