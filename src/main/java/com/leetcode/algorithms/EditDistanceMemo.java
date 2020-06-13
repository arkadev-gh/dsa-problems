package com.leetcode.algorithms;

import java.util.Arrays;

public class EditDistanceMemo {
    String word1, word2;
    int word1Len, word2Len;
    int[][] cache;

    public int minDistance(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
        this.word1Len = word1.length();
        this.word2Len = word2.length();
        this.cache = new int[word1Len + 1][word2Len + 1];

        for (int[] row : cache) Arrays.fill(row, Integer.MAX_VALUE);
        return minEditDistance(0, 0);
    }

    public int minEditDistance(int i, int j) {
        // Lookup the result in the cache if it already exists
        if (i <= word1Len && j <= word2Len && cache[i][j] < Integer.MAX_VALUE)
            return cache[i][j];

        // Base cases
        if (i == word1Len && j == word2Len) cache[i][j] = 0;
        else if (i == word1Len) cache[i][j] = word2Len - j;
        else if (j == word2Len) cache[i][j] = word1Len - i;

            // Recursive cases
        else if (word1.charAt(i) == word2.charAt(j)) cache[i][j] = minEditDistance(i + 1, j + 1);
        else {
            int insCost = minEditDistance(i, j + 1);
            int delCost = minEditDistance(i + 1, j);
            int updateCost = minEditDistance(i + 1, j + 1);
            cache[i][j] = Math.min(updateCost, Math.min(insCost, delCost)) + 1;
        }

        return cache[i][j];
    }
}
