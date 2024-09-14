package dev.mraksimus;

import dev.mraksimus.types.TrainingIntensity;

public class Main {

    public static void main(String[] args) {

        System.out.println(convertGallonsToLiters(8));
        System.out.println(fitCalc(15, TrainingIntensity.LOW));
        System.out.println(containers(3, 4, 2));
        System.out.println(triangleType(3, 4, 5));
        System.out.println(ternaryEvaluation(8, 4));
        System.out.println(howManyItems(22, 1.4, 2));
        System.out.println(factorial(3));
        System.out.println(gcd(259, 28));
        System.out.println(revenueCalculator(53, 1250));
        System.out.println(tables(5, 2));

    }

    //1
    public static double convertGallonsToLiters(double gallons) {
        return gallons * 3.78541178;
    }

    //2
    public static int fitCalc(
        int time,
        TrainingIntensity intensity
    ) {
        return time * intensity.getValue();
    }

    //3
    public static int containers(
        int boxesCount,
        int bagsCount,
        int barrelsCount
    ) {

        final int boxesCapacity = 50;
        final int bagsCapacity = 100;
        final int barrelsCapacity = 200;

        return boxesCount * boxesCapacity
            + bagsCount * bagsCapacity
            + barrelsCount * barrelsCapacity;
    }

    //4
    public static String triangleType(int a, int b, int c) {

        if (a + b < c || a + c < b || b + c < a) {
            return "Не триугольник";
        }

        if (a == b && b == c) {
            return "Равносторонний";
        } else if (a != b && b != c && c != a) {
            return "Разносторонний";
        } else {
            return "Равнобедренный";
        }

    }

    // 5
    public static int ternaryEvaluation(int a, int b) {
        return a > b ? a : b;
    }

    // 6
    public static int howManyItems(
        int length,
        double widthOfItem,
        double heightOfItem
    ) {

        double areaOfItem = widthOfItem * heightOfItem * 2;
        double areaOfCloth = length * 2; //В примерах таска не учитывается length * 2

        return (int) (areaOfCloth / areaOfItem);
    }

    //7
    public static int factorial(int n) {
        return n == 0 ? 1 : n * factorial(n - 1);
    }

    //8
    public static int gcd(int a, int b) {

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    //9
    public static int revenueCalculator(
        int numberOfItems,
        int pricePerItem
    ) {

        final double commissionOfItem = 0.28;

        return (int) (numberOfItems * pricePerItem * (1 - commissionOfItem));
    }

    //10
    public static int tables(
        int students,
        int desks
    ) {

        final int places = desks * 2;

        return students > places ? students - places : 0;
    }

}


