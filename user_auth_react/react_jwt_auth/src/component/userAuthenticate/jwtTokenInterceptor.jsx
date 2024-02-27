import React from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';

// Create an Axios instance with default headers

const JwtTokenInterceptor = axios.create({
  baseURL: 'http://localhost:9090/',
  withCredentials: true,
});

// Add an interceptor to include the cookie with each request
JwtTokenInterceptor.interceptors.request.use(
  (response) => {
    const authorizationHeader = Cookies.get('jwtToken');
    console.log('authorizationHeader--:'+authorizationHeader);

    if (authorizationHeader != '') {
        // Extract and store the cookie (you may need to adjust the logic based on your use case)
        response.headers.Authorization = `Bearer ${authorizationHeader}`;
    }
  

    return response;
  },
  (error) => {
    // Do something with the request error
    return Promise.reject(error);
  }
);

export default JwtTokenInterceptor;
