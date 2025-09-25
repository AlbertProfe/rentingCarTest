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

        //RentingCarTests.createFakeCarListTest();

        DataStore myDataStore = new DataStore();

        Car myCar = new Car();
        myCar.setId("1");
        myCar.setBrand("BMW");
        myCar.setModel("M3");
        myCar.setPlate("A123DFGR4");
        myCar.setYear(2022);

        Car myCar2 = new Car();
        myCar2.setId("2");
        myCar2.setBrand("Mazda");
        myCar2.setModel("Mazda 3");
        myCar2.setPlate("B123DFGR4");
        myCar2.setYear(2021);

        myDataStore.getCars().add(myCar);
        myDataStore.getCars().add(myCar2);

        System.out.println("Cars from DB: ");
        System.out.println("-------------------------\n");
        System.out.println("Size DB: "  + myDataStore.getCars().size());
        System.out.println("Cars from DB: " + myDataStore.getCars().toString());


        myDataStore.getCars().get(0).setPlate("1111111");

        System.out.println("Cars from DB: ");
        System.out.println("-------------------------\n");
        System.out.println("Size DB: "  + myDataStore.getCars().size());
        System.out.println("Cars from DB: " + myDataStore.getCars().toString());

        System.out.println("Finished!");
    }





}
