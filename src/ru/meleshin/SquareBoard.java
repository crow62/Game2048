package ru.meleshin;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class SquareBoard<V> extends Board<Key, V> {

    public SquareBoard(int size) {
        super(size, size);
    }

    @Override
    public void fillBoard(List<V> list) {
        Iterator<V> iterator = list.iterator();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (iterator.hasNext()) board.put(new Key(i, j), iterator.next());
            }
        }
    }

    @Override
    public List<Key> availableSpace() {
        List<Key> keysNullValues = new ArrayList<>();
        for (Key key : board.keySet()) {
            if (board.get(key) == null) {
                keysNullValues.add(key);
            }
        }
        return keysNullValues;
    }

    @Override
    public void addItem(Key key, V value) {
        board.put(key, value);
    }

    @Override
    public Key getKey(int i, int j) {
        for (Key key : board.keySet()) {
            if (key.getI() == i && key.getJ() == j) {
                return key;
            }
        }
        return null;
    }

    @Override
    public V getValue(Key key) {
        return board.get(key);
    }

    @Override
    public List<Key> getColumn(int j) {
        List<Key> columnKeys = new ArrayList<>();
        for (Key key : board.keySet().stream().sorted(Comparator.comparingInt(Key::getI)).collect(Collectors.toList())) {
            if (key.getJ() == j) {
                columnKeys.add(key);
            }
        }
        return columnKeys;
    }

    @Override
    public List<Key> getRow(int i) {
        List<Key> columnKeys = new ArrayList<>();
        for (Key key : board.keySet().stream().sorted(Comparator.comparingInt(Key::getJ)).collect(Collectors.toList())) {
            if (key.getI() == i) {
                columnKeys.add(key);
            }
        }
        return columnKeys;
    }

    @Override
    public boolean hasValue(V value) {

        if (value == null) {
            for (V valueBoard : board.values()) {
                if (valueBoard == null) {
                    return true;
                }
            }
            return false;
        } else {
            for (V valueBoard : board.values()) {
                if (valueBoard != null) {
                    if (valueBoard.equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public List<V> getValues(List<Key> keys) {
        List<V> valuesRow = new ArrayList<>();
        for (Key key : keys) {
            valuesRow.add(board.get(key));
        }
        return valuesRow;
    }
}
