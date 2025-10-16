package dev.app.rentingCar_boot.utils;

import dev.app.rentingCar_boot.model.DrivingCourse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class PopulateDrivingCourse {

    public void populateDrivingCourse(int qty) {
     // let s populate this table: Driving Course
     // generate qty driving courses using generateDrivingCourses method
     ArrayList<DrivingCourse> drivingCourses = generateDrivingCourses(qty);
     // assign clients to driving courses using assignClientsToDrivingCourses method
     assignClientsToDrivingCourses(drivingCourses);
    }

    public ArrayList<DrivingCourse> generateDrivingCourses(int qty) {
        // to do
        return null;
    }

    public void assignClientsToDrivingCourses(ArrayList<DrivingCourse> drivingCourses){
        // to do
    }
}
