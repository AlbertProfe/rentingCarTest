package dev.app.rentingCar_boot;

import dev.app.rentingCar_boot.model.Car;
import dev.app.rentingCar_boot.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RentingCarBootApplicationTests {

    @Autowired
    CarRepository carRepository;

	@Test
	void contextLoads() {
	}


    @Test
    void testCarRepository() {

        //Car car = new Car("1", "Toyota", "Corolla", "123456", 2022, 100.0);
        //carRepository.save(car);

        Car car2 = new Car("3", "Toyota", "Corolla", "123456", 2022, 100.0);
        carRepository.save(car2);

    }

}
