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

        //RentingCarTests.myDataStoreTest();

        DataStore myDataStore = new DataStore();
        myDataStore.setId("1");
        myDataStore.setLabel("Renting Card Fake DB V1.0");
        long epoch = System.currentTimeMillis()/1000;
        myDataStore.setCreationDate(epoch);
        myDataStore.setLastModification(epoch);
        myDataStore.setActive(true);

        FakeDataDBPopulator.populateDBByCars(myDataStore);

        System.out.println("Finished!");
    }





}
