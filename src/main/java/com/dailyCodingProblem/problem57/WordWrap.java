package com.dailyCodingProblem.problem57;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordWrap {
    public List<String> getWordWrap(String sentence, int k) {
        List<String> lines = new ArrayList<>();
        int remCap = k;
        boolean newLine = true, possible = true;
        StringBuilder line = new StringBuilder();
        String[] arr = sentence.split("\\s+");

        for (int i = 0; i < arr.length && possible; i++) {
            // Maximum capacity for a line has been reached
            if (!newLine && arr[i].length() + 1 > remCap) {
                lines.add(line.toString());
                line.setLength(0);
                remCap = k;
                newLine = true;
            }

            // Adding the first word to a line
            if (newLine) {
                if (arr[i].length() > remCap)
                    possible = false;

                else {
                    newLine = false;
                    remCap = remCap - arr[i].length();
                    line.append(arr[i]);
                }
            }

            // Adding subsequent words to the same line
            else {
                remCap = remCap - arr[i].length() - 1;
                line.append(" ");
                line.append(arr[i]);
            }
        }

        // There are existing words in the string builder
        if (line.length() > 0) lines.add(line.toString());

        if (possible) return lines;
        return null;
    }

    // Driver code
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a sentence: ");
        String sentence = sc.nextLine();
        System.out.println("Enter the value of k: ");
        int k = sc.nextInt();
        WordWrap obj = new WordWrap();

        List<String> wordWrap = obj.getWordWrap(sentence, k);
        if (wordWrap != null)
            wordWrap.forEach(System.out::println);

        else
            System.out.println("No word wrapping was possible");
    }
}
