package org.advent_of_code.year2024;

import common.Aoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Day1 extends Aoc {

    private static final Logger LOG = LoggerFactory.getLogger(Day1.class);

    public record Pair(int a, int b) {}

    public Day1() {
        super(1);
    }

    public static void main(String[] args) {
        new Day1().printOUt();
    }

    private void printOUt() {
        List<Pair> pairArray = parseInput(Pair.class);

        for (Pair pair : pairArray) {
            LOG.info(pair.a + " -> " + pair.b);
        }
    }

    private static ArrayList<Integer> countOccurences(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
        ArrayList<Integer> result = new ArrayList<>();

        for (Integer integer : arr1) {
            int count = 0;
            for (Integer value : arr2) {
                if (Objects.equals(integer, value)) {
                    count++;
                }
            }
            result.add(count);
        }

        return result;
    }

    private static int sum(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
        int sum = 0;
        Collections.sort(arr1);
        Collections.sort(arr2);

        for (int i = 0; i < arr1.size(); i++) {
            int tokensSum = Math.abs(arr1.get(i) - arr2.get(i));
            sum += tokensSum;
        }

        return sum;
    }

}
