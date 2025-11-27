import { Notification } from '@vaadin/react-components/Notification.js';

export const showBookingSuccess = (message: string) => {
  Notification.show(message, { theme: 'success' });
};

// This component is imperative - it doesn't render anything
// It's used via the exported function
export default function BookingSuccessNotification() {
  return null;
}
