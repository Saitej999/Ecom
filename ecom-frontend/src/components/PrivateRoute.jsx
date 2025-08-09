import React from 'react'
import { useSelector } from 'react-redux'
import { Navigate, Outlet, useLocation } from 'react-router-dom';

const PrivateRoute = ({ publicPage = false, adminOnly = false }) => {
    const { user } = useSelector((state) => state.auth);
    const isAdmin = user && user?.roles?.includes("ROLE_ADMIN");
    const isSeller = user && user?.roles.includes("ROLE_SELLER");
    const location = useLocation();
    
    // Debug logging (can be removed in production)
    if (process.env.NODE_ENV === 'development') {
        console.log('PrivateRoute check:', {
            location: location.pathname,
            user: user ? 'User logged in' : 'No user',
            userRoles: user?.roles,
            isAdmin,
            isSeller,
            publicPage,
            adminOnly
        });
    }

    if (publicPage) {
        return user ? <Navigate to="/" /> : <Outlet />
    }

    if (adminOnly) {
        if (!isAdmin && !isSeller) {
            return <Navigate to="/" replace />
        }
        
        if (isSeller && !isAdmin) {
            const sellerAllowedPaths = ["/admin/orders", "/admin/products"];
            const sellerAllowed = sellerAllowedPaths.some(path => 
                location.pathname.startsWith(path)
            );
            if (!sellerAllowed) {
                return <Navigate to="/" replace />
            }
        }
        
        return user ? <Outlet /> : <Navigate to="/login" />;
    }
    
    // For regular protected routes (like checkout), just check if user is logged in
    return user ? <Outlet /> : <Navigate to="/login" />;
}

export default PrivateRoute