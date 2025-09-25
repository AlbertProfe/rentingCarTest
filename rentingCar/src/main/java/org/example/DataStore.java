package org.example;

import java.util.ArrayList;
import java.util.List;

public class DataStore {

        private  String id;
        private  static List<Car> cars = new ArrayList<>();
        private  static List<Client> clients = new ArrayList<>();
        private  static List <Booking> bookings = new ArrayList<>();
        private   boolean isActive;
        private   int creationDate;
        private   int lastModification;

    public DataStore() {
    }

    public DataStore (String id, List<Car> cars, List<Client> clients, List<Booking> bookings,  int creationDate, int lastModification ){

        this.id = id;
        this.cars = cars;
        this.clients = clients;
        this.bookings = bookings;
        this.isActive = true;
        this.creationDate = creationDate;
        this.lastModification = lastModification;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public  List<Car> getCars() {
        return cars;
    }


    public  List<Client> getClients() {
        return clients;
    }



    public  List<Booking> getBookings() {
        return bookings;
    }



    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    public int getLastModification() {
        return lastModification;
    }

    public void setLastModification(int lastModification) {
        this.lastModification = lastModification;
    }
}
