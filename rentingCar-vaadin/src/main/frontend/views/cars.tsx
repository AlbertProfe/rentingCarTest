import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { useEffect, useState } from 'react';
import { CarEndpoint } from 'Frontend/generated/endpoints';
import Car from 'Frontend/generated/dev/app/rentingcartestvaadin/model/Car';
import { VerticalLayout } from '@vaadin/react-components/VerticalLayout.js';
import { HorizontalLayout } from '@vaadin/react-components/HorizontalLayout.js';
import { Card } from '@vaadin/react-components/Card.js';
import { Details } from '@vaadin/react-components/Details.js';
import { ProgressBar } from '@vaadin/react-components/ProgressBar.js';

export const config: ViewConfig = { menu: { order: 1, icon: 'line-awesome/svg/car-side-solid.svg' }, title: 'Cars' };

export default function CarsView() {
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

  if (loading) {
    return (
      <VerticalLayout style={{ height: '100%', alignItems: 'center', justifyContent: 'center', padding: 'var(--lumo-space-l)' }}>
        <h1>Cars</h1>
        <p>Loading cars...</p>
        <ProgressBar indeterminate />
      </VerticalLayout>
    );
  }

  if (error) {
    return (
      <VerticalLayout style={{ height: '100%', alignItems: 'center', justifyContent: 'center', padding: 'var(--lumo-space-l)' }}>
        <h1>Cars</h1>
        <p style={{ color: 'var(--lumo-error-text-color)' }}>{error}</p>
      </VerticalLayout>
    );
  }

  return (
    <VerticalLayout style={{ height: '100%', padding: 'var(--lumo-space-l)' }}>
      <h1>Cars ({cars.length})</h1>

      <div style={{ 
        display: 'grid', 
        gridTemplateColumns: 'repeat(auto-fill, minmax(300px, 1fr))', 
        gap: 'var(--lumo-space-m)',
        width: '100%'
      }}>
        {cars.map((car) => (
          <Card key={car.id} style={{ padding: 'var(--lumo-space-m)' }}>
            <VerticalLayout style={{ gap: 'var(--lumo-space-s)' }}>
              <h3 style={{ margin: '0' }}>{car.brand} {car.model}</h3>
              <VerticalLayout style={{ gap: 'var(--lumo-space-xs)' }}>
                <HorizontalLayout>
                  <span style={{ fontWeight: 'bold' }}>Plate:</span>
                  <span>{car.plate}</span>
                </HorizontalLayout>
                <HorizontalLayout>
                  <span style={{ fontWeight: 'bold' }}>Year:</span>
                  <span>{car.year}</span>
                </HorizontalLayout>
                <HorizontalLayout>
                  <span style={{ fontWeight: 'bold' }}>Price:</span>
                  <span>${car.price}</span>
                </HorizontalLayout>
                {car.inssuranceCia && (
                  <HorizontalLayout>
                    <span style={{ fontWeight: 'bold' }}>Insurance:</span>
                    <span>{car.inssuranceCia.name}</span>
                  </HorizontalLayout>
                )}
                {car.availabilityRanges && (
                  <Details summary="Availability">
                    <pre style={{ 
                      fontSize: 'var(--lumo-font-size-xs)', 
                      backgroundColor: 'var(--lumo-contrast-5pct)', 
                      padding: 'var(--lumo-space-s)', 
                      borderRadius: 'var(--lumo-border-radius-s)',
                      margin: '0',
                      whiteSpace: 'pre-wrap'
                    }}>
                      {car.availabilityRanges}
                    </pre>
                  </Details>
                )}
              </VerticalLayout>
            </VerticalLayout>
          </Card>
        ))}
      </div>

      {cars.length === 0 && (
        <VerticalLayout style={{ alignItems: 'center', marginTop: 'var(--lumo-space-xl)' }}>
          <p>No cars found. Try populating the database first.</p>
        </VerticalLayout>
      )}
    </VerticalLayout>
  );
}
