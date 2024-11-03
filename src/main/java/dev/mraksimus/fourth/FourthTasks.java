package dev.mraksimus.fourth;

import java.util.*;

// git: https://github.com/Mraksimus/Tasks

public class FourthTasks {

    public static void main(String[] args) {

        //1
        System.out.println(nonRepeat("aaabbbbccc"));

        //2
        System.out.println(bruteForce(1, 5));
        System.out.println(bruteForce(2, 2));
        System.out.println(bruteForce(5, 3));

        //3
        List<Integer> encoded = List.of(0, 31, 28, 10, 29);
        String key = "MKIIT";
        System.out.println(encode(encoded, key));
        System.out.println(decode("MTUCI", key));

        //4
        System.out.println(split("()()()"));
        System.out.println(split("((()))"));
        System.out.println(split("((()))(())()()(()())"));
        System.out.println(split("((())())(()(()()))"));

        //5
        System.out.println(shortHand("abbccc"));
        System.out.println(shortHand("vvvvaajaaaaa"));

        //6
        System.out.println(convertToRome(8));
        System.out.println(convertToRome(1234));
        System.out.println(convertToRome(52));

        //7
        System.out.println(uniqueSubstring("31312131"));
        System.out.println(uniqueSubstring("1111111"));
        System.out.println(uniqueSubstring("12223234333"));

        //8
        int[][] matrix1 = {
                {1, 3, 1},
                {1, -1, 1},
                {4, 2, 1}
        };
        int[][] matrix2 = {
                {2, -7, 3},
                {-4, -1, 8},
                {4, 5, 9}
        };
        System.out.println(labyrinth(matrix1));
        System.out.println(labyrinth(matrix2));

        //9
        System.out.println(numericOrder("t3o the5m 1One all6 r4ule ri2ng"));
        System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat"));

        //10
        System.out.println(fibString("CCCABDD"));
        System.out.println(fibString("ABC"));

    }

    //1
    public static String nonRepeat(String s) {
        return nonRepeat(s, 0);
    }

    private static String nonRepeat(String s, int index) {

        if (index >= s.length()) {
            return s;
        }

        s = s.toLowerCase();

        char currentChar = s.charAt(index);

        long count = s.chars().filter(ch -> ch == currentChar).count();

        if (count > 3) {
            s = s.replaceAll("(?i)" + currentChar, "");
            return nonRepeat(s, index);
        } else {
            return nonRepeat(s, index + 1);
        }

    }

    //2
    public static List<String> bruteForce(int n, int k) {

        if (n > k) {
            return new ArrayList<>();
        }

        char[] alphabet = new char[k];
        for (int i = 0; i < k; i++) {
            alphabet[i] = (char) ('0' + i);
        }

        List<String> combinations = new ArrayList<>();
        generateCombinations(alphabet, "", n, combinations);
        return combinations;
    }

    private static void generateCombinations(char[] alphabet, String current, int length, List<String> combinations) {

        if (current.length() == length) {
            combinations.add(current);
            return;
        }

        for (char c : alphabet) {
            if (!current.contains(String.valueOf(c))) {
                generateCombinations(alphabet, current + c, length, combinations);
            }
        }

    }

    //3
    public static String encode(List<Integer> numbers, String key) {

        StringBuilder encodedString = new StringBuilder();

        for (int i = 0; i < numbers.size(); i++) {
            char encodedChar = (char) (numbers.get(i) ^ key.charAt(i % key.length()));
            encodedString.append(encodedChar);
        }

        return encodedString.toString();
    }

    public static List<Integer> decode(String message, String key) {

        List<Integer> decodedNumbers = new ArrayList<>();

        for (int i = 0; i < message.length(); i++) {
            int decodedNum = message.charAt(i) ^ key.charAt(i % key.length());
            decodedNumbers.add(decodedNum);
        }

        return decodedNumbers;
    }

