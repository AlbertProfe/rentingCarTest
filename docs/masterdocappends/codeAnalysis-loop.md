## Code Analysis: Loop & while

## **Comparison: `availableClients` vs `contains()` Approach**

| **Aspect**                      | **availableClients Approach**                        | **contains() with if Check**                         |
| ------------------------------- | ---------------------------------------------------- | ---------------------------------------------------- |
| **Algorithm**                   | Create copy → Select random index → Remove selected  | Select random → Check if exists → Add if unique      |
| **Time Complexity**             | **O(n)** - Linear with number of clients to select   | **O(n²)** - Quadratic due to repeated contains()     |
| **Space Complexity**            | **O(n)** - Extra array copy of all clients           | **O(k)** - Only stores selected clients              |
| **Duplicate Prevention**        | **Guaranteed** - Removed clients can't be reselected | **Conditional** - Relies on contains() check         |
| **Loop Termination**            | **Guaranteed** - availableClients.size() decreases   | **Potential infinite loop** - if unlucky with random |
| **Performance (Small Dataset)** | ✅ **Fast** - Direct index access                     | ⚠️ **Acceptable** - Contains() is manageable         |
| **Performance (Large Dataset)** | ✅ **Excellent** - Scales linearly                    | ❌ **Poor** - Contains() becomes expensive            |
| **Memory Usage**                | ❌ **Higher** - Stores copy of all clients            | ✅ **Lower** - Only selected clients                  |
| **Code Readability**            | ✅ **Clear** - Straightforward logic flow             | ⚠️ **Acceptable** - Conditional logic                |
| **Predictable Runtime**         | ✅ **Yes** - Always O(n) operations                   | ❌ **No** - Depends on random luck                    |
| **Risk of Infinite Loop**       | ✅ **None** - Loop always terminates                  | ❌ **Possible** - If random keeps hitting duplicates  |

## assignClientsToDrivingCourses: `contains()`

- Branch: `master`

