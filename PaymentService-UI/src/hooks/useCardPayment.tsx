import { v4 } from "uuid";
import axios from "axios";
import { IPaymentRequest } from "@/types/payment-request";
import { useState } from "react";

interface ICardPaymentProps {
  cardNumber: string;
  cardHolder: string;
  cardExpiration: string;
  cardCVV: string;
}

export const useCardPayment = (props: ICardPaymentProps) => {
  const [isPaymentLoading, setIsPaymentLoading] = useState(false);
  const [paymentError, setPaymentError] = useState<string | null>(null);
  const [paymentSuccess, setPaymentSuccess] = useState<boolean>(false);

  const { cardNumber, cardCVV, cardExpiration } = props;
  const handlePayment = async () => {
    setIsPaymentLoading(true);

    const orderId = v4();
    const transactionId = v4();
    const merchantId = process.env.NEXT_PUBLIC_MarchantId;

    const paymentRequest: IPaymentRequest = {
      apiOperation: "PAY",
      order: {
        amount: "100",
        currency: "USD",
      },
      sourceOfFunds: {
        type: "CARD",
        provided: {
          card: {
            number: cardNumber,
            expiry: {
              month: cardExpiration.split("-")[1].slice(-1),
              year: cardExpiration.split("-")[0].slice(-2)
            },
            securityCode: cardCVV,
          },
        },
      },
    };

    await axios
      .put(
        `http://localhost:8080/api/v1/mastercard/merchant/${merchantId}/order/${orderId}/transaction/${transactionId}`, paymentRequest
      )
      .then(() => {
        setIsPaymentLoading(false);
        setPaymentSuccess(true);
        setPaymentError(null);
      })
      .catch((error) => {
        setIsPaymentLoading(false);
        setPaymentError(error.message);
        setPaymentSuccess(false);
      });

      setIsPaymentLoading(false);
  };
  return {
    handlePayment,
    isPaymentLoading,
    paymentError,
    paymentSuccess,
  };
};
