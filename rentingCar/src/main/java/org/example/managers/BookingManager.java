package org.example.managers;

import org.example.dataStore.DataStore;
import org.example.model.Booking;
import org.example.model.Car;
import org.example.utilities.Utilities;

import java.util.Scanner;

public class BookingManager {


    public static void createBooking(DataStore myDataStore, Scanner scanner) {
        // todo
        System.out.println("Welcome to Booking Manager");
        System.out.println("-------------------------" );

        System.out.println("Logged client: " + myDataStore.getLoggedClient().getName());
        System.out.println("Logged client ID: " + myDataStore.getLoggedClient().getId());
        System.out.println("Logged client email: " + myDataStore.getLoggedClient().getEmail());

        Booking booking = new Booking();
        booking.setId("0011111");

        String daysRented = Utilities.ask(scanner, "How many days do you want to rent the car? ");
        int intDaysRented = Integer.valueOf(daysRented);

        booking.setDays(intDaysRented);
        booking.setActive(true);
        booking.setClient(myDataStore.getLoggedClient());

        CarManager.printCarList(myDataStore.getCars());

        // todo: implement error handling
        // bug#1: check for valid integer
        // bug#2: check for valid index, withing bounds
        String carIndex = Utilities.ask(scanner, "Car index? ");
        Car selectedCar = myDataStore.getCars().get(Integer.valueOf(carIndex) - 1);

        booking.setPrice(intDaysRented * selectedCar.getPrice());
        booking.setCar(selectedCar);

        System.out.println("Booking created: " + booking.toString());

        // todo: implement error handling
        // todo: add are you sure?
        // todo: add booking to the list

        //String option = Utilities.ask(scanner, "Option? ");


    }
}
