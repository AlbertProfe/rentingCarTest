import { FormLayout } from '@vaadin/react-components/FormLayout.js';
import Car from 'Frontend/generated/dev/app/rentingcartestvaadin/model/Car';
import CarSelect from './CarSelect';
import BookingDatePicker from './BookingDatePicker';
import DaysField from './DaysField';
import SubmitButton from './SubmitButton';

interface BookingFormProps {
  cars: Car[];
  selectedCar: Car | null;
  bookingDate: string;
  qtyDays: number;
  submitting: boolean;
  onCarChange: (car: Car | null) => void;
  onDateChange: (date: string) => void;
  onDaysChange: (days: number) => void;
  onSubmit: () => void;
}

export default function BookingForm({
  cars,
  selectedCar,
  bookingDate,
  qtyDays,
  submitting,
  onCarChange,
  onDateChange,
  onDaysChange,
  onSubmit
}: BookingFormProps) {
  return (
    <FormLayout>
      <CarSelect
        cars={cars}
        selectedCar={selectedCar}
        onCarChange={onCarChange}
        disabled={submitting}
      />
      
      <BookingDatePicker
        value={bookingDate}
        onChange={onDateChange}
        disabled={submitting}
      />
      
      <DaysField
        value={qtyDays}
        onChange={onDaysChange}
        disabled={submitting}
      />
      
      <SubmitButton
        onSubmit={onSubmit}
        submitting={submitting}
      />
    </FormLayout>
  );
}
