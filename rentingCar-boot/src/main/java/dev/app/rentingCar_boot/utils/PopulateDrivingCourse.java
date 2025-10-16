package dev.app.rentingCar_boot.utils;

import dev.app.rentingCar_boot.model.DrivingCourse;
import dev.app.rentingCar_boot.repository.DrivingCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class PopulateDrivingCourse {

    @Autowired
    private DrivingCourseRepository drivingCourseRepository;

    public void populateDrivingCourse(int qty) {
     // let s populate this table: Driving Course
     // generate qty driving courses using generateDrivingCourses method
     ArrayList<DrivingCourse> drivingCourses = generateDrivingCourses(qty);
     // assign clients to driving courses using assignClientsToDrivingCourses method
     assignClientsToDrivingCourses(drivingCourses);
    }

    public ArrayList<DrivingCourse> generateDrivingCourses(int qty) {
        ArrayList<DrivingCourse> generatedDrivingCourses = new ArrayList<>();
        Random random = new Random();

        String[] courseNames = {"Basic Driving", "Advanced Driving", "Defensive Driving", "Highway Driving", 
                               "City Driving", "Night Driving", "Winter Driving", "Commercial Driving", 
                               "Motorcycle Basics", "Parallel Parking Mastery"};
        
        String[] descriptions = {
            "Learn the fundamentals of safe driving with experienced instructors",
            "Advanced techniques for confident and skilled driving",
            "Defensive driving strategies to avoid accidents and hazards",
            "Master highway driving with proper merging and lane changing",
            "Navigate city traffic with confidence and precision",
            "Safe night driving techniques and visibility management",
            "Winter driving skills for challenging weather conditions",
            "Professional commercial vehicle operation training",
            "Complete motorcycle riding course for beginners",
            "Perfect your parallel parking and tight space maneuvering"
        };

        String[] instructors = {"John Smith", "Sarah Johnson", "Mike Davis", "Lisa Brown", "David Wilson", 
                               "Emma Garcia", "James Miller", "Maria Rodriguez", "Robert Taylor", "Jennifer Lee"};

        String[] categories = {"Basic", "Advanced", "Defensive", "Commercial", "Specialized"};

        String[] locations = {"Downtown Center", "North Campus", "South Branch", "East Side", "West End", 
                             "City Center", "Suburban Branch", "Highway Training Ground", "Practice Lot A", "Practice Lot B"};

        for (int i = 0; i < qty; i++) {
            DrivingCourse drivingCourse = new DrivingCourse();
            
            String courseName = courseNames[random.nextInt(courseNames.length)];
            String description = descriptions[random.nextInt(descriptions.length)];
            String instructor = instructors[random.nextInt(instructors.length)];
            int durationHours = 10 + random.nextInt(41); // Between 10-50 hours
            double price = 100.0 + (random.nextDouble() * 400.0); // Between 100-500
            String category = categories[random.nextInt(categories.length)];
            int maxStudents = 5 + random.nextInt(16); // Between 5-20 students
            boolean isActive = random.nextBoolean();
            String location = locations[random.nextInt(locations.length)];

            drivingCourse.setCourseName(courseName);
            drivingCourse.setDescription(description);
            drivingCourse.setInstructor(instructor);
            drivingCourse.setDurationHours(durationHours);
            drivingCourse.setPrice(price);
            drivingCourse.setCategory(category);
            drivingCourse.setMaxStudents(maxStudents);
            drivingCourse.setActive(isActive);
            drivingCourse.setLocation(location);

            generatedDrivingCourses.add(drivingCourse);
            drivingCourseRepository.save(drivingCourse);
        }

        return generatedDrivingCourses;
    }

    public void assignClientsToDrivingCourses(ArrayList<DrivingCourse> drivingCourses){
        // to do
    }
}
