package dev.app.rentingCar_boot;

import dev.app.rentingCar_boot.model.Booking;
import dev.app.rentingCar_boot.model.Car;
import dev.app.rentingCar_boot.model.Client;
import dev.app.rentingCar_boot.repository.BookingRepository;
import dev.app.rentingCar_boot.repository.CarRepository;
import dev.app.rentingCar_boot.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookingTests {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CarRepository carRepository;

    @Test
    void bookingTest(){

        Client client = new Client(
                "00013", "Nil", "Gasol",
                "Carrer de laitat", "nil.gasol@gmail.com",
                false, 25, "1234");

        clientRepository.save(client);

        Car car = carRepository.findById("7580").get();

        Booking myBooking = new Booking();
        myBooking.setId("B004");
        myBooking.setBookingDate(1760344181);
        myBooking.setQtyDays(5);
        myBooking.setTotalAmount(100.26);
        myBooking.setActive(true);

        myBooking.setClient(client);
        myBooking.setCar(car);

        bookingRepository.save(myBooking);

        System.out.println("Booking: " + myBooking);

        bookingRepository.findById("B004").get();
        System.out.println("Booking --from db--: " + bookingRepository.findById("B001").get());

        //bookingRepository.delete(myBooking);
        //System.out.println("Booking deleted: " + myBooking);



    }
}
