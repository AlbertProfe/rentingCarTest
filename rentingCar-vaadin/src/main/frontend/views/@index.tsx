import { ViewConfig } from '@vaadin/hilla-file-router/types.js';
import { VerticalLayout } from '@vaadin/react-components/VerticalLayout.js';
import { HorizontalLayout } from '@vaadin/react-components/HorizontalLayout.js';
import { Button } from '@vaadin/react-components/Button.js';
import { Card } from '@vaadin/react-components/Card.js';
import { Icon } from '@vaadin/react-components/Icon.js';
import '@vaadin/icons';

export const config: ViewConfig = {
    menu: {
        order: 0, icon: 'line-awesome/svg/home-solid.svg'
        },
    title: 'Home'
    };

export default function HomeView() {
  const handleLogin = () => {
    // Navigate to login or show login dialog
    console.log('Login clicked');
  };

  const handleRentCar = () => {
    // Navigate to GenerateBookingView
    window.location.href = '/GenereateBookingView';
  };

  return (
    <VerticalLayout className="home-container">
      {/* Hero Section */}
      <VerticalLayout className="hero-section">
        <h1 className="hero-title">
          Premium Car Rental
        </h1>
        <p className="hero-description">
          Experience luxury and comfort with our premium fleet. 
          Book your perfect ride today and drive with confidence.
        </p>
        
        <HorizontalLayout className="hero-buttons">
          <Button 
            theme="primary large"
            onClick={handleLogin}
            className="login-button"
          >
            <Icon icon="vaadin:sign-in" slot="prefix" />
            Login
          </Button>
          
          <Button 
            theme="primary large"
            onClick={handleRentCar}
            className="rent-button"
          >
            <Icon icon="vaadin:car" slot="prefix" />
            Rent a Car
          </Button>
        </HorizontalLayout>
      </VerticalLayout>

      {/* Features Section */}
      <VerticalLayout className="features-section">
        <h2 className="features-title">
          Why Choose Us?
        </h2>
        
        <HorizontalLayout className="features-container">
          <Card className="feature-card">
            <Icon 
              icon="vaadin:car" 
              className="feature-icon-safety"
            />
            <h3 className="feature-card-title">Premium Safety</h3>
            <p className="feature-card-text">
              All our vehicles undergo rigorous safety checks and maintenance 
              to ensure your peace of mind on every journey.
            </p>
          </Card>

          <Card className="feature-card">
            <Icon 
              icon="vaadin:clock" 
              className="feature-icon-support"
            />
            <h3 className="feature-card-title">24/7 Support</h3>
            <p className="feature-card-text">
              Our dedicated customer support team is available around the clock 
              to assist you with any questions or concerns.
            </p>
          </Card>

          <Card className="feature-card">
            <Icon 
              icon="vaadin:dollar" 
              className="feature-icon-price"
            />
            <h3 className="feature-card-title">Best Prices</h3>
            <p className="feature-card-text">
              Competitive rates with no hidden fees. Get the best value 
              for your money with our transparent pricing.
            </p>
          </Card>
        </HorizontalLayout>
      </VerticalLayout>

      {/* Call to Action Section */}
      <VerticalLayout className="cta-section">
        <h2 className="cta-title">
          Ready to Hit the Road?
        </h2>
        <p className="cta-description">
          Join thousands of satisfied customers who trust us with their travel needs.
        </p>
        
        <Button 
          theme="primary large"
          onClick={handleRentCar}
          className="cta-button"
        >
          <Icon icon="vaadin:rocket" slot="prefix" />
          Start Your Journey
        </Button>
      </VerticalLayout>
    </VerticalLayout>
  );
}
