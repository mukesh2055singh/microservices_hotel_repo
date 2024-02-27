import React, { useState, useEffect } from 'react';
import axios from 'axios';
import JwtTokenInterceptor from '../userAuthenticate/jwtTokenInterceptor';

const DashboardPage = () => {
  const [data, setData] = useState(null);

  // useEffect(() => {
  //   fetchData();
  // }, []);

  const fetchData = async () => {
    try {
      const { id } =1;
      const response = await JwtTokenInterceptor.get('hotel/checkKafkaMsg', {
        method: 'GET',
      });
      // Handle the response data
      console.log('response--:'+response);
    } catch (error) {
      // Handle errors
      console.error('Error:', error);
    };
  };

  return (
    <div>
      <h2>Anchor Tab Content</h2>
      <button onClick={fetchData}>Load Data</button>
    </div>
  );
};

export default DashboardPage;