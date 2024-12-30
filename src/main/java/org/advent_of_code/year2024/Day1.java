package org.advent_of_code.year2024;

import common.Aoc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.Function;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.*;

public class Day1 extends Aoc {

    private static final Logger LOG = LoggerFactory.getLogger(Day1.class);

    public record Pair(int a, int b) {}

    public Day1() {
        super(1);
    }

    public static void main(String[] args) {
        new Day1().printOutput();
    }

    @Override
    public void printOutput() {
        int totalDistance = part1();
        LOG.info("total distance: {}", totalDistance);

        int similarityScore = part2();
        LOG.info("similarity score: {}", similarityScore);
    }

    @Override
    public int part1() {
        List<Pair> pairs = parseInput(Pair.class);

        List<Integer> leftList = pairs.stream().map(Pair::a).sorted().toList();
        List<Integer> rightList = pairs.stream().map(Pair::b).sorted().toList();

        return totalDistance(leftList, rightList);
    }

    @Override
    public int part2() {
        List<Pair> pairs = parseInput(Pair.class);

        List<Integer> leftList = pairs.stream().map(Pair::a).toList();
        List<Integer> rightList = pairs.stream().map(Pair::b).toList();

        return similarityScore(leftList, rightList);
    }

    private Integer similarityScore(List<Integer> leftList, List<Integer> rightList) {
        Map<Integer, Integer> map = rightList.stream().collect(groupingBy(Function.identity(), collectingAndThen(counting(), Long::intValue)));

        return leftList.stream().mapToInt(i -> i * map.getOrDefault(i, 0)).sum();
    }

    private int totalDistance(List<Integer> leftList, List<Integer> rightList) {
        return IntStream.range(0, leftList.size()).map(i -> Math.abs(leftList.get(i) - rightList.get(i))).sum();
    }

}
