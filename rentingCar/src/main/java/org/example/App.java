package org.example;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        int mateo = 2;
        System.out.println("This is mateo: " + mateo);

        Car bmw001 = new Car("1", "BMW", "M3", "1234", 2022, 100.0);
        System.out.println("This is my BMW: " + bmw001.toString());
        Car bmw002 = new Car("2", "BMW", "M3", "5678", 2021, 50.0);
        System.out.println("This is my Mazda: " + bmw002.toString());

        // let's print the brand of the first car
        System.out.println("My brand: "  + bmw001.getBrand());

        Car bmw003 = new Car();
        bmw003.setBrand("BMW");

        // The next line will throw an error because the constructor is not defined
        //Car bmw004 = new Car("3", "BMW");
    }
}
