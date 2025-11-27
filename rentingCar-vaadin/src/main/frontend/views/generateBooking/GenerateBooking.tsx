import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useEffect, useState } from 'react';
import { CarEndpoint } from 'Frontend/generated/endpoints';
import Car from 'Frontend/generated/dev/app/rentingcartestvaadin/model/Car';
import Client from 'Frontend/generated/dev/app/rentingcartestvaadin/model/Client';
import { VerticalLayout } from '@vaadin/react-components/VerticalLayout.js';

import ClientSummary from './_components/ClientSummary';
import CarsLoadingView from './_components/CarsLoadingView';
import CarsErrorView from './_components/CarsErrorView';
import GenerateBookingFlow from './_components/GenerateBookingFlow';

export const config: ViewConfig = {
    menu: {
        order: 4, icon: 'line-awesome/svg/car-side-solid.svg'
        },
    title: 'Book a car'
    };

// Hardcoded client
const hardcodedClient: Client = {
  id: '4782',
  name: 'Emma',
  lastName: 'Smith',
  email: 'emma.smith@gmail.com',
  age: 73,
  password: 'pass7032',
  premium: true
};

export default function GenerateBooking() {
  const [cars, setCars] = useState<Car[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    const fetchCars = async () => {
      try {
        setLoading(true);
        const carsData = await CarEndpoint.getAllCars();
        setCars(carsData ? Array.from(carsData).filter((car): car is Car => car !== undefined) : []);
      } catch (err) {
        setError('Failed to fetch cars: ' + (err as Error).message);
      } finally {
        setLoading(false);
      }
    };
    fetchCars();
  }, []);

  // Show loading view while fetching cars
  if (loading) {
    return <CarsLoadingView />;
  }

  // Show error view if there's an error and no cars loaded
  if (error && !cars.length) {
    return <CarsErrorView error={error} />;
  }

  return (
    <VerticalLayout style={{ margin: '100px' }}>
      <h1>Generate Booking</h1>
      <ClientSummary client={hardcodedClient} />
      <GenerateBookingFlow cars={cars} client={hardcodedClient} />
    </VerticalLayout>
  );
}
