package com.leetcode.algorithms;

/**
 * Problem #443 - Leetcode
 * To solve this question we can think of maintaining a valid window which
 * represents the compressed version of the character array processed so far.
 * <p>
 * For example consider the array [a, a, a, b, b, b]. If I've read 3 a's
 * and then I read the 1st 'b', my character array will look like [a, 3, a, b, b, b] where
 * the valid window goes from index 0 till index 1.
 * <p>
 * This means I've processed a contiguous group of a's and then I have seen a 'b'.
 * So, I can update the count of a's right after the 1st a and this is the valid compression I could
 * yield so far. Please note that there are extraneous a's after my valid window which I can "mark" for removal.
 * <p>
 * After I've read all the b's in the preceding example then my character array will look like [a, 3, a, b, 3, b]
 * If I've marked the extraneous a's and b's with an out of range character like '!' then
 * the array would be [a, 3, !, b, 3, !]. I'll call these '!' free spaces.
 * This array could be scanned from left to right once more and the free spaces can be overwritten to yield [a, 3, b, 3]
 * Please note that the extra characters after index 3 will still remain but my valid window index shall be = 3
 * i.e wEnd = 3.
 * <p>
 * The only extra edge case which needs to be handled is for a single character
 * where I would not need to update the count adjacent to the first occurrence of the character
 */

public class StringCompression {
    public int compress(char[] chars) {
        // A null or an empty array doesn't need to be compressed
        if (chars == null || chars.length == 0)
            return 0;

        // wStart represents the starting of a new window
        // wEnd represents the current valid window from [0...wEnd]
        // count is frequency count of the current character
        int wStart = 0, wEnd, count = 1;

        for (int i = 0; i < chars.length; i++) {
            // Encountered the end of array or a new character
            if (i + 1 == chars.length || chars[i + 1] != chars[i]) {
                // No need to update the frequency if it's just 1
                if (count == 1) wStart = i + 1;

                else {
                    // Write the current character frequency and get the new valid window i.e wEnd
                    wEnd = writeCounts(wStart + 1, count, chars);
                    // Set the new starting window location
                    wStart = i + 1;
                    // Mark free spaces between the current valid window and the next starting window
                    markFreeSpace(wEnd + 1, wStart, chars);
                }

                // Reset the frequency
                count = 1;
            } else count += 1;
        }

        // Fill the previously marked free spaces and return the new length of the compressed array
        return resize(chars) + 1;
    }

    public int writeCounts(int start, int count, char[] buffer) {
        String countStr = Integer.toString(count);
        int k = start - 1;
        for (int i = 0; i < countStr.length(); i++) buffer[++k] = countStr.charAt(i);
        return k;
    }

    public void markFreeSpace(int start, int end, char[] buffer) {
        for (int i = start; i < end; i++) buffer[i] = '!';
    }

    public int resize(char[] buffer) {
        int k = -1;
        for (int i = 0; i < buffer.length; i++) {
            if (buffer[i] != '!') buffer[++k] = buffer[i];
        }
        return k;
    }
}
