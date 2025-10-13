package dev.app.rentingCar_boot;

import dev.app.rentingCar_boot.model.Booking;
import dev.app.rentingCar_boot.model.Car;
import dev.app.rentingCar_boot.model.Client;
import dev.app.rentingCar_boot.repository.BookingRepository;
import dev.app.rentingCar_boot.repository.CarRepository;
import dev.app.rentingCar_boot.repository.ClientRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Transactional
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
                "00018", "Nil", "Gasol",
                "Carrer de laitat", "nil.gasol@gmail.com",
                false, 25, "1234");

        clientRepository.save(client);

        Car car = carRepository.findById("7580").get();

        Booking myBooking = new Booking();
        myBooking.setId("B009");
        myBooking.setBookingDate(1760344181);
        myBooking.setQtyDays(5);
        myBooking.setTotalAmount(100.26);
        myBooking.setActive(true);

        myBooking.setClient(client);
        myBooking.setCar(car);

        bookingRepository.save(myBooking);

        System.out.println("Booking: " + myBooking);

        //Booking myTetBooking = bookingRepository.findById("B008").get();
        //System.out.println("Booking id: "  + myTetBooking.getId());
        //System.out.println("Car id: "  + myTetBooking.getCar().getBrand());

        System.out.println("Booking --from db--: " + bookingRepository.findById("B009").get());

        //bookingRepository.delete(myBooking);
        //System.out.println("Booking deleted: " + myBooking);



    }
}
