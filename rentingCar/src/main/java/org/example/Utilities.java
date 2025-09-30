package org.example;

import java.util.Scanner;

public class Utilities {

    public static String ask(Scanner scanner, String textToAsk) {

        System.out.println(textToAsk);
        return scanner.nextLine();
    }
}
