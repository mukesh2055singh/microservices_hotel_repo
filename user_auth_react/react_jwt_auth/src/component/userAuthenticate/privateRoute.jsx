import React from 'react';
import { Route, Navigate } from 'react-router-dom';
import { UseAuth } from './userAutheticate';
import HomePage from '../homePackage/homePage';

const PrivateRoute = ({ children }) => {
    const { authenticated } = UseAuth();

    return authenticated ? 
        <>{ children }</>
     : 
        <Navigate to="/user/showLogin" />
    ;
};

export default PrivateRoute;