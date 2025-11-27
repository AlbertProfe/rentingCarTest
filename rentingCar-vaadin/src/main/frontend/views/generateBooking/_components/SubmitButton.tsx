import { Button } from '@vaadin/react-components/Button.js';

interface SubmitButtonProps {
  onSubmit: () => void;
  submitting: boolean;
}

export default function SubmitButton({ onSubmit, submitting }: SubmitButtonProps) {
  return (
    <Button
      theme="primary"
      onClick={onSubmit}
      disabled={submitting}
    >
      {submitting ? 'Creating Booking at server waiting for response...' : 'Generate Booking'}
    </Button>
  );
}
