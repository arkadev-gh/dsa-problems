package com.leetcode.algorithms;

public class EditDistanceRec {
    String word1, word2;
    int word1Len, word2Len;

    public int minDistance(String word1, String word2) {
        this.word1 = word1;
        this.word2 = word2;
        this.word1Len = word1.length();
        this.word2Len = word2.length();

        return minDistanceRec(0, 0);
    }

    public int minDistanceRec(int i, int j) {
        // Base cases
        if (i == word1Len && j == word2Len) return 0;
        if (i == word1Len) return word2Len - j;
        if (j == word2Len) return word1Len - i;

        // Recursive cases
        if (word1.charAt(i) == word2.charAt(j)) return minDistanceRec(i + 1, j + 1);

        int insCost = minDistanceRec(i, j + 1) + 1;
        int delCost = minDistanceRec(i + 1, j) + 1;
        int updateCost = minDistanceRec(i + 1, j + 1) + 1;
        return Math.min(updateCost, Math.min(insCost, delCost));
    }
}
