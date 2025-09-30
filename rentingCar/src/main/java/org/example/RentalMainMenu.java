package org.example;

import java.util.Scanner;

public class RentalMainMenu {

        public static void runner(DataStore myDataStore ) {

            Scanner scanner = new Scanner(System.in);

            while(true){

                MainMenuView.showMainMenu();
                String option = Utilities.ask(scanner, "Option? ");

                if (option.equals("0")) {
                    break;
                } else if (option.equals("1")){
                    // option #1;
                } else if (option.equals("2")){
                    // option #3;
                } else if (option.equals("3")){
                    // option #3;
                } else if (option.equals("4")){
                    // option #4;
                } else {
                    System.out.println("Unknown word");
                }
            }

        }



}

