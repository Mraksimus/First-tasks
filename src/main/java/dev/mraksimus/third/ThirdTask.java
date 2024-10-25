package dev.mraksimus.third;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

// git: https://github.com/Mraksimus/Tasks

public class ThirdTask {

    public static void main(String[] args) {

        //1
        System.out.println(isStrangePair("ratio", "orator"));
        System.out.println(isStrangePair("sparkling", "groups"));
        System.out.println(isStrangePair("bush", "hubris"));
        System.out.println(isStrangePair("", ""));

        //2
        List<String[]> products = new ArrayList<>();
        products.add(new String[] { "Laptop", "124200" });
        products.add(new String[] { "Phone", "51450" });
        products.add(new String[] { "Headphones", "13800" });

        List<String[]> updatedProducts = sale(products, 0.25);

        for (String[] product : updatedProducts) {
            System.out.println(product[0] + ": " + product[1]);
        }

        //3
        if (isHit(-2, -3, 4, 5, -6)) {
            System.out.println("Попадание в мишень!");
        } else {
            System.out.println("Мимо.");
        }

        //4
        if (parityAnalysis(12)) {
            System.out.println("Четность числа и суммы его цифр совпадают.");
        } else {
            System.out.println("Четность числа и суммы его цифр не совпадают.");
        }

        //5
        System.out.println(rps("rock", "paper"));
        System.out.println(rps("paper", "rock"));
        System.out.println(rps("paper", "scissors"));
        System.out.println(rps("scissors", "scissors"));
        System.out.println(rps("scissors", "paper"));

        //6
        System.out.println(bugger(39));
        System.out.println(bugger(999));
        System.out.println(bugger(4));

        //7
        String[][] inventory = {
            {"Скакалка", "550", "8"},
            {"Шлем", "3750", "4"},
            {"Мяч", "2900", "10"}
        };

        System.out.println(mostExpensive(inventory));

        //8
        System.out.println(longestUnique("abcba"));
        System.out.println(longestUnique("bbb"));

        //9
        System.out.println(isPrefix("automation", "auto-"));
        System.out.println(isSuffix("arachnophobia", "-phobia"));
        System.out.println(isPrefix("retrospect", "sub-"));
        System.out.println(isSuffix("vocation", "-logy"));

        //10
        System.out.println(doesBrickFit(1, 1, 1, 1, 1));
        System.out.println(doesBrickFit(1, 2, 1, 1, 1));
        System.out.println(doesBrickFit(1, 2, 2, 1, 1));

    }

    //1
    public static boolean isStrangePair(String str1, String str2) {

        if (str1.isEmpty() || str2.isEmpty()) {
            return false;
        }

        char str1FirstChar = str1.charAt(0);
        char str1LastChar = str1.charAt(str1.length() - 1);

        char str2FirstChar = str2.charAt(0);
        char str2LastChar = str2.charAt(str2.length() - 1);

        return str1FirstChar == str2LastChar && str1LastChar == str2FirstChar;
    }

    //2
    public static List<String[]> sale(List<String[]> products, double discount) {

        List<String[]> updatedProducts = new ArrayList<>();

        for (String[] product : products) {
            String name = product[0];
            double price = Double.parseDouble(product[1]);

            double newPrice = price * (1 - discount);

            newPrice = Math.max(Math.round(newPrice), 1);

            updatedProducts.add(new String[] { name, String.valueOf((int) newPrice) });
        }

        return updatedProducts;
    }

    //3
    public static boolean isHit(double x, double y, double r, double m, double n) {
        double distance = Math.sqrt(Math.pow(m - x, 2) + Math.pow(n - y, 2));
        return distance <= r;
    }

    //4
    public static boolean parityAnalysis(int number) {

        boolean isNumberEven = (number % 2 == 0);

        int sumOfDigits = 0;
        int tempNumber = Math.abs(number);

        while (tempNumber > 0) {
            sumOfDigits += tempNumber % 10;
            tempNumber /= 10;
        }

        boolean isSumEven = (sumOfDigits % 2 == 0);

        return isNumberEven == isSumEven;
    }

    //5
    public static String rps(String player1, String player2) {

        player1 = player1.toLowerCase();
        player2 = player2.toLowerCase();

        if (player1.equals(player2)) {
            return "Tie";
        } else if ((player1.equals("rock") && player2.equals("scissors")) ||
                (player1.equals("scissors") && player2.equals("paper")) ||
                (player1.equals("paper") && player2.equals("rock"))) {
            return "Player 1 wins";
        } else {
            return "Player 2 wins";
        }

    }

    //6
    public static int bugger(int number) {

        if (number < 10) {
            return 0;
        }

        int count = 0;
        while (number >= 10) {
            int product = 1;
            while (number > 0) {
                product *= number % 10;
                number /= 10;
            }
            number = product;
            count++;
        }

        return count;
    }

    //7
    public static String mostExpensive(String[][] items) {
        String mostExpensiveItem = "";
        int maxTotalValue = 0;

        for (String[] item : items) {
            String itemName = item[0];
            int price = Integer.parseInt(item[1]);
            int quantity = Integer.parseInt(item[2]);

            int totalValue = price * quantity;

            if (totalValue > maxTotalValue) {
                maxTotalValue = totalValue;
                mostExpensiveItem = itemName;
            }
        }

        return "Наиб. общ. стоимость у предмета " + mostExpensiveItem + " - " + maxTotalValue;
    }

    //8
    public static String longestUnique(String input) {
        String longestSubstring = "";
        String currentSubstring = "";
        HashSet<Character> uniqueChars = new HashSet<>();

        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);

            // Если символ уже присутствует, обновляем подстроку
            if (uniqueChars.contains(currentChar)) {
                if (currentSubstring.length() > longestSubstring.length()) {
                    longestSubstring = currentSubstring;
                }

                // Убираем символы до повторяющегося
                int index = currentSubstring.indexOf(currentChar);
                currentSubstring = currentSubstring.substring(index + 1);
                uniqueChars.clear();
                for (char c : currentSubstring.toCharArray()) {
                    uniqueChars.add(c);
                }
            }

            // Добавляем текущий символ
            currentSubstring += currentChar;
            uniqueChars.add(currentChar);
        }

        // Проверяем последнюю подстроку
        if (currentSubstring.length() > longestSubstring.length()) {
            longestSubstring = currentSubstring;
        }

        return longestSubstring;
    }

    //9
    public static boolean isPrefix(String word, String prefix) {
        String cleanedPrefix = prefix.substring(0, prefix.length() - 1);
        return word.startsWith(cleanedPrefix);
    }

    public static boolean isSuffix(String word, String suffix) {
        String cleanedSuffix = suffix.substring(1);
        return word.endsWith(cleanedSuffix);
    }

    //10
    public static boolean doesBrickFit(int a, int b, int c, int w, int h) {
        return (a <= w && b <= h) || (a <= h && b <= w) ||
                (a <= w && c <= h) || (a <= h && c <= w) ||
                (b <= w && c <= h) || (b <= h && c <= w);
    }

}
