# 🛒 eCommerce Application - User Guide

Welcome to your complete Java Spring Boot eCommerce application! This guide will help you navigate and use all the features of the platform.

---

## 🔐 Test Accounts

### Quick Access Credentials

| Role | Username | Password | Description |
|------|----------|----------|-------------|
| **Customer** | `user1` | `password1` | Regular shopping account |
| **Seller** | `seller1` | `password2` | Product management & sales |
| **Admin** | `admin` | `adminPass` | Full system administration |

> 💡 **Tip**: Use these accounts to test different features and permissions!

---

## 🏠 Getting Started

### 1. **Homepage**
- Browse featured products and categories
- View promotional banners
- Quick navigation to all sections

### 2. **User Registration**
- Click "Sign Up" to create a new account
- Fill in required details
- Choose your role (Customer/Seller)
- Email verification (if enabled)

### 3. **Login Process**
- Use the test credentials above
- Or login with your registered account
- Secure JWT-based authentication

---

## 🛍️ Shopping Features (Customer Role)

### **Product Browsing**
- **Categories**: Browse by Beverages, Energy Drinks, Dairy, Snacks, Fruits, Vegetables, Bakery, Frozen Foods, Health Products
- **Search**: Find products by name or description
- **Filters**: Sort by price, category, popularity
- **Product Details**: View images, descriptions, prices, and stock

### **Shopping Cart**
- Add products to cart
- Adjust quantities
- Remove items
- View total price
- Persistent cart (saved between sessions)

### **Checkout Process**
1. **Address Management**
   - Add multiple delivery addresses
   - Edit existing addresses
   - Select delivery location

2. **Payment Methods**
   - Stripe integration for secure payments
   - Credit/Debit card processing
   - Order confirmation

3. **Order Tracking**
   - View order history
   - Track order status
   - Download invoices

---

## 📦 Seller Features

### **Access Seller Dashboard**
- Login with `seller1` / `password2`
- Navigate to Seller Portal

### **Product Management**
- **Add Products**: Create new product listings
- **Edit Products**: Update details, prices, stock
- **Upload Images**: Add product photos
- **Inventory**: Track stock levels
- **Categories**: Organize products

### **Order Management**
- View incoming orders
- Update order status
- Process shipments
- Customer communication

### **Analytics**
- Sales reports
- Revenue tracking
- Popular products
- Customer insights

---

## ⚙️ Admin Features

### **Access Admin Panel**
- Login with `admin` / `adminPass`
- Full administrative privileges

### **User Management**
- View all users
- Manage customer accounts
- Handle seller applications
- Role assignments

### **Category Management**
- Create new categories
- Edit existing categories
- Organize product hierarchy
- Category analytics

### **System Administration**
- Monitor platform health
- Manage system settings
- View comprehensive analytics
- Database management

### **Order Oversight**
- View all platform orders
- Resolve disputes
- Generate reports
- Revenue tracking

---

## 🌟 Key Features

### **Security**
- JWT Authentication
- Role-based access control
- Secure password handling
- HTTPS encryption

### **Payment Integration**
- Stripe payment gateway
- Secure card processing
- PCI compliance
- Multiple payment methods

### **Responsive Design**
- Mobile-friendly interface
- Tablet compatibility
- Desktop optimization
- Cross-browser support

### **Real-time Updates**
- Live inventory tracking
- Instant order notifications
- Dynamic pricing
- Status updates

---

## 🎮 Testing Scenarios

### **Customer Journey Test**
1. Login as `user1` / `password1`
2. Browse products in "Energy Drinks" category
3. Add "Red Bull Energy Drink" to cart
4. Proceed to checkout
5. Add delivery address
6. Complete payment with test Stripe card
7. View order in order history

### **Seller Workflow Test**
1. Login as `seller1` / `password2`
2. Navigate to Seller Dashboard
3. Add a new product
4. Upload product image
5. Set price and stock quantity
6. View sales analytics

### **Admin Operations Test**
1. Login as `admin` / `adminPass`
2. View all users in system
3. Check platform analytics
4. Monitor recent orders
5. Manage product categories

---

## 🛠️ Troubleshooting

### **Common Issues**

**Login Problems**
- Verify credentials are correct
- Check caps lock is off
- Clear browser cache
- Try incognito mode

**Payment Issues**
- Use test card: `4242 4242 4242 4242`
- Any future expiry date
- Any 3-digit CVC
- Check internet connection

**Product Upload Issues**
- Ensure image is under 10MB
- Supported formats: JPG, PNG, WEBP
- Check seller permissions

**Cart Problems**
- Refresh the page
- Clear browser storage
- Re-login to account

---

## 📱 Sample Data Overview

### **Categories Available**
- 🥤 **Beverages** (Coca-Cola, Pepsi, Sprite, etc.)
- ⚡ **Energy Drinks** (Red Bull, Monster, Rockstar, etc.)
- 🥛 **Dairy Products** (Milk, Cheese, Yogurt, etc.)
- 🍿 **Snacks** (Chips, Crackers, Nuts, etc.)
- 🍎 **Fresh Fruits** (Apples, Bananas, Oranges, etc.)
- 🥕 **Vegetables** (Carrots, Broccoli, Spinach, etc.)
- 🍞 **Bakery** (Bread, Croissants, Muffins, etc.)
- 🧊 **Frozen Foods** (Ice Cream, Frozen Pizza, etc.)
- 💊 **Health Products** (Vitamins, Supplements, etc.)

### **Sample Products** (54+ items available)
- Premium products with realistic pricing
- High-quality placeholder images
- Detailed descriptions
- Stock quantities

---

## 🚀 Technical Details

### **Frontend Technologies**
- React 18 with Vite
- Redux Toolkit for state management
- Material-UI components
- Tailwind CSS styling
- Stripe React SDK

### **Backend Technologies**
- Java Spring Boot 3.5.3
- Spring Security with JWT
- PostgreSQL/H2 Database
- Maven build system
- Stripe Java SDK

### **Deployment**
- Frontend: Vercel
- Backend: Railway
- Database: PostgreSQL Cloud
- CDN: Image optimization

---

## 📞 Support & Contact

### **Need Help?**
- Check this user guide first
- Try the troubleshooting section
- Test with provided sample accounts
- Contact system administrator

### **Developer Information**
- **Repository**: [https://github.com/Saitej999/Ecommerce](https://github.com/Saitej999/Ecommerce)
- **Developer**: Saitej999
- **Technology Stack**: Java Spring Boot + React
- **Payment Gateway**: Stripe

---

## 🎉 Enjoy Shopping!

Your eCommerce platform is fully functional with:
- ✅ User authentication
- ✅ Product catalog
- ✅ Shopping cart
- ✅ Secure checkout
- ✅ Payment processing
- ✅ Order management
- ✅ Admin panel
- ✅ Seller dashboard

**Happy Shopping & Selling!** 🛒✨
