import React, { createContext, useContext, useState } from 'react';

const AuthContext = createContext();

const AuthProvider = ({ children }) => {
    const [authenticated, setAuthenticated] = useState(false);

    const login = () => {
        // Perform authentication logic if needed
        // ...

        // Set authenticated to true
        setAuthenticated(true);
    };

    const logout = () => {
        // Perform logout logic if needed
        // ...

        // Set authenticated to false
        setAuthenticated(false);
    };

    return (
        <AuthContext.Provider value={{ authenticated, login, logout }}>
            {children}
        </AuthContext.Provider>
    );
};

const UseAuth = () => {
    const context = useContext(AuthContext);
    if (!context) {
        throw new Error('useAuth must be used within an AuthProvider');
    }
    return context;
};

export { AuthProvider, UseAuth };