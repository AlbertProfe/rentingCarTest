package dev.app.rentingCar_boot.service;

import dev.app.rentingCar_boot.model.Car;
import dev.app.rentingCar_boot.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    // let s implement all CRUD operations

    @Autowired
    CarRepository carRepository;

    public Iterable<Car> findAll() {

        Iterable<Car> foundCard =  new ArrayList<>();

        foundCard = carRepository.findAll();

        return foundCard;
    }

    public Optional<Car> findCarById (String id) {

        Optional<Car> foundCar = carRepository.findById(id);

        if (foundCar.isEmpty()) System.out.println("Car not found");
        else System.out.println("Car found: " + foundCar.get());


        return foundCar;
    }

    public void deleteCarById (String id) {

        carRepository.deleteById(id);

        Optional<Car> foundCar = carRepository.findById(id);

        if (foundCar.isEmpty()) System.out.println("Car not found: we can not delete the car");
        else System.out.println("Car found and deleted: " + foundCar);

    }

    public void deleteAllCard (){

        carRepository.deleteAll();
    }

    public void updateCar (Car car){

        carRepository.save(car);
    }

    public boolean checkAvailability(Car car, int bookingDate, int qtyDays) {
        HashMap<Integer, Boolean> availableDates = car.getAvailableDates();

        // Check each day in the requested booking period
        for (int i = 0; i < qtyDays; i++) {
            int currentDate = bookingDate + i;

            // If date exists in HashMap and is false (unavailable), return false
            if (availableDates.containsKey(currentDate) && !availableDates.get(currentDate)) {
                return false; // Car is not available on this date
            }
        }

        return true; // All dates are available
    }


}
