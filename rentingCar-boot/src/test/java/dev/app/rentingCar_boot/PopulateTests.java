package dev.app.rentingCar_boot;

import dev.app.rentingCar_boot.utils.PopulateStatus;
import dev.app.rentingCar_boot.utils.PopulateBooking;
import dev.app.rentingCar_boot.utils.PopulateCar;
import dev.app.rentingCar_boot.utils.PopulateClient;
import dev.app.rentingCar_boot.utils.PopulateDrivingCourse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PopulateTests {

    @Autowired
    PopulateBooking populateBooking;

    @Autowired
    PopulateCar populateCar;

    @Autowired
    PopulateDrivingCourse populateDrivingCourse;

    @Autowired
    PopulateClient populateClient;

    @Test
    void populateAllTables () {
        // let s populate cars first
        PopulateStatus populateCarStatus = populateCar.populateCar(10);
        System.out.println("Populate Car operations: " + populateCarStatus.getQty() +
                " \n" + populateCarStatus.getMessage());

        // let s populate clients
        populateClient.populateClient(10);

        // once cars are populated, let s populate bookings
        populateBooking.populateBooking(10);

        // once bookings are populated, let s populate driving courses
        populateDrivingCourse.populateDrivingCourse(10);
    }

}
