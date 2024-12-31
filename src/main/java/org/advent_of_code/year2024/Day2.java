package org.advent_of_code.year2024;

import common.Aoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Day2 extends Aoc {

    public static final Logger LOG = LoggerFactory.getLogger(Day2.class);

    public Day2() {
        super(2);
    }

    public static void main(String[] args) {
        new Day2().printOutput();
    }

    @Override
    public void printOutput() {
        int safeReports = part1();
        LOG.info("Safe reports: {}", safeReports);
    }

    @Override
    public int part1() {
        return getArray().map(this::isSafe).filter(Boolean::booleanValue).toList().size();
    }

    @Override
    public int part2() {
        return 0;
    }

    private boolean isSafe(String input) {
        String[] tokens = input.split(" ");
        boolean isAscending = true;

        for (int i = 0; i < tokens.length - 1 ; i++) {
            int diff = Integer.parseInt(tokens[i]) - Integer.parseInt(tokens[i + 1]);

            if ((Math.abs(diff) > 3 || diff == 0) || (i > 0 && Integer.parseInt(tokens[i - 1]) - Integer.parseInt(tokens[i]) < 0 && diff > 0) || (i > 0 && Integer.parseInt(tokens[i - 1]) - Integer.parseInt(tokens[i]) > 0 && diff < 0)) {
                return false;
            }
        }
        return true;
    }
}
