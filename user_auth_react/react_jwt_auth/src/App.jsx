import React, { Component }  from 'react';
import './App.css';
import HomePage from './component/homePackage/homePage';
import { BrowserRouter as Router,Link,Routes,Route } from "react-router-dom";
import LoginPage from './component/loginPackage/loginPage';
import DashboardPage from './component/dashboard/dashboardPage';
import PrivateRoute from './component/userAuthenticate/privateRoute';
import { AuthProvider } from './component/userAuthenticate/userAutheticate';



function App() {
  return (
    <AuthProvider>
      <Router>
        
          <Routes>
            <Route path="/" Component={HomePage} ></Route>
            <Route path="/user/showLogin" Component={LoginPage} />
            <Route path="/detail/features" element={<PrivateRoute><HomePage /></PrivateRoute>} />
            <Route path="/detail/about" element={<PrivateRoute><HomePage /></PrivateRoute>} />
            <Route path="/detail/services" element={<PrivateRoute><HomePage /></PrivateRoute>} />
            <Route path="/detail/gallery" element={<PrivateRoute><HomePage /></PrivateRoute>} />
            <Route path="/dashboard" element={<PrivateRoute><DashboardPage /></PrivateRoute>} />
          </Routes>
        
      </Router>
    </AuthProvider>
  );
};

export default App;
