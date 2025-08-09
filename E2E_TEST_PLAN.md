# End-to-End Testing Plan for Ecommerce Application

## Issues Identified and Fixes Applied

### 🔧 **Critical Fixes Made:**

1. **JWT Authentication Filter Fixed**
   - ✅ Fixed missing import `OncePerRequestFilter` in `AuthTokenFilter.java`
   - ✅ JWT token parsing now works for both cookies and Authorization headers

2. **Cart Isolation Between Users**
   - ✅ Added `localStorage.removeItem("cartItems")` in login/logout actions
   - ✅ Cart data is cleared when users switch accounts

3. **Checkout Address API Fixed**
   - ✅ Changed `/addresses` to `/users/addresses` in `getUserAddresses` action
   - ✅ Users now fetch only their own addresses instead of all addresses

4. **Error Handling Enhanced**
   - ✅ Added better error logging for authentication failures
   - ✅ Improved Stripe payment secret creation error handling

## 🧪 **Test Scenarios to Execute:**

### **Phase 1: Authentication Flow**
- [ ] User Registration with valid data
- [ ] User Login with correct credentials  
- [ ] User Login with incorrect credentials
- [ ] JWT token persistence across browser refresh
- [ ] Logout functionality
- [ ] Admin login and access
- [ ] Seller login and access

### **Phase 2: Product Browsing**
- [ ] View product catalog without login
- [ ] Filter products by category
- [ ] Search products by name
- [ ] Product pagination
- [ ] View product details

### **Phase 3: Cart Operations**
- [ ] Add products to cart (requires login)
- [ ] Update product quantity in cart
- [ ] Remove products from cart
- [ ] Cart persistence for logged-in users
- [ ] Cart isolation between different users
- [ ] Cart total calculation accuracy

### **Phase 4: Address Management**
- [ ] Add new address for user
- [ ] View user-specific addresses only
- [ ] Update existing address
- [ ] Delete address
- [ ] Select address for checkout

### **Phase 5: Checkout Process**
- [ ] Access checkout page (authenticated users only)
- [ ] Select delivery address
- [ ] Choose payment method (Stripe/PayPal)
- [ ] Review order summary
- [ ] Stripe payment integration
- [ ] Payment confirmation and order creation
- [ ] Order history display

### **Phase 6: Admin Functions**
- [ ] Admin dashboard access
- [ ] Category management (CRUD)
- [ ] Product management (CRUD)
- [ ] Order management and status updates
- [ ] User management (view sellers)

### **Phase 7: Seller Functions**
- [ ] Seller access to product management
- [ ] Seller access to order management
- [ ] Seller restrictions (cannot access user management)

## 🚨 **Critical Test Cases:**

### **Authentication Edge Cases:**
1. **Token Expiration**: What happens when JWT expires during checkout?
2. **Concurrent Sessions**: Multiple tabs with different users
3. **Browser Refresh**: Does authentication persist?
4. **API 401/403 Handling**: Proper redirect to login

### **Cart Edge Cases:**
1. **User Switch**: Cart isolation when switching between accounts
2. **Product Deletion**: What happens if admin deletes product in user's cart?
3. **Stock Changes**: Handling out-of-stock products during checkout
4. **Guest to User**: Cart transfer when guest user registers

### **Payment Edge Cases:**
1. **Payment Failure**: Stripe payment rejection handling
2. **Network Issues**: Payment interruption scenarios
3. **Address Validation**: Invalid or incomplete addresses
4. **Amount Mismatch**: Frontend/backend total price validation

## 🎯 **Success Criteria:**

- ✅ All user types can login and access appropriate features
- ✅ Cart operations work correctly for each user
- ✅ Checkout process completes without authentication errors
- ✅ Payment integration works end-to-end
- ✅ Admin/Seller role-based access works properly
- ✅ No cross-user data leakage (cart, addresses, orders)

## 🔄 **Testing Protocol:**

1. **Start both servers**: Backend (port 8080) and Frontend (port 5173)
2. **Execute tests in order**: Each phase builds on the previous
3. **Document issues**: Screenshot errors and console logs
4. **Verify fixes**: Re-test after each fix implementation
5. **Performance check**: Ensure reasonable response times

## 📊 **Test Data:**

### **Test Users:**
- **Regular User**: user1 / password1 (ROLE_USER)
- **Seller**: seller1 / password1 (ROLE_SELLER)  
- **Admin**: admin / password1 (ROLE_ADMIN)

### **Test Products:**
- Sample categories and products created via DataInitializer
- Various price ranges and stock levels
- Different product images and descriptions

### **Test Payment:**
- **Stripe Test Card**: 4242 4242 4242 4242
- **Expiry**: Any future date (12/34)
- **CVC**: Any 3 digits (123)
