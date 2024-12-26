package org.advent_of_code.dayone;

import common.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        int sum = sum();
        LOG.info("Sum of all words in a file: {}", sum);
    }

    private static int sum() {
        int sum = 0;
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();

        File file = Util.readFile("aoc_day1");

        Util.readToArray(file, arr1, arr2);

        Collections.sort(arr1);
        Collections.sort(arr2);

        for (int i = 0; i < arr1.size(); i++) {
            int tokensSum = Math.abs(arr1.get(i) - arr2.get(i));
            sum += tokensSum;
        }

        return sum;
    }

}
