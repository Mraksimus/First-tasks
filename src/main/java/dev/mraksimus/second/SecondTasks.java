package dev.mraksimus.second;

import java.util.Arrays;
import java.util.stream.DoubleStream;

// git: https://github.com/Mraksimus/Tasks

public class SecondTasks {

    public static void main(String[] args) {

        System.out.println(duplicateChars("Barack", "Obama"));

        System.out.println(dividedByThree(new int[]{3, 12, 7, 3, 81, 52}));

        System.out.println(getInitials("SiMoNov sErgey evgEnievIch"));

        System.out.println(Arrays.toString(normalizer(new double[]{10.0, 5.0, 6.0, 10.0})));

        System.out.println(Arrays.toString(compressedNums(new double[]{1.6, 0, 212.3, 34.8, 0, 27.5})));

        System.out.println(camelToSnake("CamelCase"));

        System.out.println(secondBiggest(new int[]{3, 5, 8, 1, 2, 4}));

        System.out.println(localReverse("Hello, I’m under the water, please help me", 'e'));

        System.out.println(equal(5, 5, 1));

        System.out.println(isAnagram("11 plus 2?", "12 plus 1!"));

    }

    // 1
    public static String duplicateChars(String firstString, String secondString) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < firstString.length(); i++) {

            char lowerCaseCharOfFirstString = firstString.toLowerCase().charAt(i);
            char baseChar = firstString.charAt(i);

            if (secondString.toLowerCase().indexOf(lowerCaseCharOfFirstString) == -1) {
                result.append(baseChar); // char not lowercased for correct output
            }

        }

        return result.toString();
    }

    // 2
    public static int dividedByThree(int[] arrayOfInt) {

        int count = 0;

        for (int i : arrayOfInt) {
            if (i % 3 == 0) {
                count++;
            }
        }

        return count;
    }

    // 3
    public static String getInitials(String fullName) {

        String[] parts = fullName.trim().split(" ");

        if (parts.length != 3) {
            throw new IllegalArgumentException("Input must contain exactly three parts: surname, name, and patronymic.");
        }

        String surname = parts[0];
        String name = parts[1];
        String patronymic = parts[2];

        return name.substring(0, 1).toUpperCase() + "." + patronymic.substring(0, 1).toUpperCase() + ". " +
                surname.substring(0, 1).toUpperCase() + surname.substring(1).toLowerCase();
    }

    // 4
    public static double[] normalizer(double[] arrayOfDouble) {

        if (arrayOfDouble.length == 0) {
            throw new IllegalArgumentException("Array cannot be empty");
        }

        double min = Arrays.stream(arrayOfDouble).min().getAsDouble();
        double max = Arrays.stream(arrayOfDouble).max().getAsDouble();

        if (min == max) {

            Arrays.fill(arrayOfDouble, 0.0);

            return arrayOfDouble;
        }

        double[] normalizedArray = new double[arrayOfDouble.length];
        for (int i = 0; i < arrayOfDouble.length; i++) {
            normalizedArray[i] = (arrayOfDouble[i] - min) / (max - min);
        }

        return normalizedArray;
    }

    // 5
    public static int[] compressedNums (double[] array) {
        return DoubleStream.of(array).filter(n -> n != 0).sorted().mapToInt(n -> (int) n).distinct().toArray();
    }

    // 6
    public static String camelToSnake (String str) {
        return str
                .replaceAll("([A-Z]+)([A-Z][a-z])", "$1_$2")
                .replaceAll("([a-z])([A-Z])", "$1_$2")
                .toLowerCase();
    }

    // 7
    public static int secondBiggest(int[] nums) {

        if (nums.length < 2) {
            throw new IllegalArgumentException("Input array must contain at least two elements.");
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int num : nums) {

            if (num > first) {
                second = first;
                first = num;
            } else if (num > second && num < first) {
                second = num;
            }
        }

        if (second == Integer.MIN_VALUE) {
            throw new IllegalArgumentException("There is no second biggest number.");
        }

        return second;
    }

    // 8
    public static String localReverse (String str, char marker) {

        StringBuilder result = new StringBuilder();
        int start = 0;
        int end;

        while ((end = str.indexOf(marker, start)) != -1) {

            result.append(str, start, end + 1);
            start = end + 1;
            end = str.indexOf(marker, start);

            if (end != -1) {

                String toReverse = str.substring(start, end);
                result.append(new StringBuilder(toReverse).reverse());
                result.append(marker);
                start = end + 1;
            }
        }

        result.append(str.substring(start));

        return result.toString();
    }

    // 9
    public static int equal (int a, int b, int c) {

        if (a == b && b == c) {
            return 3;
        } else if (a == b || a == c || b == c) {
            return 2;
        } else {
            return 0;
        }
    }

    // 10
    public static boolean isAnagram (String s1, String s2) {

        String cleanedString1 = s1.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        String cleanedString2 = s2.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        char[] charArray1 = cleanedString1.toCharArray();
        char[] charArray2 = cleanedString2.toCharArray();

        Arrays.sort(charArray1);
        Arrays.sort(charArray2);

        return Arrays.equals(charArray1, charArray2);
    }

}
