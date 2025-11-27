import { Details } from '@vaadin/react-components/Details.js';
import { VerticalLayout } from '@vaadin/react-components/VerticalLayout.js';
import Client from 'Frontend/generated/dev/app/rentingcartestvaadin/model/Client';

interface ClientSummaryProps {
  client: Client;
}

export default function ClientSummary({ client }: ClientSummaryProps) {
  return (
    <Details summary="Client Information">
      <VerticalLayout style={{ margin: '10px' }}>
        <p><strong>Name:</strong> {client.name} {client.lastName}</p>
        <p><strong>Email:</strong> {client.email}</p>
        <p><strong>Client subscription:</strong> {client.premium ? "Premium" : "Standard"}</p>
      </VerticalLayout>
    </Details>
  );
}
