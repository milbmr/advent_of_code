package org.advent_of_code.year2024;

import common.Aoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.MatchResult;
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
        return matchPattern(getDayInput(), "mul\\((\\d+,\\d+)\\)")
                .map(this::findNumMatch).map(i -> mapStream(i, Pair.class, ",")).mapToInt(p -> p.a() * p.b()).sum();
    }

    @Override
    public int part2() {
        AtomicBoolean isDo = new AtomicBoolean(true);
        return matchPattern(getDayInput(), "mul\\((\\d+,\\d+)\\)|(do\\(\\))|(don't\\(\\))")
                .map(p -> filterInstruction(p, isDo)).filter(Objects::nonNull)
                .map(i -> mapStream(i, Pair.class, ",")).mapToInt(p -> p.a() * p.b()).sum();
    }

    private String findNumMatch(MatchResult matchResult) {
        return matchResult.group(1);
    }

    private String filterInstruction(MatchResult matchResult, AtomicBoolean isDo) {
            boolean isSet = matchResult.group(2) != null || matchResult.group(3) == null && isDo.get();
            isDo.set(isSet);

            if (matchResult.group(1) != null && isDo.get()) {
                return matchResult.group(1);
            }
            return null;
    }
}
