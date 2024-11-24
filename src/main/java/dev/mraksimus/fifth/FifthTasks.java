package dev.mraksimus.fifth;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FifthTasks {

    public static void main(String[] args) throws ParseException {

        //1
        System.out.println(sameLetterPattern("ABAB", "CDCD"));
        System.out.println(sameLetterPattern("ABCBA", "BCDCB"));
        System.out.println(sameLetterPattern("FFGG", "CDCD"));
        System.out.println(sameLetterPattern("FFFF", "ABCD"));

        //2
        System.out.println(memeSum(26, 39)); // ➞ 515
        System.out.println(memeSum(122, 81)); // ➞ 1103
        System.out.println(memeSum(1222, 30277)); // ➞ 31499

        //3
        System.out.println(digitsCount(123456789)); // ➞ 9
        System.out.println(digitsCount(1234567890)); // ➞ 10
        System.out.println(digitsCount(1234567890)); // ➞ 19
        System.out.println(digitsCount(0)); // ➞ 1

        //4
        System.out.println(totalPoints(List.of("cat", "create", "sat"), "caster"));
        System.out.println(totalPoints(List.of("trance", "recant"), "recant"));
        System.out.println(totalPoints(List.of("dote", "dotes", "toes", "set", "dot", "dots", "sted"), "tossed"));

        //5
        System.out.println(longestRun(new int[]{1, 2, 3, 5, 6, 7, 8, 9}));
        System.out.println(longestRun(new int[]{1, 2, 3, 10, 11, 15}));
        System.out.println(longestRun(new int[]{5, 4, 2, 1}));
        System.out.println(longestRun(new int[]{3, 5, 7, 10, 15}));

        //6
        System.out.println(takeDownAverage(new String[]{"95%", "83%", "90%", "87%", "88%", "93%"}));
        System.out.println(takeDownAverage(new String[]{"10%"}));
        System.out.println(takeDownAverage(new String[]{"53%", "79%"}));

        //7
        System.out.println(canMove("Rook", "A8", "H8"));
        System.out.println(canMove("Bishop", "A7", "G1"));
        System.out.println(canMove("Queen", "C4", "D6"));

        //8
        System.out.println(maxPossible(9328, 456));
        System.out.println(maxPossible(523, 76));
        System.out.println(maxPossible(9132, 5564));
        System.out.println(maxPossible(8732, 91255));

        //9
        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra"));
        System.out.println(timeDifference("London", "July 31, 1983 23:01", "Rome"));
        System.out.println(timeDifference("New York", "December 31, 1970 13:40", "Beijing"));

        //10
        System.out.println(isNew(3));
        System.out.println(isNew(30));
        System.out.println(isNew(321));
        System.out.println(isNew(123));

    }

    //1
    public static boolean sameLetterPattern(String s1, String s2) {

        if (s1.length() != s2.length()) {
            return false;
        }

        String pattern1 = createPattern(s1);
        String pattern2 = createPattern(s2);

        return pattern1.equals(pattern2);
    }

    private static String createPattern(String str) {

        HashMap<Character, Integer> charMap = new HashMap<>();
        StringBuilder pattern = new StringBuilder();
        int index = 0;

        for (char c : str.toCharArray()) {
            charMap.putIfAbsent(c, index++);
            pattern.append(charMap.get(c));
        }

        return pattern.toString();
    }

    //2
    public static int memeSum(int a, int b) {

        StringBuilder result = new StringBuilder();

        String strA = new StringBuilder(String.valueOf(a)).reverse().toString();
        String strB = new StringBuilder(String.valueOf(b)).reverse().toString();

        int maxLength = Math.max(strA.length(), strB.length());

        for (int i = 0; i < maxLength; i++) {

            int digitA = i < strA.length() ? strA.charAt(i) - '0' : 0;
            int digitB = i < strB.length() ? strB.charAt(i) - '0' : 0;

            result.append(digitA + digitB);
        }

        return Integer.parseInt(result.reverse().toString());
    }

    //3
    public static int digitsCount(int number) {
        if (number < 10 && number >= 0) {
            return 1;
        } else {
            return 1 + digitsCount(number / 10);
        }
    }

    //4
    public static int totalPoints(List<String> guessedWords, String unscrambledWord) {
        int totalScore = 0;

        HashMap<Character, Integer> unscrambledCount = countLetters(unscrambledWord);

        for (String word : guessedWords) {
            if (isValidWord(word, unscrambledCount)) {
                int wordScore = calculateScore(word, word.equals(unscrambledWord));
                totalScore += wordScore;
            }
        }

        return totalScore;
    }

    private static HashMap<Character, Integer> countLetters(String word) {
        HashMap<Character, Integer> letterCount = new HashMap<>();
        for (char c : word.toCharArray()) {
            letterCount.put(c, letterCount.getOrDefault(c, 0) + 1);
        }
        return letterCount;
    }

    private static boolean isValidWord(String word, HashMap<Character, Integer> unscrambledCount) {
        HashMap<Character, Integer> wordCount = countLetters(word);

        for (char c : wordCount.keySet()) {
            if (wordCount.get(c) > unscrambledCount.getOrDefault(c, 0)) {
                return false;
            }
        }
        return true;
    }

    private static int calculateScore(String word, boolean isFullUnscrambledWord) {
        int length = word.length();
        int score = 0;

        if (length == 3) score = 1;
        else if (length == 4) score = 2;
        else if (length == 5) score = 3;
        else if (length == 6) score = 4;

        if (isFullUnscrambledWord && length == 6) {
            score += 50;
        }

        return score;
    }

    //5
    public static int longestRun(int[] arr) {

        if (arr.length == 0) return 0;

        int maxLength = 1;
        int currentLength = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1] + 1 || arr[i] == arr[i - 1] - 1) {
                currentLength++;
            } else {
                maxLength = Math.max(maxLength, currentLength);
                currentLength = 1;
            }
        }

        maxLength = Math.max(maxLength, currentLength);

        return maxLength;
    }

    //6
    public static String takeDownAverage(String[] grades) {

        int totalSum = 0;
        for (String grade : grades) {
            totalSum += Integer.parseInt(grade.replace("%", ""));
        }

        int currentAverage = totalSum / grades.length;

        int newAverage = currentAverage - 5;

        int requiredGrade = newAverage * (grades.length + 1) - totalSum;

        return requiredGrade + "%";
    }

    //7
    public static boolean canMove(String piece, String start, String end) {

        int startX = start.charAt(0) - 'A';
        int startY = start.charAt(1) - '1';
        int endX = end.charAt(0) - 'A';
        int endY = end.charAt(1) - '1';

        int dx = Math.abs(endX - startX);
        int dy = Math.abs(endY - startY);

        return switch (piece.toLowerCase()) {
            case "pawn" -> (dx == 0 && dy == 1) || (dx == 0 && dy == 2 && startY == 1);
            case "knight" -> (dx == 2 && dy == 1) || (dx == 1 && dy == 2);
            case "bishop" -> dx == dy;
            case "rook" -> dx == 0 || dy == 0;
            case "queen" -> dx == dy || dx == 0 || dy == 0;
            case "king" -> dx <= 1 && dy <= 1;
            default -> false;
        };
    }

    //8
    public static int maxPossible(int num1, int num2) {

        ArrayList<Integer> digits = new ArrayList<>();
        while (num2 > 0) {
            digits.add(num2 % 10);
            num2 /= 10;
        }

        digits.sort(Collections.reverseOrder());

        char[] num1Chars = String.valueOf(num1).toCharArray();
        int digitIndex = 0;

        for (int i = 0; i < num1Chars.length; i++) {
            if (digitIndex < digits.size() && digits.get(digitIndex) > Character.getNumericValue(num1Chars[i])) {
                num1Chars[i] = Character.forDigit(digits.get(digitIndex), 10);
                digitIndex++;
            }
        }

        return Integer.parseInt(new String(num1Chars));
    }

    //9
    public static String timeDifference(String cityA, String timestamp, String cityB) throws ParseException {

        HashMap<String, Double> gmtOffsets = new HashMap<>();
        gmtOffsets.put("Los Angeles", -8.0);
        gmtOffsets.put("New York", -5.0);
        gmtOffsets.put("Caracas", -4.5);
        gmtOffsets.put("Buenos Aires", -3.0);
        gmtOffsets.put("London", 0.0);
        gmtOffsets.put("Rome", 1.0);
        gmtOffsets.put("Moscow", 3.0);
        gmtOffsets.put("Tehran", 3.5);
        gmtOffsets.put("New Delhi", 5.5);
        gmtOffsets.put("Beijing", 8.0);
        gmtOffsets.put("Canberra", 10.0);

        double offsetA = gmtOffsets.get(cityA);
        double offsetB = gmtOffsets.get(cityB);

        SimpleDateFormat inputFormat = new SimpleDateFormat("MMMM d, yyyy HH:mm");
        inputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        Date dateInCityA = inputFormat.parse(timestamp);

        long offsetDifference = (long) ((offsetB - offsetA) * 3600 * 1000);

        Date dateInCityB = new Date(dateInCityA.getTime() + offsetDifference);

        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-M-d HH:mm");
        outputFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        return outputFormat.format(dateInCityB);
    }

    //10
    public static boolean isNew(int num) {

        String numStr = String.valueOf(num);

        char[] digits = numStr.toCharArray();

        char[] sortedDigits = digits.clone();
        Arrays.sort(sortedDigits);

        return Arrays.equals(digits, sortedDigits);
    }

}
