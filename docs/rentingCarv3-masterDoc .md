# rentingCar v3

`version document: v3.1`

## Goal & Summary

> Rent a car by CLI with client, car, init and ending date, price with Spring Boot
> 
> New Feature: define data-model for **availability by car and dates range.**

- Reference project: [Spring Boot: H2 DB and Thymeleaf – albertprofe wiki](https://albertprofe.dev/springboot/boot-what-create-th-h2.html)
- Microservices: https://spring.io/
- Spring Boot is open-source: [GitHub - spring-projects/spring-boot: Spring Boot helps you to create Spring-powered, production-grade applications and services with absolute minimum fuss.](https://github.com/spring-projects/spring-boot)
- Spring Boot Guides / Academy: https://spring.io/guides / https://spring.academy/courses
- Quickstart: https://spring.io/quickstart

## Version

- [rentingCarTest/docs/rentingCar-sprints.md at master · AlbertProfe/rentingCarTest · GitHub](https://github.com/AlbertProfe/rentingCarTest/blob/master/docs/masterdocappends/rentingCar-sprints.md)

## Tree

```
[Mon Oct 20 10:30:53] albert@albert-VirtualBox:~/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src (master)
$ tree
.
├── main
│   ├── java
│   │   └── dev
│   │       └── app
│   │           └── rentingCar_boot
│   │               ├── controller
│   │               │   ├── CarController.java
│   │               │   └── CarRestController.java
│   │               ├── model
│   │               │   ├── Booking.java
│   │               │   ├── CarExtras.java
│   │               │   ├── Car.java
│   │               │   ├── Client.java
│   │               │   ├── DrivingCourse.java
│   │               │   └── InssuranceCia.java
│   │               ├── RentingCarBootApplication.java
│   │               ├── repository
│   │               │   ├── BookingRepository.java
│   │               │   ├── CarExtrasRepository.java
│   │               │   ├── CarRepository.java
│   │               │   ├── ClientRepository.java
│   │               │   ├── DrivingCourseRepository.java
│   │               │   └── InssuranceCiaRepository.java
│   │               ├── service
│   │               │   └── CarService.java
│   │               └── utils
│   │                   ├── GenerateUUID.java
│   │                   ├── PopulateAllTables.java
│   │                   ├── PopulateBooking.java
│   │                   ├── PopulateCar.java
│   │                   ├── PopulateClient.java
│   │                   ├── PopulateDrivingCourse.java
│   │                   └── PopulateStatus.java
│   └── resources
│       ├── application.properties
│       ├── static
│       └── templates
│           ├── cars.html
│           ├── cars-nocss-data.html
│           ├── cars-nocss.html
│           └── cars-relation.html
└── test
    └── java
        └── dev
            └── app
                └── rentingCar_boot
                    ├── BookingTests.java
                    ├── CarTests.java
                    ├── ClientTests.java
                    ├── DrivingCourseTests.java
                    └── PopulateTests.java

19 directories, 33 files
```

## Car Booking by Date Range Feature

### Availability by car/dates range

> A system functionality that allows users to reserve vehicles for specific time periods **by checking car availability against existing bookings.**
> 
> The feature validates date conflicts, prevents double-booking, and ensures seamless rental scheduling through real-time availability queries across the booking database for optimal resource management.

Analysis of Four Approaches for Car Availability (SQL/NoSQL): 

- [rentingCarTest/docs/../4-approaches-availability.md at master · AlbertProfe/rentingCarTest · GitHub](https://github.com/AlbertProfe/rentingCarTest/blob/master/docs/masterdocappends/4-approaches-availability.md)

#### Approach 2: HashMap of availableDates for year 2026

- [rentingCarTest/docs/masterdocappends/4-approaches-availability.md at master · AlbertProfe/rentingCarTest · GitHub](https://github.com/AlbertProfe/rentingCarTest/blob/master/docs/masterdocappends/4-approaches-availability.md#approach-2-car-has-hashmap-of-availabledates-for-year-2026)

- [rentingCarTest/docs/masterdocappends/FakeAvailableDatesHashMap.md at master · AlbertProfe/rentingCarTest · GitHub](https://github.com/AlbertProfe/rentingCarTest/blob/master/docs/masterdocappends/FakeAvailableDatesHashMap.md)

## HashMap

References

- [Java HashMap](https://www.w3schools.com/java/java_hashmap.asp)
- [HashMap (Java Platform SE 8 )](https://docs.oracle.com/javase/8/docs/api/java/util/HashMap.html)
- [Java HashMap (With Examples)](https://www.programiz.com/java-programming/hashmap)
- [What is a Java Hashmap?](https://www.freecodecamp.org/news/what-is-a-java-hashmap/)   

Definition

> In Java, you use a HashMap to store items in **key/value pairs**. You can access items stored in a `HashMap` using the item's key, which is unique for each item.

### What Are the Features of a HashMap in Java?

Before working with HashMaps, it is important to understand how they work.

Here are some of the features of a `HashMap`:

- Items are stored in **key/value pairs.**
- Items do not maintain **any order when added.** The data is unordered.
- In a case where there are duplicate keys, the last one will override the other(s).
- Data types are specified using **wrapper classes instead of primitive data types.**

### How to Create a HashMap in Java

In order to create and use a HashMap, you must first import the `java.util.HashMap` package. That is:

```java
import java.util.HashMap;
```

Here's what the syntax looks like for creating a new `HashMap`:

```java
HashMap<KeyDataType, ValueDataType> HashMapName = new HashMap<>();
```

## JPA Annotations for HashMap Persistence**

Add these annotations above the `availableDates` field in your [Car.java](cci:7://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/model/Car.java:0:0-0:0):

```java
@ElementCollection(fetch = FetchType.LAZY)
@CollectionTable(name = "car_available_dates", joinColumns = @JoinColumn(name = "car_id"))
@MapKeyColumn(name = "date_key")
@Column(name = "is_available")
private HashMap<Integer, Boolean> availableDates = new HashMap<>();
```

### **What each annotation does:**

- **`@ElementCollection`**: Tells JPA this is a collection of basic types (not entities)
- **`@CollectionTable`**: Creates a separate table `car_available_dates` to store the HashMap
- **`@MapKeyColumn`**: Maps the HashMap keys (Integer timestamps) to column `date_key`
- **`@Column`**: Maps the HashMap values (Boolean) to column `is_available`
- **`joinColumns = @JoinColumn(name = "car_id")`**: Foreign key linking back to the Car entity

Resulting H2 Table Structure:

```sql
CREATE TABLE car_available_dates (
    car_id VARCHAR(255) NOT NULL,
    date_key INTEGER NOT NULL,
    is_available BOOLEAN,
    PRIMARY KEY (car_id, date_key),
    FOREIGN KEY (car_id) REFERENCES car(id)
);
```

## UML Data Model

#### CLASS Car

```java
ackage org.example;

public class Car {
    private String id;
    private String brand;
    private String model;
    private String plate;
    private int year;
    private double price;

    // constructor, geters, setters, methods and toString

    private int carAge ()
}
```

#### CLASS Client & MinimalClient

```java
public class Client {

    private String id;
    private String name;
    private String lastName;
    private String address;
    private String email;
    private boolean premium;
    private int age;
    private String password;

    // constructor, geters, setters, methods and toString
}


public class MinimalClient {

    private String email;
    private String password;

    public MinimalClient() {
    }

    // constructor, geters, setters, methods and toString
}
```

#### CLASS Booking

```java
public class Booking {

    private String id;
    //private Client client;
    private Car car;
    private int days;
    private double price;
    private boolean isActive;
    // private LocalDate bookingDate

    // constructor, geters, setters, methods and toString
}
```

## H2 & application.properties

> Welcome to H2, the Java SQL database. The main features of H2 are:
> 
> - Very fast, open source, JDBC API
> - Embedded and server modes; in-memory databases
> - Browser based Console application
> - Small footprint: around 2.5 MB jar file size

- Official web: https://h2database.com/html/installation.html
- Create H2 db from CLI: [Lab#SB08-3: H2 and API Rest – albertprofe wiki](https://albertprofe.dev/springboot/sblab8-3.html#h2-db)
- Step-by-step: [Spring Boot: H2 DB and Thymeleaf – albertprofe wiki](https://albertprofe.dev/springboot/boot-what-create-th-h2.html)
- DDL: [Spring Boot: H2 DB and Thymeleaf – albertprofe wiki](https://albertprofe.dev/springboot/boot-what-create-th-h2.html)

Config `applcations properties` 

```properties
spring.application.name=rentingCar-boot


#spring.datasource.url=jdbc:h2:tcp://localhost/~/MyProjects/Sandbox/rentingCarTest/dataBase/rentingCar.db
spring.datasource.url=jdbc:h2:/home/albert/MyProjects/Sandbox/rentingCarTest/dataBase/rentingCar
#spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=albert
#spring.datasource.username=sa
spring.datasource.password=1234
#spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
#spring.jpa.hibernate.ddl-auto=create
spring.jpa.hibernate.ddl-auto=update
```

This [application.properties](cci:7://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/resources/application.properties:0:0-0:0) file configures a Spring Boot application for car rental management with H2 database integration.

Application Identity

- **`spring.application.name=rentingCar-boot`** - Sets the application name used for identification in logs, monitoring tools, and service discovery. This appears in Spring Boot banners and helps distinguish this app from others.

Database Configuration

- **H2 Database Setup** - Uses H2 as an embedded/file-based database
- **Active URL**: `jdbc:h2:/home/albert/MyProjects/Sandbox/rentingCarTest/dataBase/rentingCar` - Points to a persistent file-based H2 database stored locally
- **Commented alternatives**:
  - TCP server mode: `jdbc:h2:tcp://localhost/...` (for remote access)
  - In-memory mode: `jdbc:h2:mem:testdb` (data lost on restart)

Authentication

- **Username**: `albert` (custom user, `sa` is H2's default admin)
- **Password**: `1234` (simple password for development)

JPA/Hibernate Settings

- **`spring.jpa.database-platform=org.hibernate.dialect.H2Dialect`** - Tells Hibernate to use H2-specific SQL syntax
- **`spring.jpa.show-sql=true`** - Enables SQL query logging for debugging
- **`spring.jpa.hibernate.ddl-auto=update`** - Automatically updates database schema without dropping existing data (safer than `create` which recreates tables)

## Tech Stack

- IDE: IntelliJ IDEA 2025.1.3 (Community Edition)
  
  - [Descargar IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/download/?section=linux)
  
  - With [Installing snap on Ubuntu | Snapcraft documentation](https://snapcraft.io/docs/installing-snap-on-ubuntu): `sudo snap install intellij-idea-community --classic`

- Java 21 (or 25, 17, 11, 8)

- JUniit 3.8.1

- Maven Project from https://start.spring.io/
  
  - Dependencies: Spring Web, H2, DevTools, Thymeleaf, JPA

## POM.XML

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>3.5.6</version>
        <relativePath/> <!-- lookup parent from repository -->
    </parent>
    <groupId>dev.app</groupId>
    <artifactId>rentingCar-boot</artifactId>
    <version>0.0.1-SNAPSHOT</version>
    <name>rentingCar-boot</name>
    <description>Demo project for Spring Boot for rent a car</description>
    <url/>
    <licenses>
        <license/>
    </licenses>
    <developers>
        <developer/>
    </developers>
    <scm>
        <connection/>
        <developerConnection/>
        <tag/>
        <url/>
    </scm>
    <properties>
        <java.version>21</java.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-thymeleaf</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <scope>runtime</scope>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <optional>true</optional>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <annotationProcessorPaths>
                        <path>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </path>
                    </annotationProcessorPaths>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
                <configuration>
                    <excludes>
                        <exclude>
                            <groupId>org.projectlombok</groupId>
                            <artifactId>lombok</artifactId>
                        </exclude>
                    </excludes>
                </configuration>
            </plugin>
        </plugins>
    </build>

</project>
```
