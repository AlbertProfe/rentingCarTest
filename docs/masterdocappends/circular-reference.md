# Circular Reference in Car <> CarExtras

## Issue

A **circular reference** occurs when two objects reference each other, creating an endless loop during serialization.

In our example:

- [Car](cci:2://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/model/Car.java:15:0-276:1) contains a list of [CarExtras](cci:2://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/model/CarExtras.java:6:0-102:1)
- Each [CarExtras](cci:2://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/model/CarExtras.java:6:0-102:1) has a `carFK` field pointing back to its parent [Car](cci:2://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/model/Car.java:15:0-276:1)

When <mark>Jackson</mark> tries to convert this to JSON:

1. It serializes [Car](cci:2://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/model/Car.java:15:0-276:1) → includes `carExtras` list
2. It serializes each [CarExtras](cci:2://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/model/CarExtras.java:6:0-102:1) → includes `carFK` (the parent Car)
3. It serializes that [Car](cci:2://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/model/Car.java:15:0-276:1) again → includes `carExtras` again
4. This repeats infinitely: Car → CarExtras → Car → CarExtras...

The result is <mark>massive JSON output that never ends</mark>, causing memory issues (sometims **stackoverflow**) and unreadable responses. The `@JsonManagedReference`/`@JsonBackReference` annotations break this cycle by preventing the back-reference from being serialized.

> **Jackson** is a popular Java library that converts Java objects to JSON format and vice versa. It's commonly used in Spring Boot applications to automatically serialize your entity objects (like [Car](cci:2://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/model/Car.java:15:0-276:1), [CarExtras](cci:2://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/model/CarExtras.java:6:0-102:1)) into JSON responses for REST APIs. When you return objects from controllers, Jackson handles the JSON conversion behind the scenes.

## Approach

The best approach is to use `@JsonBackReference` and `@JsonManagedReference` annotations to break the circular reference. We need to add the corresponding `@JsonBackReference` annotation to the [CarExtras](cci:2://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/model/CarExtras.java:5:0-101:1) entity.

We fix the circular reference issue by adding Jackson annotations:

- **`@JsonManagedReference`** on `Car.carExtras` - This marks the "forward" part of the reference (the parent side)
- **`@JsonBackReference`** on `CarExtras.carFK` - This marks the "back" part of the reference (the child side) and prevents it from being serialized

### How it works:

1. When Jackson serializes a [Car](cci:2://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/model/Car.java:15:0-277:1), it will include the `carExtras` list (due to `@JsonManagedReference`)
2. When serializing each [CarExtras](cci:2://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/model/CarExtras.java:6:0-103:1) in that list, it will **skip** the `carFK` field (due to `@JsonBackReference`)
3. This breaks the circular reference: Car → CarExtras (but CarExtras doesn't serialize back to Car)

Now our `/api/cars` endpoint should return proper JSON without the infinite loop. The [CarExtras](cci:2://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/model/CarExtras.java:6:0-103:1) objects will still be included in the response, but they won't contain the back-reference to their parent [Car](cci:2://file:///home/albert/MyProjects/Sandbox/rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/model/Car.java:15:0-277:1), preventing the circular dependency.

We now can test the endpoint again and we should see clean JSON output with car extras included but no circular references.
