package com.leetcode.algorithms.minRemoveParen;

import java.util.ArrayDeque;
import java.util.Deque;

public class MinRemoveParantheses {
    public String minRemoveToMakeValid(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        StringBuilder result = new StringBuilder();
        boolean[] invalid = new boolean[s.length()];

        for(int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            if(curr == ')') {
                if(stack.isEmpty() || s.charAt(stack.peek()) != '(') stack.push(i);
                else stack.pop();
            }

            else if(curr == '(') stack.push(i);
        }

        for(int index : stack)
            invalid[index] = true;

        for(int i = 0; i < s.length(); i++)
            if(!invalid[i])
                result.append(s.charAt(i));

        return result.toString();
    }
}
