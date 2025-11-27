import { Select } from '@vaadin/react-components/Select.js';
import Car from 'Frontend/generated/dev/app/rentingcartestvaadin/model/Car';

interface CarSelectProps {
  cars: Car[];
  selectedCar: Car | null;
  onCarChange: (car: Car | null) => void;
  disabled?: boolean;
}

export default function CarSelect({ cars, selectedCar, onCarChange, disabled = false }: CarSelectProps) {
  // Map cars to Select items
  const carItems = cars.map((car) => ({
    label: `${car.brand} ${car.model} - ${car.plate} (€${car.price}/day)`,
    value: car.id?.toString() || ''
  }));

  const handleValueChange = (e: any) => {
    const carId = parseInt(e.detail.value);
    const car = cars.find(c => c.id?.toString() === carId.toString()) || null;
    onCarChange(car);
  };

  return (
    <Select
      label="Select Car"
      placeholder="Choose a car..."
      items={carItems}
      value={selectedCar?.id?.toString() || ''}
      onValueChanged={handleValueChange}
      disabled={disabled}
    />
  );
}
