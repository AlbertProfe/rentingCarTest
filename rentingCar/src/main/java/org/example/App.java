package org.example;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        createAndPrintEpoch();



        System.out.println("Finished!");
    }


    public static void createAndPrintEpoch() {

        long epoch = System.currentTimeMillis()/1000;

        System.out.println("Epoch 1: " + epoch);

        String date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date(epoch * 1000L));
        System.out.println("Creation Date epoch1 to date: " + date);

        try {
            long epoch2 = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").parse("01/02/1972 01:00:00").getTime() / 1000L;
            System.out.println("Epoch 2: " + epoch2);
        } catch (java.text.ParseException e) {
            System.err.println("Error parsing date: " + e.getMessage());
            e.printStackTrace();
        }
    }





}
