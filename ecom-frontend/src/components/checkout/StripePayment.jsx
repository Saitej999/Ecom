import { Alert, AlertTitle, Skeleton } from '@mui/material'
import { Elements } from '@stripe/react-stripe-js';
import { loadStripe } from '@stripe/stripe-js';
import React, { useEffect } from 'react'
import { useDispatch, useSelector } from 'react-redux'
import PaymentForm from './PaymentForm';
import { createStripePaymentSecret } from '../../store/actions';

const stripePromise = loadStripe(import.meta.env.VITE_STRIPE_PUBLISHABLE_KEY);

const StripePayment = () => {
  const dispatch = useDispatch();
  const { clientSecret } = useSelector((state) => state.auth);
  const { totalPrice } = useSelector((state) => state.carts);
  const { isLoading, errorMessage } = useSelector((state) => state.errors);
  const { user, selectedUserCheckoutAddress } = useSelector((state) => state.auth);

  useEffect(() => {
    console.log('StripePayment useEffect - clientSecret:', clientSecret);
    console.log('User:', user);
    console.log('Selected address:', selectedUserCheckoutAddress);
    console.log('Total price:', totalPrice);
    
    if (!clientSecret && user && selectedUserCheckoutAddress && totalPrice > 0) {
    const sendData = {
      amount: Number(totalPrice) * 100,
      currency: "usd",
      email: user.email,
      name: `${user.username}`,
      address: selectedUserCheckoutAddress,
      description: `Order for ${user.email}`,
      metadata: {
        test: "1"
      }
    };
      console.log('Creating Stripe payment secret with data:', sendData);
      dispatch(createStripePaymentSecret(sendData));
    }
  }, [clientSecret, user, selectedUserCheckoutAddress, totalPrice]);

  if (isLoading) {
    return (
      <div className='max-w-lg mx-auto'>
        <Skeleton />
      </div>
    )
  }


  return (
    <>
      {clientSecret && (
        <Elements stripe={stripePromise} options={{ clientSecret }}>
          <PaymentForm clientSecret={clientSecret} totalPrice={totalPrice} />
        </Elements>
      )}
    </>
  )
}

export default StripePayment