package com.general.foobar;

public class LuckyLambs2 {
    public static int luckyLambs(int lambs) {
        return numConstrained(lambs) - numLenient(lambs);
    }

    public static int numLenient(int lambs) {
        int henchMen = 0, exponent = 0, sum = 0;

        while (Math.pow(2, exponent) + sum <= lambs) {
            henchMen += 1;
            sum += Math.pow(2, exponent);
            exponent += 1;
        }

        if ((lambs - sum) > Math.pow(2, exponent - 1) + Math.pow(2, exponent - 2))
            henchMen += 1;

        return henchMen;
    }

    public static int numConstrained(int lambs) {
        int henchMen = 2, first = 1, second = 1, curr = first + second, sum = curr + 2;

        while (sum <= lambs) {
            henchMen += 1;
            first = second;
            second = curr;
            curr = first + second;
            sum += curr;
        }

        return henchMen;
    }

    public static void main(String[] args) {
        System.out.println(numLenient(2));
    }
}
