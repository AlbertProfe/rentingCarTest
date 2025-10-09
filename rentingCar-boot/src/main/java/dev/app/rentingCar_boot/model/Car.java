package dev.app.rentingCar_boot.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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

    @OneToMany(mappedBy= "carFK" , cascade = CascadeType.ALL)
    private List<CarExtras> carExtras = new ArrayList<>();

    /**
     * Generates a 4-digit UUID for the car
     * @return A 4-digit string ID
     */
    private String generateFourDigitUuid() {
        Random random = new Random();
        int uuid = 1000 + random.nextInt(9000); // Generates number between 1000-9999
        return String.valueOf(uuid);
    }

    public Car() {
        this.id = generateFourDigitUuid();
    }

    public Car(String brand, String model, String plate, int year, double price) {
        this.id = generateFourDigitUuid();
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

    @Override
    public String toString() {
        return "Car{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", plate='" + plate + '\'' +
                ", year=" + year +
                ", price=" + price +
                '}';
    }
}
