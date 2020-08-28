package com.general.foobar;

public class LuckyTriples {
    public static void main(String[] args) {
        int[] l = new int[]{1, 2, 3, 4, 5, 6};
        System.out.println(solution(l));
    }

    public static int solution(int[] l) {
        if (l == null || l.length < 3) return 0;

        int[] numMultiplesRight = new int[l.length];
        int totalWays = 0;

        // Find number of multiples to the right
        for (int i = 0; i < l.length; i++) {
            for (int j = i + 1; j < l.length; j++) {
                if (l[j] % l[i] == 0)
                    numMultiplesRight[i] += 1;
            }
        }

        // Calculate the total number of ways to form lucky triples
        for (int i = 0; i <= l.length - 3; i++) {
            for (int j = i + 1; j <= l.length - 2; j++) {
                if (l[j] % l[i] == 0)
                    totalWays += numMultiplesRight[j];
            }
        }

        return totalWays;
    }
}
