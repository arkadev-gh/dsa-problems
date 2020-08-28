package com.general.StringMatch;

import java.util.Scanner;

public class StringMatch2 {
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
        String replaced = text.replaceAll(pattern, "X");
        return replaced.replaceAll("X+", "X");
    }
}
