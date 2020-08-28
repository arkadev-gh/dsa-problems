package com.leetcode.algorithms.minWindowSubstring;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        Map<Character, Integer> expectedFreq = new HashMap<>();
        Map<Character, Integer> actualFreq = new HashMap<>();

        int ec = 0, ac = 0, currWStart = 0, validStart = 0, validEnd = Integer.MAX_VALUE;

        for (int i = 0; i < t.length(); i++) {
            expectedFreq.put(t.charAt(i), expectedFreq.getOrDefault(t.charAt(i), 0) + 1);
            actualFreq.put(t.charAt(i), 0);
            ec++;
        }

        int i = 0;
        while (true) {
            // Expand window
            if (ac < ec) {
                // Attempt to expand window beyond it's limits
                if (i == s.length()) break;

                // If we're dealing with an expected character
                if (expectedFreq.containsKey(s.charAt(i))) {
                    int actual = actualFreq.get(s.charAt(i));
                    int expected = expectedFreq.get(s.charAt(i));

                    if (actual < expected) ac++;
                    actualFreq.put(s.charAt(i), actual + 1);
                }

                i++;
            }

            // Opportunity to contract window
            else {
                // Register the current valid window
                if (i - currWStart < validEnd - validStart) {
                    validStart = currWStart;
                    validEnd = i;
                }

                // Contract the current window
                if (expectedFreq.containsKey(s.charAt(currWStart))) {
                    int actual = actualFreq.get(s.charAt(currWStart));
                    int expected = expectedFreq.get(s.charAt(currWStart));

                    if (actual - 1 < expected) ac--;
                    actualFreq.put(s.charAt(currWStart), actual - 1);
                }

                currWStart++;
            }
        }

        return (validEnd - validStart < Integer.MAX_VALUE) ? s.substring(validStart, validEnd) : "";
    }
}
