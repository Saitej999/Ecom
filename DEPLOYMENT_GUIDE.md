# 🚀 eCommerce Application Deployment Guide

## Overview
This guide will help you deploy your eCommerce application with:
- **Frontend**: React app on Vercel
- **Backend**: Spring Boot on Railway
- **Database**: PostgreSQL on Railway

---

## 🎯 Step 1: Deploy Backend to Railway

### 1.1 Create Railway Account
1. Go to [railway.app](https://railway.app)
2. Sign up with GitHub account
3. Create new project

### 1.2 Deploy Backend
1. Connect your GitHub repository: `Saitej999/Ecom`
2. Select the `sb-ecom` folder as root directory
3. Railway will automatically detect the Java application

### 1.3 Add Environment Variables in Railway
```
DATABASE_URL=postgresql://username:password@host:port/database
DB_USERNAME=your_db_username
DB_PASSWORD=your_db_password
JWT_SECRET=mySecretKey123912738aopsgjnspkmndfsopkvajoirjg94gf2opfng2moknm
STRIPE_SECRET_KEY=your_stripe_secret_key_here
FRONTEND_URL=https://your-vercel-app.vercel.app
BACKEND_URL=https://your-railway-app.railway.app
SPRING_PROFILES_ACTIVE=prod
```

### 1.4 Add PostgreSQL Database
1. In Railway dashboard, click "Add Database"
2. Select PostgreSQL
3. Railway will automatically set DATABASE_URL

---

## 🎯 Step 2: Deploy Frontend to Vercel

### 2.1 Create Vercel Account
1. Go to [vercel.com](https://vercel.com)
2. Sign up with GitHub account

### 2.2 Deploy Frontend
1. Import project from GitHub: `Saitej999/Ecom`
2. Select `ecom-frontend` as root directory
3. Framework preset: Vite
4. Build command: `npm run build`
5. Output directory: `dist`

### 2.3 Add Environment Variables in Vercel
```
VITE_BACK_END_URL=https://your-railway-app.railway.app
VITE_FRONTEND_URL=https://your-vercel-app.vercel.app
VITE_STRIPE_PUBLISHABLE_KEY=your_stripe_publishable_key_here
```

---

## 🎯 Step 3: Final Configuration

### 3.1 Update CORS Settings
Update your backend's `application-prod.properties` with the actual Vercel URL:
```
frontend.url=https://your-actual-vercel-url.vercel.app
```

### 3.2 Test the Application
1. Visit your Vercel URL
2. Test user registration/login
3. Test product browsing
4. Test cart functionality
5. Test checkout with Stripe

---

## 📋 Test Accounts

- **Regular User**: `user1` / `password1`
- **Seller**: `seller1` / `password2`
- **Admin**: `admin` / `adminPass`

---

## 🔧 Troubleshooting

### CORS Issues
If you get CORS errors, ensure:
1. Frontend URL is correctly set in backend environment
2. Backend URL is correctly set in frontend environment

### Database Issues
1. Check DATABASE_URL is correctly formatted
2. Ensure PostgreSQL service is running on Railway

### Stripe Issues
1. Verify both publishable and secret keys are set
2. Ensure return URLs match your deployed frontend URL

---

## 📞 Support

If you encounter issues:
1. Check Railway logs for backend errors
2. Check Vercel function logs for frontend issues
3. Verify all environment variables are set correctly
