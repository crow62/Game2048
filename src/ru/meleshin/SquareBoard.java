package ru.meleshin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class SquareBoard extends Board {

    public SquareBoard(int size) {
        super(size, size);
    }

    @Override
    void fillBoard(List<Integer> list) {
        Iterator<Integer> iterator = list.iterator();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (iterator.hasNext()) board.put(new Key(i, j), iterator.next());
            }
        }
    }

    @Override
    List<Key> availableSpace() {
        List<Key> keysNullValues = new ArrayList<>();
        for (Key key : board.keySet()) {
            if (board.get(key) == null) {
                keysNullValues.add(key);
            }
        }
        return keysNullValues;
    }

    @Override
    void addItem(Key key, Integer value) {
        board.put(key, value);
    }

    @Override
    Key getKey(int i, int j) {
        for (Key key : board.keySet()) {
            if (key.getI() == i && key.getJ() == j) {
                return key;
            }
        }
        return null;
    }

    @Override
    Integer getValue(Key key) {
        return board.get(key);
    }

    @Override
    List<Key> getColumn(int j) {
        List<Key> columnKeys = new ArrayList<>();
        for (Key key : board.keySet().stream().sorted(Comparator.comparingInt(Key::getI)).collect(Collectors.toList())) {
            if (key.getJ() == j) {
                columnKeys.add(key);
            }
        }
        return columnKeys;
    }

    @Override
    List<Key> getRow(int i) {
        List<Key> columnKeys = new ArrayList<>();
        for (Key key : board.keySet().stream().sorted(Comparator.comparingInt(Key::getJ)).collect(Collectors.toList())) {
            if (key.getI() == i) {
                columnKeys.add(key);
            }
        }
        return columnKeys;
    }

    @Override
    boolean hasValue(Integer value) {
        for (Integer i : board.values()) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    List<Integer> getValues(List<Key> keys) {
        List<Integer> valuesRow = new ArrayList<>();
        for (Key key : keys) {
            valuesRow.add(board.get(key));
        }
        return valuesRow;
    }
}
