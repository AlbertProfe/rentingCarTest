# rentingCar v2

`version document: v2.0`

## Goal & Summary

> Rent a car by CLI with client, car, init and ending date, price with Spring Boot

- Reference project: [Spring Boot: H2 DB and Thymeleaf – albertprofe wiki](https://albertprofe.dev/springboot/boot-what-create-th-h2.html)

## Version

- 

## Debt Tech & Efficiency

- 

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

#### CLASS DataStore

This is a draft, we wil not use `final` so far, and `static` will be reserved just for the **Lists**.

```java
public class DataStore {

    private String id;
    private List<Car> cars;
    private static List<Client> clients;
    private static List <Booking> bookings
    private boolean isActive;
    private int creationDate;
    private int lastModification;

    // constructor, geters, setters, methods and toString
}
```

## Syntetic data & fake objects

- [GitHub - DiUS/java-faker: Brings the popular ruby faker gem to Java](https://github.com/DiUS/java-faker)

> This library is a port of Ruby's [faker](https://github.com/stympy/faker) gem (as well as Perl's Data::Faker library) that generates fake data. It's useful when you're developing a new project and need some pretty data for showcase.

Usage

In pom.xml, add the following xml stanza between `<dependencies> ... </dependencies>`

```xml
<dependency>
    <groupId>com.github.javafaker</groupId>
    <artifactId>javafaker</artifactId>
    <version>1.0.2</version>
</dependency>
```

Code example:

```java
Faker faker = new Faker();

String name = faker.name().fullName(); // Miss Samanta Schmidt
String firstName = faker.name().firstName(); // Emory
String lastName = faker.name().lastName(); // Barton

String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
```

## UML Renting Car

```mermaid
classDiagram
    class App {

    } 

    class Car{
      - String id
      - String brand
      - String model
      - String plate
      - int year
      - double price
    }
    class Client{
      - String id
      - String name
      - String lastName
      - String address
      - String email
      - bool premium
      - int age
    }
    class Booking{
      - bool isActive
      - LocalDate bookingDate
      - int days
      - Car car
      - Client client
      - double price
      - bool isActive  

    }

    class FakeDataDBPopulator {
    }

    class CarManager {

     - printCars()

    }

    class DataStore {

    - String id;
    - List<Car> cars;
    - List<Client> clients;
    - List <Booking> bookings
    - bool isActive;
    - int creationDate;
    - int lastModification;


     }


    App --> FakeDataDBPopulator
    DataStore ..o App
    DataStore --* Car
    DataStore --* Booking
    DataStore --* Client
    Car --* Booking
    Client --* Booking
```



## Tech Stack

- IDE: IntelliJ IDEA 2025.1.3 (Community Edition)
  
  - [Descargar IntelliJ IDEA](https://www.jetbrains.com/es-es/idea/download/?section=linux)
  
  - With [Installing snap on Ubuntu | Snapcraft documentation](https://snapcraft.io/docs/installing-snap-on-ubuntu): `sudo snap install intellij-idea-community --classic`

- Java 21 (or 25, 17, 11, 8)

- JUniit 3.8.1

- Maven Project from https://start.spring.io/
  
  - Dependencies: Spring Web, H2, DevTools, Thymeleaf

## POM.XML

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.example</groupId>
  <artifactId>rentingCarTest</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>jar</packaging>

  <name>rentingCarTest</name>
  <url>http://maven.apache.org</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencies>
    <!-- JUnit: tool to test  -->
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>3.8.1</version>
      <scope>test</scope>
    </dependency>

    <!-- Java Faker: fake data creation -->
    <dependency>
      <groupId>com.github.javafaker</groupId>
      <artifactId>javafaker</artifactId>
      <version>1.0.2</version>
    </dependency>

  </dependencies>
</project>
```
