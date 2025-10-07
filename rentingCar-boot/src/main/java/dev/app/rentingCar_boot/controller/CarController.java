package dev.app.rentingCar_boot.controller;


import dev.app.rentingCar_boot.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CarController {

    @Autowired
    CarRepository carRepository;

    @GetMapping("/cars")
    public String listCars(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        System.out.println("Cars (controller/cars): " + carRepository.findAll());
        return "cars";
    }

    @GetMapping("/cars-nocss")
    public String listCarsNoCss(Model model) {
        model.addAttribute("cars", carRepository.findAll());
        System.out.println("Cars (controller/cars-nocss): " + carRepository.findAll());
        return "cars-nocss";
    }
}
