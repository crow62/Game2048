package ru.meleshin;

import java.util.ArrayList;
import java.util.List;

public class GameHelper {
    List<Integer> moveAndMergeEqual(List<Integer> list) {

        List<Integer> listNewValues = new ArrayList<>(list);
        int counter = 0;
        for (int i = 0; i < listNewValues.size()-1; i++) {
            if (listNewValues.get(counter)==null) {
                listNewValues.remove(counter);
                listNewValues.add(null);
                counter--;
            }
            counter++;
        }

        for (int i = 0; i < listNewValues.size() - 1; i++) {
            if (listNewValues.get(i) == null) break;
            if (listNewValues.get(i) == listNewValues.get(i + 1)) {
                listNewValues.set(i, listNewValues.get(i) * 2);
                listNewValues.remove(i + 1);
                listNewValues.add(null);
            }
        }
        return listNewValues;

    }
}
