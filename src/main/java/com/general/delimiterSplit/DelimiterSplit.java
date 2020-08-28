package com.general.delimiterSplit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DelimiterSplit {

    public static String reverse(String str) {
        List<String> allMatches = new ArrayList<>();
        Matcher m = Pattern.compile("[/:]").matcher(str);
        while (m.find()) allMatches.add(m.group());

        String[] words = str.split("[/:]", -1);
        Collections.reverse(Arrays.asList(words));
        assert (words.length == allMatches.size() - 1);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length - 1; i++) {
            sb.append(words[i]).append(allMatches.get(i));
        }

        sb.append(words[words.length - 1]);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverse("hello//world:here"));
        System.out.println(reverse("hello/world:here/"));
        System.out.println(reverse(":::::////"));
    }
}
