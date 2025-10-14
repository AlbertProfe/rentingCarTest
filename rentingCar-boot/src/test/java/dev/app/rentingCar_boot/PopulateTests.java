package dev.app.rentingCar_boot;

import dev.app.rentingCar_boot.utils.PopulateBooking;
import dev.app.rentingCar_boot.utils.PopulateCar;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PopulateTests {

    @Autowired
    PopulateBooking populateBooking;

    @Autowired
    PopulateCar populateCar;

    @Test
    void populateAllTables () {
        // let s populate cars first
        populateCar.populateCar(10);
        // once cars are populated, let s populate bookings
        populateBooking.populateBooking(10);
    }

}
