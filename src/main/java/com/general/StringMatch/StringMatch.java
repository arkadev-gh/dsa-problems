package com.general.StringMatch;

import java.util.Scanner;

public class StringMatch {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        while (t-- > 0) {
            String text = scan.next();
            String pattern = scan.next();
            String result = getReplacedString(text, pattern);
            System.out.println(result.toString());
        }
    }

    public static String getReplacedString(String text, String pattern) {
        StringBuilder sb = new StringBuilder();
        int textPtr = 0, patternPtr = 0, wEnd = -1, wStart = -1;

        while (textPtr < text.length()) {
            // If two characters match. This might be a window
            if (text.charAt(textPtr) == pattern.charAt(patternPtr)) {
                if (wStart == -1)
                    wStart = textPtr;

                textPtr += 1;
                patternPtr += 1;

                // Record the new found window
                if (patternPtr == pattern.length()) {
                    wEnd = textPtr;
                    patternPtr = 0;
                }
            }

            // If two characters don't match
            else {
                // If a valid window already exists
                if (wEnd >= wStart && wEnd != -1) {
                    sb.append("X");
                    textPtr = wEnd;
                }

                // No valid window exists
                else if (wStart >= 0) {
                    textPtr = wStart + 1;
                    sb.append(text, wStart, textPtr);
                }

                // A window doesn't exist
                else {
                    sb.append(text.charAt(textPtr));
                    textPtr += 1;
                }

                // Reset the window and pattern pointer
                patternPtr = 0;
                wStart = -1;
                wEnd = -1;
            }
        }

        if (wEnd >= wStart && wEnd != -1) {
            sb.append("X");
            sb.append(text, wEnd, textPtr);
        }

        else if (wStart >= 0) {
            sb.append(text, wStart, textPtr);
        }

        return sb.toString();
    }
}
