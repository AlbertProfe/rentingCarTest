import { DatePicker } from '@vaadin/react-components/DatePicker.js';

interface BookingDatePickerProps {
  value: string;
  onChange: (date: string) => void;
  disabled?: boolean;
}

export default function BookingDatePicker({ value, onChange, disabled = false }: BookingDatePickerProps) {
  const handleValueChange = (e: any) => {
    onChange(e.detail.value);
  };

  return (
    <DatePicker
      label="Booking Date"
      value={value}
      onValueChanged={handleValueChange}
      disabled={disabled}
    />
  );
}
