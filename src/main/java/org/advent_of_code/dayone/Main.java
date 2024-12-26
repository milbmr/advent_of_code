package org.advent_of_code.dayone;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.IntStream;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    private static final Util util = new Util();

    public static void main(String[] args) {

        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();

        File file = util.readFile("aoc_day1");

        util.readToArray(file, arr1, arr2);

        int sum = sum(arr1, arr2);
        LOG.info("distance between the lists: {}", sum);

        ArrayList<Integer> occur = countOccurences(arr1, arr2);
        int simScore = IntStream.range(0, arr1.size()).mapToObj(i -> arr1.get(i) * occur.get(i))
                .mapToInt(Integer::intValue).sum();
        LOG.info("Similarity between the lists: {}", simScore);
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
