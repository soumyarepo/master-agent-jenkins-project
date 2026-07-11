package com.devops;

public class App {
    public static String message() {
        return "Hello from Jenkins Master-Agent Mini Project!";
    }

    public static int add(int first, int second) {
        return first + second;
    }

    public static void main(String[] args) {
        System.out.println(message());
        System.out.println("Application executed successfully on a Jenkins agent.");
        System.out.println("2 + 3 = " + add(2, 3));
    }
}
