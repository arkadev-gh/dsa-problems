package com.leetcode.algorithms.minCostTickets;

import java.util.TreeMap;

public class MinCostTickets {
    public int minCostTickets(int[] days, int[] costs) {
        TreeMap<Integer, Integer> dayToIdx = new TreeMap<>();
        for (int i = 0; i < days.length; i++)
            dayToIdx.put(days[i], i);

        int[] minCost = new int[days.length];

        for (int i = minCost.length - 1; i >= 0; i--) {
            Integer oneDayKey = dayToIdx.ceilingKey(days[i] + 1);
            Integer sevenDayKey = dayToIdx.ceilingKey(days[i] + 7);
            Integer thirtyDayKey = dayToIdx.ceilingKey(days[i] + 30);

            int oneDayCost = (oneDayKey == null) ? costs[0] : minCost[dayToIdx.get(oneDayKey)] + costs[0];
            int sevenDayCost = (sevenDayKey == null) ? costs[1] : minCost[dayToIdx.get(sevenDayKey)] + costs[1];
            int thirtyDayCost = (thirtyDayKey == null) ? costs[2] : minCost[dayToIdx.get(thirtyDayKey)] + costs[2];

            minCost[i] = Math.min(Math.min(oneDayCost, sevenDayCost), thirtyDayCost);
        }

        return minCost[0];
    }
}
