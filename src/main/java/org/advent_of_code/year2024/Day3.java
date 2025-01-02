package org.advent_of_code.year2024;

import common.Aoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Day3 extends Aoc {

    private static final Logger LOG = LoggerFactory.getLogger(Day3.class);

    public Day3() {
        super(3);
    }

    public static void main(String[] args) {
        new Day3().part1();
    }

    @Override
    public void printOutput() {

    }

    @Override
    public int part1() {
        List<String> matches = Arrays.stream(getArray()
                .map(this::findMulMatch).flatMap(List::stream).toArray(String[]::new)).map(this::findNumMatch).toList();
        for (String match : matches) {
            LOG.info("Found number of matches: {}", match);
        }
        return 0;
    }

    @Override
    public int part2() {
        return 0;
    }

    private List<String> findMulMatch(String input) {
        return matchPattern(input, "mul\\((\\d+),(\\d+)\\)");
    }

    private String findNumMatch(String input) {
        return matchString(input, "(\\d+),(\\d+)");
    }
}