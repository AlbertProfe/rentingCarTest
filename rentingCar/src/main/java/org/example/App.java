package org.example;

import org.example.dataStore.DataStore;
import org.example.controller.MainDispatcher;
import org.example.managers.ClientManger;
import org.example.model.Client;
import org.example.utilities.FakeDataDBPopulator;

public class App
{
    public static void main( String[] args )
    {
        System.out.println("Starting code...");

        DataStore myDataStore = new DataStore();
        myDataStore.setId("1");
        myDataStore.setLabel("Renting Card Fake DB V1.0");
        long epoch = System.currentTimeMillis()/1000;
        myDataStore.setCreationDate(epoch);
        myDataStore.setLastModification(epoch);
        myDataStore.setActive(true);

        // What? populate DB with fake data
        // How? with a static method we add fake data to the list of cars
        // Why? For What? we need data to init the app
        FakeDataDBPopulator.populateDBByCars(myDataStore);

        FakeDataDBPopulator.populateDBByClients(myDataStore);

        //CarManager.printCarList(myDataStore.getCars());
        Client loggedClient;
        loggedClient = ClientManger.loginClient();
        myDataStore.setLoggedClient(loggedClient);

        MainDispatcher.runner(myDataStore);


        System.out.println("Finished!");
    }








}
