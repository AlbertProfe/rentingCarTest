package org.example;

import java.util.List;

public class CarManager {


    public static void printCarList(List<Car> cars) {


        System.out.println("Cars from DB: ");
        System.out.println("-------------------------");
        System.out.println("Size DB: " + cars.size());


        for (Car car : cars) {
            System.out.println("\t" + car);
        }

        System.out.println("\n");
    }
}
