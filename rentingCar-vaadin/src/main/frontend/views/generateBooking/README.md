# Generate Booking Component Structure

This directory contains the decoupled components for the Generate Booking functionality, organized in a hierarchical structure for better maintainability and reusability.

## Directory Structure

```
generateBooking/
├── GenerateBooking.tsx          (main view - exposed as route)
├── _components/                 (sub-components - hidden from router with _ prefix)
│   ├── ClientSummary.tsx
│   ├── CarsLoadingView.tsx
│   ├── CarsErrorView.tsx
│   ├── GenerateBookingFlow.tsx
│   ├── BookingForm.tsx
│   ├── CarSelect.tsx
│   ├── BookingDatePicker.tsx
│   ├── DaysField.tsx
│   ├── SubmitButton.tsx
│   ├── BookingSuccessNotification.tsx
│   ├── BookingErrorNotification.tsx
│   └── BookingResultDisplay.tsx
├── index.ts                     (clean exports)
└── README.md                    (this file)
```

## Component Hierarchy

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
     ├── BookingSuccessNotification  (imperative via exported functions)
     ├── BookingErrorNotification    (imperative via exported functions)
     └── BookingResultDisplay
```

## Components Description

### Main Components

- **`GenerateBooking.tsx`** - Main view component that handles car loading and orchestrates the entire booking flow
- **`ClientSummary.tsx`** - Displays client information in a collapsible details section
- **`CarsLoadingView.tsx`** - Loading state component shown while fetching cars
- **`CarsErrorView.tsx`** - Error state component shown when car loading fails
- **`GenerateBookingFlow.tsx`** - Manages the booking process state and orchestrates form submission

### Form Components

- **`BookingForm.tsx`** - Container for all form elements
- **`CarSelect.tsx`** - Car selection dropdown component
- **`BookingDatePicker.tsx`** - Date picker for booking date selection
- **`DaysField.tsx`** - Integer field for quantity of days
- **`SubmitButton.tsx`** - Submit button with loading state

### Notification Components

- **`BookingSuccessNotification.tsx`** - Imperative success notification (exports `showBookingSuccess` function)
- **`BookingErrorNotification.tsx`** - Imperative error notification (exports `showBookingError` function)

### Display Components

- **`BookingResultDisplay.tsx`** - Displays booking result in a collapsible details section

## Key Features

### Separation of Concerns
- **State Management**: Each component manages its own local state
- **Data Flow**: Props flow down, events bubble up
- **Side Effects**: Isolated to appropriate components (API calls in `GenerateBooking` and `GenerateBookingFlow`)

### Reusability
- Form components can be reused in other booking contexts
- Notification functions can be imported and used anywhere
- Loading and error views can be adapted for other data fetching scenarios

### Maintainability
- Single responsibility principle applied to each component
- Clear component boundaries and interfaces
- TypeScript interfaces ensure type safety

## Usage

### Direct Import
```typescript
import GenerateBooking from './generateBooking/GenerateBooking';
```

### Via Index File
```typescript
import { 
  GenerateBooking, 
  CarSelect, 
  showBookingSuccess 
} from './generateBooking';
```

## Important Notes

### Vaadin File-Based Routing
The sub-components are placed in the `_components/` subdirectory (with underscore prefix) to prevent Vaadin's file-based router from automatically creating routes for them. The underscore prefix is a common convention to hide directories from route generation. Only `GenerateBooking.tsx` should be exposed as a route since the other components require props and are not meant to be standalone pages.

### Notification Usage
```typescript
import { showBookingSuccess, showBookingError } from './generateBooking';

// Show success notification
showBookingSuccess('Booking created successfully!');

// Show error notification
showBookingError('Failed to create booking');
```

## State Flow

1. **Initial Load**: `GenerateBooking` fetches cars and shows `CarsLoadingView`
2. **Error Handling**: If fetch fails, shows `CarsErrorView`
3. **Success**: Shows `ClientSummary` and `GenerateBookingFlow`
4. **Form Interaction**: User interacts with form components in `BookingForm`
5. **Submission**: `GenerateBookingFlow` handles submission and shows notifications
6. **Result Display**: `BookingResultDisplay` shows the booking result

## Benefits of This Structure

1. **Modularity**: Each component has a single, clear responsibility
2. **Testability**: Components can be tested in isolation
3. **Reusability**: Form components can be reused in other contexts
4. **Maintainability**: Changes to one component don't affect others
5. **Type Safety**: Full TypeScript support with proper interfaces
6. **Performance**: Components can be optimized individually
7. **Developer Experience**: Clear structure makes it easy to find and modify code
