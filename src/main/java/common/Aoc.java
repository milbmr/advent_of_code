package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
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
        return new File("src/main/resources/" + path);
    }

    private String getDayInput() {
        return getResource(getDayPath());
    }

    protected Stream<String> getArray() {
        return getArray("\n");
    }

    protected Stream<String> getArray(String delimiter) {
        return Arrays.stream(getDayInput().split(delimiter));
    }

    public abstract int part1();
    public abstract int part2();

    public abstract void printOutput();

    protected <T> List<T> parseInput(Class<T> cont) {
        return getArray().filter(s -> !s.isBlank()).map(s -> mapStream(s, cont)).toList();
    }

    @SuppressWarnings("unchecked")
    private <T> T mapStream(String input, Class<T> container) {
        Object[] mapArray = parseInt(input);

        try {
            return (T) container.getConstructors()[0].newInstance(mapArray);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private Object[] parseInt(String input) {
        return Arrays.stream(input.split(DEFAULT_DELIMITER)).map(i -> (Object) Integer.parseInt(i)).toArray();
    }
}


