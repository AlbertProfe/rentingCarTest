import { Details } from '@vaadin/react-components/Details.js';

interface BookingResultDisplayProps {
  result: string | null;
}

export default function BookingResultDisplay({ result }: BookingResultDisplayProps) {
  if (!result) {
    return null;
  }

  return (
    <Details summary="Booking Result" opened>
      <pre>{result}</pre>
    </Details>
  );
}
