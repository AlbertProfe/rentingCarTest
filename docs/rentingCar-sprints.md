# rentingCar sprints

`version document: v2.7`

## Version Boot

### Week #03

| Version | Title                                 | Description                                               | Status   | Date  |
| ------- | ------------------------------------- | --------------------------------------------------------- | -------- | ----- |
| v2.0    | create project with repository and H2 | test create Car                                           | 86fa570d | 06/10 |
| v2.1    | JPA CarExtras 1:n                     | just the dradt, pending configure annotatoins             | eb3217d0 | 07/10 |
| v2.2.1  | JPA CarExtras 1:n                     | implement the draft and fake cars (uuid-4 digit for cars) | 8f4d22cb | 09/10 |
| V2.2    | CarService test CRUD                  | Implement CarService                                      | eb3217d0 | 07/10 |
| v2.3    | JPA InsuranceCia n:1                  | Implement class and test it                               | 12d87982 | 10/10 |
| v2.4.1  | populate and assign cars to carExtras | implement part 1/2                                        | 56966f3d | 10/10 |
| v2.4.2  | populate and assign cars to carExtras | implement part 2/2                                        | b8dd7b9d | 10/10 |

### Week #04

| Version | Title                                                 | Description                            | Status   | Date  |
| ------- | ----------------------------------------------------- | -------------------------------------- | -------- | ----- |
| v2.4.3  | th view Car related                                   | View with car and its related objects  | 62b5c184 | 13/10 |
| v2.5.1  | JPA relationships: n:m with @ManyToOne unidirectional | Booking entity with Car/Client         | 62b5c184 | 13/10 |
| v2.6    | Booking and Car bidirectional: List<Bookings>         | JPA relationships: n:m with @ManyToOne | b7a01b33 | 14/10 |
| v2.7    | @Entity Driving Course n:m                            | Implement with @joinTable              | bf2f0dc  | 14/10 |
| v2.7.1  | generateUUI for all entities                          |                                        |          | 16/10 |
| v2.7.2  | Client with List<String>                              | @ElementCollection                     |          | 17/10 |
| v2.7.3  | populate all tables                                   |                                        |          | 17/10 |

## Version Quickstart Maven

### Week #01

| Version          | Title                                        | Description                                      | Status  | Date |
| ---------------- | -------------------------------------------- | ------------------------------------------------ | ------- | ---- |
| v1.0             | Create project and test                      | Basic test to create cars: testCar();            | da0e3f1 | 22/9 |
| v1.1             | Test booking                                 | Basic test to create booking: testBooking();     | da0e3f1 | 22/9 |
| v1.2             | Fake cars and list cars                      | Create HashMap or List to store fake cars        | c7c49c8 | 23/9 |
| v1.3.0           | Data store                                   | Java Class with 3 Lists                          | 63a7190 | 25/9 |
| v.1.3.1 - v1.3.3 | Fake data populator & CarManager `printList` | Fake data populator to populate  **myDataStore** | e849d8a | 26/9 |

### Week #02

| Version    | Title                                   | Description                                        | Status   | Date      |
| ---------- | --------------------------------------- | -------------------------------------------------- | -------- | --------- |
| v1.4       | Main Loop & Scanner, `Dispatcher`       | Create main loop to select options                 | 1ecf4b2  | 30/9      |
| v1.4.1     | Refractor directories                   | Main Menu & Utilities                              | 3144476  | 30/9      |
| v1.5       | `BookingManager`, createBooking draft   | Harcoded Login client                              | 5c0eb83  | 02/10     |
| v1.5.1     | Implement createBooking                 | select car with fake logged client                 | 366d19d  | 02/10     |
| ~~v1.5.2~~ | ~~check createBooking~~                 | ~~check errors, bugs and add loop, are you sure?~~ |          | ~~03/10~~ |
| v1.6       | Implement Login                         | decoupled: loginView, validateLogin                | e470b2f8 | 03/10     |
| v1.6.1     | print Clients and Bookings              |                                                    | 1e68aa46 | 03/10     |
| v1.7       | check createBooking                     | check bugs and add loop, are you sure?             |          |           |
| v1.8       | CarManger and ClientManager improvement |                                                    |          |           |
| v1.9       | logout                                  |                                                    |          |           |

## Debt Tech & Efficiency v1

- v1.5.0: MainDispatcher too much coupled: view + bussines logic

- v1.5.0: MainDispatcher: elseif vs. Switch

- v1.5.0 add UUID: 
  
  - [UUID (Java Platform SE 8 )](https://docs.oracle.com/javase/8/docs/api/java/util/UUID.html)
  
  - [random - Create a GUID / UUID in Java - Stack Overflow](https://stackoverflow.com/questions/2982748/create-a-guid-uuid-in-java)
