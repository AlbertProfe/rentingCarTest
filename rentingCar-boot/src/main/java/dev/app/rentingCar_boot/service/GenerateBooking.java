package dev.app.rentingCar_boot.service;

import dev.app.rentingCar_boot.model.Car;
import dev.app.rentingCar_boot.model.Client;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class GenerateBooking {

    public String generateBooking(Client client, Car car, int bookingDate, int qtyDays) {

        // 1. defensive programming : not null data

        // 2. check availability

        // 3. calculate total amount: maths at calculateTotalAmount

        // 4. create booking: Booking myBooking = new Booking();

        // 5. setting our booking with revised and legit data

        // 6. call createBooking and save it with bookingRepository

        // 7. update Car availibalty attribute

        // 8  return booking done




        return null;
    }

    public double calculateTotalAmount(Car car, int qtyDays) {

        // to do
        return 0.0;
    }

    public boolean checkAvailability(Car car, int bookingDate, int qtyDays) {
        Map<Integer, Boolean> availableDates = car.getAvailableDates();

        // Check each day in the requested booking period
        for (int i = 0; i < qtyDays; i++) {
            int currentDate = bookingDate + (i * 86400); // Add 86400 seconds (1 day) per iteration

            // If date exists in HashMap and is false (unavailable), return false
            if (availableDates.containsKey(currentDate) && !availableDates.get(currentDate)) {
                return false; // Car is not available on this date
            }
        }

        return true; // All dates are available
    }
}
