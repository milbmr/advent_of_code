package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Util {

    private static final Logger LOG = LoggerFactory.getLogger(Util.class);

    public static void readToArray(File file, ArrayList<Integer> arr1, ArrayList<Integer> arr2) {

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(" {3}");
                int[] nums = parseInts(tokens);
                arr1.add(nums[0]);
                arr2.add(nums[1]);
            }
            scanner.close();
        }
        catch (FileNotFoundException e) {
            LOG.error("File not found", e);
        }
    }

    public static File readFile(String fileName) {
        return new File("/home/milood/Downloads/aoc/" + fileName + ".txt");
    }

    private static int[] parseInts(String[] s) {
        int num1 = Integer.parseInt(s[0]);
        int num2 = Integer.parseInt(s[1]);
        return new int[]{num1, num2};
    }
}
