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

    private <T> T mapStream(String input, Class<T> container) {
        List<Object> mapped = new ArrayList<>();

        int[] mapArray = parseInt(input);
        return (T) Arrays.stream(s)
    }

    private int[] parseInt(String input) {
        return Arrays.stream(input.split(DEFAULT_DELIMITER)).mapToInt(Integer::parseInt).toArray();
    }
}


