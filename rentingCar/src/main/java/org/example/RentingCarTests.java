package org.example;

import com.github.javafaker.Faker;

import java.util.ArrayList;

public class RentingCarTests {

    public static void testCar() {

        System.out.println( "Testing car..." );
        int mateo = 2;
        System.out.println("This is mateo: " + mateo);

        Car bmw001 = new Car("1", "BMW", "M3", "1234", 2022, 100.0);
        System.out.println("This is my BMW: " + bmw001.toString());
        Car bmw002 = new Car("2", "BMW", "M3", "5678", 2021, 50.0);
        System.out.println("This is my Mazda: " + bmw002.toString());

        // let's print the brand of the first car
        System.out.println("My brand: "  + bmw001.getBrand());

        Car bmw003 = new Car();
        bmw003.setBrand("BMW");
        bmw003.setYear(2000);

        System.out.println("This is the age of the car: " + bmw003.carAge());

        // carAge() is a method of the Car class
        // therefore it needs to be called on an instance of the Car class
        //carAge();

        // The next line will throw an error because the constructor is not defined
        //Car bmw004 = new Car("3", "BMW");
    }

    public static void testBooking() {

        System.out.println("Testing booking...");

        Car bmw001 = new Car("1", "BMW", "M3", "1234", 2022, 100.0);
        Booking book001 = new Booking();
        book001.setId("1");
        book001.setCar(bmw001);
        book001.setDays(5);
        book001.setPrice(500.0);
        book001.setActive(true);

        System.out.println("This is my booking: " + book001.toString());



    }

    public static void TestFaker() {

        Faker faker = new Faker();

        String name = faker.cat().breed().toString();

        System.out.println(name);
    }

    public static void  testList() {
        ArrayList<Car> cars = new ArrayList<Car>();

        Car bmw001 = new Car("1", "BMW", "M3", "1234", 2022, 100.0);
        cars.add(bmw001);

        cars.add(new Car("2", "BMW", "M3", "1234", 2022, 100.0));
    }
}
