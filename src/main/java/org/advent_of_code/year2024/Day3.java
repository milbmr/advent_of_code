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

    public record Pair(int a, int b) {
    }

    public static void main(String[] args) {
        new Day3().printOutput();
    }

    @Override
    public void printOutput() {
        int mul = part1();
        LOG.info("multiplications: {}", mul);

        int allMul = part2();
        LOG.info("filtered multiplications: {}", allMul);
    }

    @Override
    public int part1() {
        return Arrays.stream(getArray()
                        .map(this::findMulMatch).flatMap(List::stream).toArray(String[]::new))
                .map(this::findNumMatch).map(i -> mapStream(i, Pair.class, ",")).mapToInt(p -> p.a() * p.b()).sum();
    }

    @Override
    public int part2() {
        return Arrays.stream(filterInstruction()).map(this::findNumMatch)
                .map(i -> mapStream(i, Pair.class, ",")).mapToInt(p -> p.a() * p.b()).sum();
    }

    private List<String> findInstructionMatch(String input) {
        return matchPattern(input, "mul\\((\\d+),(\\d+)\\)|do\\(\\)|don't\\(\\)");
    }

    private List<String> findMulMatch(String input) {
        return matchPattern(input, "mul\\((\\d+),(\\d+)\\)");
    }

    private String findNumMatch(String input) {
        return matchString(input, "(\\d+),(\\d+)");
    }

    private String[] filterInstruction() {
        List<String> matches = Arrays.stream(getArray()
                .map(this::findInstructionMatch).flatMap(List::stream).toArray(String[]::new)).toList();
        List<String> filtered = new ArrayList<>();

        boolean isDo = true;
        for (String match : matches) {
            isDo = match.equals("do()") || !match.equals("don't()") && isDo;

            if (match.contains("mul") && isDo) {
                filtered.add(match);
            }
        }
        return filtered.toArray(String[]::new);
    }
}
