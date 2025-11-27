import { VerticalLayout } from '@vaadin/react-components/VerticalLayout.js';
import { ProgressBar } from '@vaadin/react-components/ProgressBar.js';

export default function CarsLoadingView() {
  return (
    <VerticalLayout style={{ margin: '100px' }}>
      <h1>Generate Booking</h1>
      <p>Loading cars...</p>
      <ProgressBar indeterminate />
      <img 
        style={{ width: '800px' }} 
        src="https://raw.githubusercontent.com/AlbertProfe/rentingCarTest/refs/heads/master/docs/ui/create_booking.drawio.png" 
        alt="Booking Flow Diagram"
      />
    </VerticalLayout>
  );
}
