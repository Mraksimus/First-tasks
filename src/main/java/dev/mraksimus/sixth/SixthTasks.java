package dev.mraksimus.sixth;

import java.util.*;
import java.util.stream.Collectors;

// git: https://github.com/Mraksimus/Tasks

public class SixthTasks {

    public static void main(String[] args) {

        // Task 1
        System.out.println(hiddenAnagram("My world evolves in a beautiful space called Tesh.", "sworn love lived"));
        System.out.println(hiddenAnagram("An old west action hero actor", "Clint Eastwood"));

        // Task 2
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2"));
        System.out.println(stripUrlParams("https://edabit.com?a=1&b=2&a=2", new String[]{"b"}));

        // Task 3
        System.out.println(nicoCipher("mubashirhassan", "crazy"));

        // Task 4
        System.out.println(Arrays.toString(twoProduct(new int[]{1, 2, 3, 9, 4, 5, 15, 3}, 45)));

        // Task 5
        System.out.println(Arrays.toString(isExact(720)));

        // Task 6
        System.out.println(fractions("0.(6)"));

        // Task 7
        System.out.println(pilishString("33314444"));

        // Task 8
        System.out.println(formula("6*4=24"));

        // Task 9
        System.out.println(isValid("abcdefghhgfedecba"));

        // Task 10
        System.out.println(palindromeDescendant(11211230));

    }

    // Task 1
    public static String hiddenAnagram(String sentence, String target) {

        sentence = sentence.replaceAll("[^a-zA-Z]", "").toLowerCase();
        target = target.replaceAll("[^a-zA-Z]", "").toLowerCase();

        int[] targetCount = new int[26];
        for (char c : target.toCharArray()) {
            targetCount[c - 'a']++;
        }

        int[] windowCount = new int[26];
        for (int i = 0; i < target.length(); i++) {
            windowCount[sentence.charAt(i) - 'a']++;
        }

        for (int i = 0; i <= sentence.length() - target.length(); i++) {

            if (Arrays.equals(targetCount, windowCount)) {
                return sentence.substring(i, i + target.length());
            }

            if (i + target.length() < sentence.length()) {
                windowCount[sentence.charAt(i) - 'a']--;
                windowCount[sentence.charAt(i + target.length()) - 'a']++;
            }

        }

        return "notfound";
    }

    // Task 2
    public static String stripUrlParams(String url, String[]... toStrip) {

        if (!url.contains("?")) return url;

        String[] parts = url.split("\\?", 2);
        String baseUrl = parts[0];
        String[] params = parts[1].split("&");

        Map<String, String> paramMap = new LinkedHashMap<>();
        for (String param : params) {
            String[] pair = param.split("=");
            paramMap.put(pair[0], pair[1]);
        }

        if (toStrip.length > 0) {
            Set<String> toStripSet = new HashSet<>(Arrays.asList(toStrip[0]));
            paramMap.keySet().removeAll(toStripSet);
        }

        String queryString = paramMap.entrySet().stream()
                .map(e -> e.getKey() + "=" + e.getValue())
                .collect(Collectors.joining("&"));

        return baseUrl + "?" + queryString;
    }

    // Task 3
    public static String nicoCipher(String message, String key) {

        int[] order = key.chars().boxed().sorted().mapToInt(key::indexOf).toArray();
        StringBuilder padded = new StringBuilder(message);
        while (padded.length() % key.length() != 0) {
            padded.append(' ');
        }

        StringBuilder encoded = new StringBuilder();
        for (int i = 0; i < padded.length(); i += key.length()) {
            char[] chunk = padded.substring(i, i + key.length()).toCharArray();
            for (int o : order) {
                encoded.append(chunk[o]);
            }
        }

        return encoded.toString();
    }

    // Task 4
    public static int[] twoProduct(int[] arr, int n) {

        Map<Integer, Integer> seen = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (n % arr[i] == 0 && seen.containsKey(n / arr[i])) {
                return new int[]{n / arr[i], arr[i]};
            }
            seen.put(arr[i], i);
        }

