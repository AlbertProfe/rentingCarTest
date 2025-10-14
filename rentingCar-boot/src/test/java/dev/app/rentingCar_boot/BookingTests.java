package dev.app.rentingCar_boot;

import dev.app.rentingCar_boot.model.Booking;
import dev.app.rentingCar_boot.model.Car;
import dev.app.rentingCar_boot.model.Client;
import dev.app.rentingCar_boot.repository.BookingRepository;
import dev.app.rentingCar_boot.repository.CarRepository;
import dev.app.rentingCar_boot.repository.ClientRepository;
import dev.app.rentingCar_boot.utils.PopulateBooking;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@Transactional
@SpringBootTest
public class BookingTests {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CarRepository carRepository;

    @Autowired
    PopulateBooking populateBooking;

    @Test
    void bookingTest(){

        Client client = new Client(
                "00024", "Nil", "Gasol",
                "Carrer de laitat", "nil.gasol@gmail.com",
                false, 25, "1234");

        clientRepository.save(client);
        System.out.println("Client --from DB--: " + clientRepository.findById("00024").get());

        Car car = carRepository.findById("5225").get();

        Booking myBooking = new Booking();
        myBooking.setId("B019");
        myBooking.setBookingDate(1760344181);
        myBooking.setQtyDays(5);
        myBooking.setTotalAmount(100.26);
        myBooking.setActive(true);

        myBooking.setClient(client);
        myBooking.setCar(car);

        bookingRepository.save(myBooking);

        System.out.println("Booking: " + myBooking);

        System.out.println("Booking --from db--: " + bookingRepository.findById("B019").get());
        System.out.println("Car --from db--: " + carRepository.findById("5225").get());

    }

    @Test
    void populateBookingTest(){
        populateBooking.populateBooking(10);
    }
}
