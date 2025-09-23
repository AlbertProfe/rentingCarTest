package org.example;

import com.github.javafaker.Faker;
import java.util.ArrayList;

public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Starting code...");

        // this is a static method
        // it can be called without creating an instance of the class

        //RentingCarTests.testCar();
        //RentingCarTests.testBooking();

        //RentingCarTests.testList();
        //RentingCarTests.TestFaker();

        CreateFakeCarList();

        System.out.println("Finished!");
    }

    public static void CreateFakeCarList() {

        // create a list of cars

        // let s create a for to run from 0 to 100

                    // let s crate a faker object
                    // let s create a car object
                    // Car mycar = new Car();
                     // faker ... create a fake id
                    // faker. .... create a frake brand
                    // faker  .... create a fake year

                     // set car with fake data

                    // add mycar to the list
                    ///  cars.add(mycar);

        // finish loop


        ArrayList<Car> cars = new ArrayList<>();
        Faker faker = new Faker();

        for (int i = 0; i < 100; i++) {
            // create a car object
            Car myCar = new Car();
            // set fake data
            myCar.setId( String.valueOf (  faker.number().randomNumber()));
            myCar.setBrand(faker.company().name());
            myCar.setYear(faker.number().numberBetween(1980, 2024));
            // add mycar to the list
            cars.add(myCar);
        }

        System.out.println("\n");
        System.out.println("This is my list of cars: ");
        System.out.println("-------------------------");
        System.out.println("The list has " + cars.size() + " cars");
        System.out.println("\n");
        
        // Print each car on a separate line
        for (Car car : cars) {
            System.out.println("\t" + car);
        }
        System.out.println("\n");
    }



}
