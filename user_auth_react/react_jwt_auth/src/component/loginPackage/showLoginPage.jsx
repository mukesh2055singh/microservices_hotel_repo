import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ShowLoginPage = () => {
  const [data, setData] = useState(null);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    try {
      //const response =axios.get('http://localhost:8085/user/showLogin');
      // Handle the response data
    } catch (error) {
      // Handle errors
      console.error('Error:', error);
    }
  };

  return (
    <div>
      <h2>Anchor Tab Content</h2>
      <button onClick={fetchData}>Load Data</button>
    </div>
  );
};

export default ShowLoginPage;