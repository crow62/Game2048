package ru.meleshin;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class GameHelper {
    List<Integer> moveAndMergeEqual(List<Integer> list) {

        List<Integer> list1 = new ArrayList<>(list);
        int counter = 0;
        for (int i = 0; i < list1.size()-1; i++) {
            if (list1.get(counter)==null) {
                list1.remove(counter);
                list1.add(null);
                counter--;
            }
            counter++;
        }

        for (int i = 0; i < list1.size() - 1; i++) {
            if (list1.get(i) == null) break;
            if (list1.get(i) == list1.get(i + 1)) {
                list1.set(i, list1.get(i) * 2);
                list1.remove(i + 1);
                list1.add(null);
            }
        }
        return list1;

    }
}
