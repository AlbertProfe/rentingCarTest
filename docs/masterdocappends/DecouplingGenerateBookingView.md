# Decoupling Strategy for GenerateBooking View

### From Monolithic HomeView to Clean, Maintainable Architecture

![](https://raw.githubusercontent.com/AlbertProfe/rentingCarTest/refs/heads/master/docs/ui/create_booking.drawio.png)

- [rentingCarTest/rentingCar-vaadin/src/main/frontend/views/@index.tsx at 7e2d652841eaa550862fdf1dd803bb4b5cceecdd · AlbertProfe/rentingCarTest · GitHub](https://github.com/AlbertProfe/rentingCarTest/blob/7e2d652841eaa550862fdf1dd803bb4b5cceecdd/rentingCar-vaadin/src/main/frontend/views/%40index.tsx)

> Decoupling is not just a “nice-to-have refactoring” — it is a **strategic necessity** for a production-grade Vaadin + Hilla + React application . 
> 
> Splitting `GenerateBooking` into the clearly named child components listed previously transforms it from a fragile prototype view into a scalable, testable, and reusable foundation for the entire booking subsystem.

### Why decoupling the original `HomeView` (now `GenerateBooking`) is critically relevant

1. **Single Responsibility Principle (SRP) Violation in the Original Component**  
   The original file was doing **everything at once**:  
   
   - Fetching cars (`useEffect` + endpoint call)  
   - Managing loading and error states  
   - Rendering hardcoded client info  
   - Building and validating the entire form  
   - Handling submission logic and notifications  
   - Displaying raw booking result  
     → **~180 lines** in one function component = classic God Component anti-pattern.

2. **Testability Becomes Nearly Impossible**  
   Unit testing the booking logic requires mocking `CarEndpoint`, `GenerateBookingEndpoint`, Vaadin components, notifications, etc. With everything intertwined, you end up with fragile, bloated tests or no tests at all.

3. **Reusability is Blocked**  
   
   - The same booking form (car + date + days) will very likely be needed in other views (e.g., admin panel, mobile version, quick-booking widget).  
   - The client summary block is useful anywhere you need to display the current user.  
   - Loading/error patterns are repeated across the entire Festival Manager platform.

4. **Maintainability & Team Collaboration Nightmare**  
   Every small change (e.g., modify success message, add a new field, change notification duration) forces developers to scroll through a massive component and risk breaking unrelated parts.

5. **Performance & Readability Degradation**  
   React re-renders the entire view on every state change (even when only the submission is in progress), making it harder to optimize with `React.memo` or future `useTransition` patterns.

### 

## Approach

Here are one approach **clear, descriptive component names** for decoupling the `HomeView` (which will be renamed to `GenerateBooking`) – each one focused on a single responsibility:

| Purpose                             | Suggested Child Component Name            | What it will render / handle                                     |
| ----------------------------------- | ----------------------------------------- | ---------------------------------------------------------------- |
| Loading state (initial fetch)       | `CarsLoadingView`                         | "Loading cars…", ProgressBar, optional image                     |
| Error state when cars fail to load  | `CarsErrorView`                           | Error message when car list couldn’t be fetched                  |
| Generic error notification (submit) | `BookingErrorNotification`                | Shows error from booking submission (re-usable)                  |
| Success notification (submit)       | `BookingSuccessNotification`              | Shows success message after booking is created                   |
| The whole booking request flow      | `GenerateBookingFlow` or `BookingCreator` | Orchestrates form → submit → result (main logic component)       |
| The actual form with inputs         | `BookingForm`                             | Select car, DatePicker, IntegerField (days), submit button       |
| Client information summary          | `ClientSummary`                           | The <Details> block with hardcoded client data                   |
| Booking result display              | `BookingResultDisplay`                    | The <Details> that shows the raw string result from the endpoint |

### Approach tree

```
GenerateBooking (main view)
├── ClientSummary
├── CarsLoadingView          (only while loading)
├── CarsErrorView            (only on load error)
├── GenerateBookingFlow
     ├── BookingForm
     │    ├── CarSelect
     │    ├── BookingDatePicker
     │    ├── DaysField
     │    └── SubmitButton
     ├── BookingSuccessNotification  (imperative via context or props)
     ├── BookingErrorNotification    (imperative via context or props)
     └── BookingResultDisplay
```

We can even go one level deeper and extract `CarSelect`, `BookingDatePicker`, `DaysField` if we want maximum reusability, but the names above are the most useful split points for readability and future maintenance.
