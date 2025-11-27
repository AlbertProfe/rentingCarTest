import { Notification } from '@vaadin/react-components/Notification.js';

export const showBookingError = (message: string) => {
  Notification.show(message, { theme: 'error' });
};

// This component is imperative - it doesn't render anything
// It's used via the exported function
export default function BookingErrorNotification() {
  return null;
}
