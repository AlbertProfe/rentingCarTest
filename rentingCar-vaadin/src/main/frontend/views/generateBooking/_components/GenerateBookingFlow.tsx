import { useState } from 'react';
import { GenerateBookingEndpoint } from 'Frontend/generated/endpoints';
import Car from 'Frontend/generated/dev/app/rentingcartestvaadin/model/Car';
import Client from 'Frontend/generated/dev/app/rentingcartestvaadin/model/Client';
import BookingForm from './BookingForm';
import BookingResultDisplay from './BookingResultDisplay';
import { showBookingSuccess } from './BookingSuccessNotification';
import { showBookingError } from './BookingErrorNotification';

interface GenerateBookingFlowProps {
  cars: Car[];
  client: Client;
}

export default function GenerateBookingFlow({ cars, client }: GenerateBookingFlowProps) {
  // Form state
  const [selectedCar, setSelectedCar] = useState<Car | null>(null);
  const [bookingDate, setBookingDate] = useState<string>('');
  const [qtyDays, setQtyDays] = useState<number>(1);
  
  // Request state
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState<string | null>(null);
  const [bookingResult, setBookingResult] = useState<string | null>(null);

  const handleSubmit = async () => {
    if (!selectedCar) {
      showBookingError('Please select a car');
      return;
    }

    if (!bookingDate) {
      showBookingError('Please select a booking date');
      return;
    }

    try {
      setSubmitting(true);
      setError(null);
      setBookingResult(null);

      // Convert date string to Unix timestamp (epoch seconds at 00:00:00 GMT)
      const dateObj = new Date(bookingDate + 'T00:00:00.000Z');
      const bookingDateEpoch = Math.floor(dateObj.getTime() / 1000);

      // Call GenerateBookingEndpoint with IDs instead of full objects
      const result = await GenerateBookingEndpoint.generateBooking(
        client.id!,
        selectedCar.id!,
        bookingDateEpoch,
        qtyDays
      );

      // Set the string result from the endpoint
      setBookingResult(result || null);
      if (result) {
        showBookingSuccess('Booking created successfully!');
      }

    } catch (err) {
      const errorMessage = 'Failed to create booking: ' + (err as Error).message;
      setError(errorMessage);
      showBookingError(errorMessage);
    } finally {
      // Reset form to default values
      setSubmitting(false);
      setQtyDays(1);
      setSelectedCar(null);
      setBookingDate('');
    }
  };

  return (
    <>
      <BookingForm
        cars={cars}
        selectedCar={selectedCar}
        bookingDate={bookingDate}
        qtyDays={qtyDays}
        submitting={submitting}
        onCarChange={setSelectedCar}
        onDateChange={setBookingDate}
        onDaysChange={setQtyDays}
        onSubmit={handleSubmit}
      />
      
      <BookingResultDisplay result={bookingResult} />
    </>
  );
}
