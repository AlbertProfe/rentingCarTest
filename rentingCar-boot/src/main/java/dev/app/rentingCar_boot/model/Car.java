package dev.app.rentingCar_boot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.app.rentingCar_boot.utils.GenerateUUID;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Entity
public class Car {

    @Id
    private String id;
    private String brand;
    private String model;
    private String plate;
    @Column(name = "car_year")
    private int year;
    private double price;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "inssurance_cia_id")
    private InssuranceCia inssuranceCia;

    @OneToMany(mappedBy= "carFK" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CarExtras> carExtras = new ArrayList<>();

    //@ElementCollection(fetch = FetchType.LAZY)
    @JsonIgnore
    @OneToMany(mappedBy= "car" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Booking> bookings = new ArrayList<>();


    private HashMap<Integer, Boolean> availableDates = new HashMap<>();

    public Car() {
        this.id = GenerateUUID.generateFourDigitUuid();
    }

    public Car(String brand, String model, String plate, int year, double price) {
        this.id = GenerateUUID.generateFourDigitUuid();
        this.brand = brand;
        this.model = model;
        this.plate = plate;
        this.year = year;
        this.price = price;
    }

    public Car(String id) {
        this.id = id;
        this.carExtras = new ArrayList<>();
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int carAge() {
        return 2025 - year;
    }

    public List<CarExtras> getCarExtras() {
        return carExtras;
    }

    public void setCarExtras(List<CarExtras> carExtras) {
        this.carExtras = carExtras;
    }

    public InssuranceCia getInssuranceCia() {
        return inssuranceCia;
    }

    public void setInssuranceCia(InssuranceCia inssuranceCia) {
        this.inssuranceCia = inssuranceCia;
    }


    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", plate='" + plate + '\'' +
                ", year=" + year +
                ", price=" + price +
                ", carAge=" + carAge() +
                ", inssuranceCia=" + (inssuranceCia != null ? inssuranceCia.getName() : "null") +
                ", carExtras=" + carExtras.size() + " extras" +
                ", bookings=" + bookings.size() + " bookings [" +
                String.join(", ", bookings.stream().map(Booking::getId).toList()) + "]" +
                '}';
    }


    public HashMap<Integer, Boolean> getAvailableDates() {
        return availableDates;
    }

    public void setAvailableDates(HashMap<Integer, Boolean> availableDates) {
        this.availableDates = availableDates;
    }
}
