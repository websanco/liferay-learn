import React from 'react';
import 'bootstrap/dist/css/bootstrap.css';
import Carousel from 'react-bootstrap/Carousel';
import './style.css';



//Displays Account information from Liferay in a Carousel object

export default function Accounts() {
  return(
    <div className="Dashboard">
      <h2>Faria Financial Management</h2>
      <div className="carousel">
        <Carousel>
        <Carousel.Item interval={15000}>
            <h2>Checking Account 84566548</h2>
            <h3>$2500.17</h3>
            
          </Carousel.Item>
          <Carousel.Item interval={15000}>
            <h2>Savings Account 23755732</h2>
            <h3>$4694.98</h3>
            
          </Carousel.Item>
          <Carousel.Item interval={15000}>
            <h2>Indvidual Retirement Account 4321234</h2>
            <h3>$40067.68</h3>
          </Carousel.Item>
        </Carousel>
      </div>
    </div>
  );
};