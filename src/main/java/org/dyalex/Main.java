package org.dyalex;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main( String[] args ) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение:");
        String userInput = scanner.nextLine().toUpperCase();
        if (Pattern.compile("[^-+*/0-9IVX ]").matcher(userInput).find()) {
            throw new Exception("Используются недопустимые символы");
        } else {
            System.out.println(calc(userInput));
        }
        scanner.close();

    }
    public static String calc(String input) throws Exception {
        StringBuilder operand1 = new StringBuilder();
        StringBuilder operand2 = new StringBuilder();
        StringBuilder romanOp1 = new StringBuilder();
        StringBuilder romanOp2 = new StringBuilder();
        String operationSign = "";
        String result;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == ' ') continue;
            if ((c == '-') || (c == '+') || (c == '*') || (c == '/')) {
                operationSign = String.valueOf(c);
            } else if (c >= '0' && c <= '9' && operationSign.isEmpty()) {
                operand1.append(c);
            } else if (c >= '0' && c <= '9' && !operationSign.isEmpty()) {
                operand2.append(c);
            } else if ((c == 'I' || c == 'V' || c == 'X') && operationSign.isEmpty()) {
                romanOp1.append(c);
            } else if ((c == 'I' || c == 'V' || c == 'X') && !operationSign.isEmpty()) {
                romanOp2.append(c);
            } else {
                throw new Exception("Используются недопустимые символы");
            }
        }

        if ((operand1.length() > 0) && (operand2.length() > 0)) {
            int op1 = Integer.parseInt(operand1.toString());
            int op2 = Integer.parseInt(operand2.toString());
            if (op1 >= 1 && op1 <= 10 && op2 >= 1 && op2 <= 10) {
                result = String.valueOf(calculation(op1, op2, operationSign));
            } else {
                throw new Exception("Калькулятор принимает на вход только числа от 1 до 10 включительно");
            }

        } else if ((romanOp1.length() > 0) && (romanOp2.length() > 0)) {
            int op1 = convertToArabNumerics(romanOp1.toString());
            int op2 = convertToArabNumerics(romanOp2.toString());
            if (op1 >= 1 && op1 <= 10 && op2 >= 1 && op2 <= 10) {
                result = convertToRomanNumerics(calculation(op1, op2, operationSign));
            } else {
                throw new Exception("Калькулятор принимает на вход только числа от 1 до 10 включительно");
            }
        } else {
            throw new Exception("Используются римские и арабские цифры вперемежку либо недопустимое выражение");
        }

//        return "This is your input: " + input + ". Result is: " + result;
        return result;
    }

    /*
    Method takes 2 integer operands and arithmetic operation sign.
    Then returns result of the arithmetic operation on these operands.
     */
    static int calculation(int op1, int op2, String operationSign) {
        int result = 0;
        switch (operationSign){
            case "-":
                result = (op1 - op2);
                break;
            case "+":
                result = (op1 + op2);
                break;
            case "*":
                result = (op1 * op2);
                break;
            case "/":
                result = (op1 / op2);
        }
        return result;
    }

    /*
    Method converts string of roman number to arabic number. Returns integer.
     */
    static int convertToArabNumerics(String operand) {
        return RomanNumeric.valueOf(operand).getArabicNumber();
    }

    /*
    Method converts integer to string of Roman numerics
     */
    static String convertToRomanNumerics(int operand) throws Exception {
        if (operand <= 0) {
            throw new Exception("Результат работы меньше единицы");
        }
        return String.valueOf(RomanNumeric.findByValue(operand));
    }
}
