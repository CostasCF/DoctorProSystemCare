package com.WebFlexers;

import java.util.Random;

public class StringUtility {
    /**
     * Generates random string with pattern "LLNNNN" L = Letter, N = Number
     * @return the produced string
     */
    public static String generateRandomId()
    {
        String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "1234567890";

        StringBuilder part1 = new StringBuilder();
        Random rnd = new Random();
        while (part1.length() < 2) { // length of the random string.
            int index = (int) (rnd.nextFloat() * letters.length());
            part1.append(letters.charAt(index));
        }

        StringBuilder part2 = new StringBuilder();
        while (part2.length() < 4) { // length of the random string.
            int index = (int) (rnd.nextFloat() * numbers.length());
            part2.append(numbers.charAt(index));
        }

        String part1Str = part1.toString();
        String part2Str = part2.toString();

        return part1Str + part2Str;
    }
}
