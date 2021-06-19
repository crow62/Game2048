package ru.meleshin;


import java.util.List;

public class GameHelper {
    List<Integer> moveAndMergeEqual(List<Integer> list) {
        //сортировка с перемещением нуллов вправо
        list.sort((o1, o2) -> {
            if (o2 == null) return -1;
            else if (o1 != null && o1 < o2) return -1;
            else return 0;
        });

        // cложение соседних равных чисел и добавление нуллов
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i) == null || list.get(i + 1) == null) {
                continue;
            }
            if (list.get(i) == list.get(i + 1)) {
                list.set(i, list.get(i) * 2);
                list.set(i + 1, null);
            }
        }

        //результирующая сортировка
        list.sort((o1, o2) -> {
            if (o2 == null) return -1;
            else return 0;
        });

        return list;
    }
}
