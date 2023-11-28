package org.dyalex;

import java.util.Scanner;

public class Main {
    public static void main( String[] args )
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение:");
        String userInput = scanner.nextLine();
        System.out.println(calc(userInput));
        scanner.close();
    }
    public static String calc(String input) {
        String[] userInput = input.split(" ");
        for (String str : userInput) {
            System.out.println(str);
        }

        return "This is your input " + input;
    }

//    String [] parseUserInput(String userInput) {
//
//        return [];
//    }
}
