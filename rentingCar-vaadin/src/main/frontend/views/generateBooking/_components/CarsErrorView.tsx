import { VerticalLayout } from '@vaadin/react-components/VerticalLayout.js';

interface CarsErrorViewProps {
  error: string;
}

export default function CarsErrorView({ error }: CarsErrorViewProps) {
  return (
    <VerticalLayout style={{ margin: '100px' }}>
      <h1>Generate Booking</h1>
      <p>{error}</p>
      <img 
        style={{ width: '800px' }} 
        src="https://raw.githubusercontent.com/AlbertProfe/rentingCarTest/refs/heads/master/docs/ui/create_booking.drawio.png" 
        alt="Booking Flow Diagram"
      />
    </VerticalLayout>
  );
}