- Commit:  [GitHub - AlbertProfe/rentingCarTest at 463c84268e14d8a200341d2da71e93dc86b3e878](https://github.com/AlbertProfe/rentingCarTest/tree/463c84268e14d8a200341d2da71e93dc86b3e878)

- [rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/utils/PopulateDrivingCourse.java at 463c84268e14d8a200341d2da71e93dc86b3e878 · AlbertProfe/rentingCarTest · GitHub](https://github.com/AlbertProfe/rentingCarTest/blob/463c84268e14d8a200341d2da71e93dc86b3e878/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/utils/PopulateDrivingCourse.java#L91)

```java
public void assignClientsToDrivingCourses(ArrayList<DrivingCourse> drivingCourses){
        //List<Client> allClients = (List<Client>) clientRepository.findAll();
        // Fetch all clients from database
        List<Client> allClients = new ArrayList<>();
        clientRepository.findAll().forEach(allClients::add);
        // defensive programming: check if there are clients in the database
        if (allClients.isEmpty()) {
            System.out.println("No clients found in database. Cannot assign clients to driving courses.");
            return;
        }

        Random random = new Random();
        // loop: for each driving course
        for (DrivingCourse drivingCourse : drivingCourses) {
            // Determine number of clients to assign (between 5 and 10)
            int numClientsToAssign = 5 + random.nextInt(6); // 5 to 10 clients

            List<Client> clientsToAdd = new ArrayList<>();

            // Add random clients without duplicates
            // clientsToAdd.size() < numClientsToAssign: until we have assigned the required number of clients
            // clientsToAdd.size() < allClients.size(): until we run out of clients
            while (clientsToAdd.size() < numClientsToAssign && clientsToAdd.size() < allClients.size()) {
                // Get a random client from allClients
                Client randomClient = allClients.get(random.nextInt(allClients.size()));

                // Check if this client is already in clientsToAdd
                if (!clientsToAdd.contains(randomClient)) {
                    clientsToAdd.add(randomClient);
                }
            }

            // Add all selected clients to the driving course
            drivingCourse.getClients().addAll(clientsToAdd);

            // Save the updated driving course with assigned clients
            drivingCourseRepository.save(drivingCourse);

            System.out.println("Assigned " + clientsToAdd.size() + " clients to course: '" + drivingCourse.getCourseName() +
                             "' (ID: " + drivingCourse.getId() + ", Instructor: " + drivingCourse.getInstructor() + 
                             ", Max Students: " + drivingCourse.getMaxStudents() + ")");
        }
    }
```

## assignClientsToDrivingCourses: `availableClients`

- Branch `populateDrivingCourses-with-rnd-clients`

- Commit: [feat: study different approaches to assignClientsToDrivingCourses · AlbertProfe/rentingCarTest@ab62cbb · GitHub](https://github.com/AlbertProfe/rentingCarTest/commit/ab62cbbf8bb6bff1ce7ff8e0c15755a1f42e5bff)

- [rentingCarTest/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/utils/PopulateDrivingCourse.java at ab62cbbf8bb6bff1ce7ff8e0c15755a1f42e5bff · AlbertProfe/rentingCarTest · GitHub](https://github.com/AlbertProfe/rentingCarTest/blob/ab62cbbf8bb6bff1ce7ff8e0c15755a1f42e5bff/rentingCar-boot/src/main/java/dev/app/rentingCar_boot/utils/PopulateDrivingCourse.java#L91)

```java
public void assignClientsToDrivingCourses(ArrayList<DrivingCourse> drivingCourses){
        //List<Client> allClients = (List<Client>) clientRepository.findAll();
        // Fetch all clients from database
        List<Client> allClients = new ArrayList<>();
        clientRepository.findAll().forEach(allClients::add);
        // defensive programming: check if there are clients in the database
        if (allClients.isEmpty()) {
            System.out.println("No clients found in database. Cannot assign clients to driving courses.");
            return;
        }

        Random random = new Random();
        // loop: for each driving course
        for (DrivingCourse drivingCourse : drivingCourses) {
            // Determine number of clients to assign (between 5 and 10)
            int numClientsToAssign = 5 + random.nextInt(6); // 5 to 10 clients

            List<Client> clientsToAdd = new ArrayList<>();

            // Create a copy of all clients that we can modify
            List<Client> availableClients = new ArrayList<>(allClients);

            // Add random clients without duplicates
            // clientsToAdd.size() < numClientsToAssign: until we have assigned the required number of clients
            // availableClients.size() > 0: until we run out of available clients
            while (clientsToAdd.size() < numClientsToAssign && availableClients.size() > 0) {
                // Get a random client from availableClients
                int randomIndex = random.nextInt(availableClients.size());
                Client randomClient = availableClients.get(randomIndex);

                // Add the client to our selection
                clientsToAdd.add(randomClient);

                // Remove the selected client from available clients to avoid duplicates
                availableClients.remove(randomIndex);
            }

            // Add all selected clients to the driving course
            drivingCourse.getClients().addAll(clientsToAdd);

            // Save the updated driving course with assigned clients
            drivingCourseRepository.save(drivingCourse);

            System.out.println("Assigned " + clientsToAdd.size() + " clients to course: '" + drivingCourse.getCourseName() +
                             "' (ID: " + drivingCourse.getId() + ", Instructor: " + drivingCourse.getInstructor() + 
                             ", Max Students: " + drivingCourse.getMaxStudents() + ")");
        }
    }
```

## **Infinite Loop Probability Analysis**

 **Scenario Setup:**

- **1,000,000 courses** each needing 5-10 clients
- **1,000,000 clients** available
- Using `contains()` approach with random selection

**Mathematical Analysis:**

| **Selection Stage** | **Probability of Duplicate** | **Probability of Success** |
| ------------------- | ---------------------------- | -------------------------- |
| **1st client**      | 0/1,000,000 = **0%**         | **100%**                   |
| **2nd client**      | 1/1,000,000 = **0.0001%**    | **99.9999%**               |
| **3rd client**      | 2/1,000,000 = **0.0002%**    | **99.9998%**               |
| **4th client**      | 3/1,000,000 = **0.0003%**    | **99.9997%**               |
| **5th client**      | 4/1,000,000 = **0.0004%**    | **99.9996%**               |
| **10th client**     | 9/1,000,000 = **0.0009%**    | **99.9991%**               |

 **Worst-Case Scenario Analysis:**

For a course trying to select **10 clients** from **1 million**:

```
Expected attempts for 10th client = 1 / (1 - 9/1,000,000) = 1.000009 attempts
```

 **Probability Calculations:**

| **Metric**                                                      | **Value**      | **Explanation**                       |
| --------------------------------------------------------------- | -------------- | ------------------------------------- |
| **Probability of needing >100 attempts for any single client**  | **≈ 0%**       | (9/1,000,000)^100 ≈ 10^-600           |
| **Probability of needing >1000 attempts for any single client** | **≈ 0%**       | (9/1,000,000)^1000 ≈ 10^-6000         |
| **Expected total attempts per course**                          | **≈ 55.00005** | Sum of 1/(1-k/1M) for k=0 to 9        |
| **Probability of infinite loop per course**                     | **≈ 0%**       | Mathematically negligible             |
| **Probability of infinite loop across all courses**             | **≈ 0%**       | Still negligible even with 1M courses |

### **Practical Reality:**

```java
// Worst case for 10th client selection:
// Probability of hitting duplicate = 9/1,000,000 = 0.0009%
// Expected attempts = 1/(1-0.000009) ≈ 1.000009 attempts

// Even in extreme bad luck (1 in trillion chance):
// 1000 consecutive duplicates = (0.000009)^1000 ≈ 0% chance
```

### **Conclusion:**

| **Assessment**                   | **Result**                                   |
| -------------------------------- | -------------------------------------------- |
| **Practical Infinite Loop Risk** | **Essentially 0%**                           |
| **Theoretical Possibility**      | **Yes, but negligible**                      |
| **Expected Performance Impact**  | **Minimal** - ~55 attempts vs 55 operations  |
| **Real-World Concern**           | **No** - More likely to hit hardware failure |

> **Verdict:** While infinite loops are mathematically possible with 1M clients/courses, the probability is so low it's practically impossible. But the `availableClients` approach eliminates even this theoretical risk while providing consistent performance.
