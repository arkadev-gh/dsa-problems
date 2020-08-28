package com.general.foobar;

import java.util.*;

public class Subset {
    public static HashMap<String, Integer> cache;

    public static void main(String[] args) {
        int[] numbers = new int[]{3, 1, 2, 5, 7, 2, 8, 6, 1, 4};
        int[] sorted = Arrays.stream(numbers).boxed().sorted(Comparator.reverseOrder()).mapToInt(Integer::intValue).toArray();
        cache = new HashMap<>();

        int num = longestDivideBy3(sorted, 0, 0);
        System.out.println(num);
    }

    public static int longestDivideBy3(int[] sorted, int index, int currNum) {
        String key = index + " " + currNum;

        if (cache.containsKey(key)) {
            return cache.get(key);
        }

        if (index == sorted.length) {
            if (currNum % 3 == 0) cache.put(key, currNum);
            else cache.put(key, 0);
        }

        else {
            int newNum = (currNum * 10) + sorted[index];
            int inclNum = longestDivideBy3(sorted, index + 1, newNum);
            int exclNum = longestDivideBy3(sorted, index + 1, currNum);
            cache.put(key, Math.max(inclNum, exclNum));
        }

        return cache.get(key);
    }
}
