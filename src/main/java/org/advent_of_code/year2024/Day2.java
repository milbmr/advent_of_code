package org.advent_of_code.year2024;

import common.Aoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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
        int safeReport= part2();
        LOG.info("Safe reports: {}", safeReport);
    }

    @Override
    public int part1() {
        return (int) getArray().filter(this::isSafe).count();
    }

    @Override
    public int part2() {
        return (int) getArray().filter(this::isUnsafeSafe).count();
    }

    private boolean isSafe(String input) {
        String[] tokens = getInput(input);

        for (int i = 0; i < tokens.length - 1 ; i++) {
            int diff = Integer.parseInt(tokens[i]) - Integer.parseInt(tokens[i + 1]);

            if ((Math.abs(diff) > 3 || diff == 0) || (i > 0 && Integer.parseInt(tokens[i - 1]) - Integer.parseInt(tokens[i]) < 0 && diff > 0) || (i > 0 && Integer.parseInt(tokens[i - 1]) - Integer.parseInt(tokens[i]) > 0 && diff < 0)) {
                return false;
            }
        }
        return true;
    }

    private boolean isUnsafeSafe(String input) {
        if (isSafe(input)) {
            return true;
        }

        String[] tokens = getInput(input);
        for (int i = 0; i < tokens.length ; i++) {
            if (isSafe(removeElement(tokens, i))) {
                return true;
            };
        }
        return false;
    }
}
