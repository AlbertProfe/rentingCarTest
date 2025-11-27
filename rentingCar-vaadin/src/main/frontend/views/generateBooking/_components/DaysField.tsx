import { IntegerField } from '@vaadin/react-components/IntegerField.js';

interface DaysFieldProps {
  value: number;
  onChange: (days: number) => void;
  disabled?: boolean;
}

export default function DaysField({ value, onChange, disabled = false }: DaysFieldProps) {
  const handleValueChange = (e: any) => {
    onChange(parseInt(e.detail.value) || 1);
  };

  return (
    <IntegerField
      label="Quantity of Days"
      value={value.toString()}
      min={1}
      onValueChanged={handleValueChange}
      disabled={disabled}
    />
  );
}
