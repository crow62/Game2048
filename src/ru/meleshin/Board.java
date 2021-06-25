package ru.meleshin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Board <K,V> {
    int width;
    int height;

    protected Map<K, V> board = new HashMap<>();

    public Board(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public abstract void fillBoard (List<V> list);
    public abstract List<K> availableSpace();
    public abstract void addItem (K k, V v);
    public abstract K getKey (int i, int j);
    public abstract V getValue (K k);
    public abstract List<K> getColumn(int j);
    public abstract List<K> getRow(int i);
    public abstract boolean hasValue(V v);
    public abstract List<V> getValues(List<K> keys);
}
