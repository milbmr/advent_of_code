package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public abstract class Aoc {

    private final static Logger LOG = LoggerFactory.getLogger(Aoc.class);
    private final int day;
    private final String DEFAULT_DELIMITER = "\\s+";

    public Aoc(int day) {
        this.day = day;
    }

    private String getDayPath() {
        return "day" + day + ".txt";
    }

    private String getResource(String resourceName) {
        try {
            return Files.readString(getFile(resourceName).toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File getFile(String path) {
        return new File("src/main/resources/days/" + path);
    }

    protected String getDayInput() {
        return getResource(getDayPath());
    }

    protected String[] getInput(String input) {
        return input.split(DEFAULT_DELIMITER);
    }

    protected String[] getInput(String input, String delimiter) {
        return input.split(delimiter);
    }

    protected String removeElement(String[] old, int n) {
        String[] newArray = new String[old.length - 1];

        for (int i = 0, k = 0; i < old.length; i++) {
            if (i == n) continue;
            newArray[k++] = old[i];
        }
        return String.join(" ", newArray);
    }

    protected Stream<String> getArray() {
        return getArray("\n");
    }

    protected Stream<String> getArray(String delimiter) {
        return Arrays.stream(getDayInput().split(delimiter));
    }

    public abstract void printOutput();

    public abstract int part1();
    public abstract int part2();

    protected <T> List<T> parseInput(Class<T> cont) {
        return getArray().filter(s -> !s.isBlank()).map(s -> mapStream(s, cont, DEFAULT_DELIMITER)).toList();
    }

    @SuppressWarnings("unchecked")
    protected  <T> T mapStream(String input, Class<T> container, String delimiter) {
        Object[] mapArray = parseInt(input, delimiter);

        try {
            return (T) container.getConstructors()[0].newInstance(mapArray);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private Object[] parseInt(String input, String delimiter) {
        return Arrays.stream(input.split(delimiter)).map(i -> (Object) Integer.parseInt(i)).toArray();
    }

    private Object[] parseInt(String input) {
        return Arrays.stream(input.split(DEFAULT_DELIMITER)).map(i -> (Object) Integer.parseInt(i)).toArray();
    }

    protected Stream<MatchResult> matchPattern(String input, String pattern) {
        Pattern pat = Pattern.compile(pattern);
        Matcher matcher = pat.matcher(input);

        return matcher.results();
    }
}