    //4
    public static List<String> split(String s) {

        List<String> clusters = new ArrayList<>();

        int balance = 0;

        StringBuilder currentCluster = new StringBuilder();

        for (char c : s.toCharArray()) {
            currentCluster.append(c);
            balance += (c == '(') ? 1 : -1;

            if (balance == 0) {
                clusters.add(currentCluster.toString());
                currentCluster.setLength(0);
            }
        }

        return clusters;
    }

    //5
    public static String shortHand(String s) {

        StringBuilder result = new StringBuilder();
        int count = 1;

        for (int i = 1; i <= s.length(); i++) {
            if (i < s.length() && s.charAt(i) == s.charAt(i - 1)) {
                count++;
            } else {
                result.append(s.charAt(i - 1));
                if (count > 1) {
                    result.append("*").append(count);
                }
                count = 1;
            }
        }

        return result.toString();
    }

    //6
    public static String convertToRome(int number) {

        if (number < 1 || number > 1502) {
            throw new IllegalArgumentException("Number out of range");
        }

        String[] thousands = {"", "M", "MM", "MMM"};
        String[] hundreds = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String[] ones = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

        return thousands[number / 1000] +
                hundreds[(number % 1000) / 100] +
                tens[(number % 100) / 10] +
                ones[number % 10];
    }

    //7
    public static String uniqueSubstring(String s) {

        Map<Character, Integer> counts = new HashMap<>();
        int[] maxIndex = {-1};
        int[] maxCount = {0};

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);
            counts.put(ch, counts.getOrDefault(ch, 0) + 1);

            if (counts.get(ch) > maxCount[0] || (counts.get(ch) == maxCount[0] && maxIndex[0] == -1)) {
                maxCount[0] = counts.get(ch);
                maxIndex[0] = i;
            }
        }

        return (maxIndex[0] % 2 == 0) ? "чет" : "нечет";
    }

    //8
    public static List<String> labyrinth(int[][] matrix) {

        int n = matrix.length;
        int[][] dp = new int[n][n];

        if (matrix[n - 1][n - 1] < 0 || matrix[0][0] < 0) {
            return Collections.singletonList("Прохода нет");
        }

        dp[n - 1][n - 1] = matrix[n - 1][n - 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (matrix[i][j] < 0) continue;

                if (i < n - 1 && matrix[i + 1][j] >= 0) {
                    dp[i][j] = dp[i + 1][j] + matrix[i][j];
                }
                if (j < n - 1 && matrix[i][j + 1] >= 0) {
                    dp[i][j] = dp[i][j + 1] + matrix[i][j];
                }
            }
        }

        if (dp[0][0] == 0) {
            return Collections.singletonList("Прохода нет");
        }

        List<String> path = new ArrayList<>();
        int i = 0, j = 0;
        while (i != n - 1 || j != n - 1) {
            path.add(String.valueOf(matrix[i][j]));
            if (i < n - 1 && dp[i + 1][j] == dp[i][j] - matrix[i][j]) i++;
            else j++;
        }
        path.add(String.valueOf(matrix[n - 1][n - 1]));

        return List.of(String.join("-", path), String.valueOf(dp[0][0]));
    }

    //9
    public static String numericOrder(String s) {
        return Arrays.stream(s.split(" "))
                .sorted(Comparator.comparingInt(word -> Integer.parseInt(word.replaceAll("\\D", ""))))
                .map(word -> word.replaceAll("\\d", ""))
                .reduce((a, b) -> a + " " + b)
                .orElse("");
    }

    //10
    private static boolean isFibonacci(int n) {

        int a = 0, b = 1;
        while (b < n) {
            int temp = a;
            a = b;
            b = temp + b;
        }

        return n == b;
    }

    public static boolean fibString(String s) {

        Map<Character, Integer> counts = new HashMap<>();

        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        for (int count : counts.values()) {
            if (!isFibonacci(count)) {
                return false;
            }
        }

        return true;
    }

}