        return new int[0];
    }

    // Task 5
    public static int[] isExact(int n) {
        return isExactHelper(n, 1);
    }

    private static int[] isExactHelper(int n, int i) {

        if (n == 1) return new int[]{n, i - 1};
        if (n % i != 0) return new int[0];

        return isExactHelper(n / i, i + 1);
    }

    // Task 6
    public static String fractions(String decimal) {
        if (!decimal.contains("(")) {
            return fractionsNonRepeating(decimal);
        } else {
            return fractionsRepeating(decimal);
        }
    }

    private static String fractionsNonRepeating(String decimal) {

        String[] parts = decimal.split("\\.");
        int integerPart = Integer.parseInt(parts[0]);
        int fractionalPart = Integer.parseInt(parts[1]);
        int denominator = (int) Math.pow(10, parts[1].length());
        int numerator = integerPart * denominator + fractionalPart;
        int gcd = gcd(numerator, denominator);

        return (numerator / gcd) + "/" + (denominator / gcd);
    }

    private static String fractionsRepeating(String decimal) {

        String[] parts = decimal.split("\\.");
        String nonRepeating = parts[1].split("\\(")[0];
        String repeating = parts[1].split("\\(")[1].replace(")", "");

        int numerator = Integer.parseInt(parts[0] + nonRepeating + repeating) -
                Integer.parseInt(parts[0] + nonRepeating);
        int denominator = (int) Math.pow(10, nonRepeating.length() + repeating.length()) -
                (int) Math.pow(10, nonRepeating.length());

        int gcd = gcd(numerator, denominator);

        return (numerator / gcd) + "/" + (denominator / gcd);
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // Task 7
    public static String pilishString(String txt) {

        final int[] PI = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5, 8, 9, 7, 9};
        StringBuilder result = new StringBuilder();
        int piIndex = 0;

        for (int i = 0; i < txt.length(); ) {
            int len = PI[piIndex++];
            if (i + len > txt.length()) {
                StringBuilder lastPart = new StringBuilder(txt.substring(i));
                while (lastPart.length() < len) {
                    lastPart.append(lastPart.charAt(lastPart.length() - 1));
                }
                result.append(lastPart).append(" ");
                break;
            } else {
                result.append(txt, i, i + len).append(" ");
                i += len;
            }
        }

        return result.toString().trim();
    }

    // Task 8
    public static boolean formula(String expr) {

        String [] part = expr.split("=");
        String [] rez = new String[part.length];

        for(int i = 0; i < part.length; i++) {
            rez[i] = part[i];
            if (part[i].contains("*")) {
                int a = Integer.parseInt(part[i].substring(0, part[i].indexOf("*")));
                int b = Integer.parseInt(part[i].substring(part[i].indexOf("*")+1));
                rez[i] = Integer.toString(a*b);
            }
            if (part[i].contains("+")) {
                int a = Integer.parseInt(part[i].substring(0, part[i].indexOf("+")));
                int b = Integer.parseInt(part[i].substring(part[i].indexOf("+")+1));
                rez[i] = Integer.toString(a+b);
            }
            if (part[i].contains("/")) {
                int a = Integer.parseInt(part[i].substring(0, part[i].indexOf("/")));
                int b = Integer.parseInt(part[i].substring(part[i].indexOf("/")+1));
                rez[i] = Integer.toString(a/b);
            }
            if (part[i].contains("-")) {
                int a = Integer.parseInt(part[i].substring(0, part[i].indexOf("-")));
                int b = Integer.parseInt(part[i].substring(part[i].indexOf("-")+1));
                rez[i] = Integer.toString(a-b);
            }
        }

        boolean x = true;

        for (int i = 0; i < rez.length-1; i++) {
            for (int j = i+1; j < rez.length ;j++) {
                if (!rez[i].equals(rez[j])) {
                    x = false;
                    break;
                }
            }
        }

        return x;
    }

    // Task 9
    public static String isValid(String str) {

        Map<Character, Integer> freq = new HashMap<>();
        for (char c : str.toCharArray()) freq.put(c, freq.getOrDefault(c, 0) + 1);
        Map<Integer, Integer> countFreq = new HashMap<>();
        for (int count : freq.values()) countFreq.put(count, countFreq.getOrDefault(count, 0) + 1);

        if (countFreq.size() == 1) return "YES";
        if (countFreq.size() == 2) {
            List<Integer> keys = new ArrayList<>(countFreq.keySet());
            Collections.sort(keys);
            if ((keys.get(0) == 1 && countFreq.get(keys.get(0)) == 1) ||
                    (keys.get(1) - keys.get(0) == 1 && countFreq.get(keys.get(1)) == 1)) {
                return "YES";
            }
        }

        return "NO";
    }

    // Task 10
    public static boolean palindromeDescendant(int num) {
        String s = String.valueOf(num);
        while (s.length() > 1) {
            if (new StringBuilder(s).reverse().toString().equals(s)) return true;
            StringBuilder next = new StringBuilder();
            for (int i = 0; i < s.length(); i += 2) {
                next.append((s.charAt(i) - '0') + (s.charAt(i + 1) - '0'));
            }
            s = next.toString();
        }
        return false;
    }

}
