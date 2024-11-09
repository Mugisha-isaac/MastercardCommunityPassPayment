import { v4 } from "uuid";
import axios from "axios";
import { IPaymentRequest } from "@/types/payment-request";
import { useState } from "react";
import { IPaymentFormData } from "@/types/payment-form-data";

export const useCardPayment = (props: IPaymentFormData) => {
  const [isPaymentLoading, setIsPaymentLoading] = useState(false);
  const [paymentError, setPaymentError] = useState<string | null>(null);
  const [paymentSuccess, setPaymentSuccess] = useState<boolean>(false);

  const { cardNumber, cardCVV, cardExpiration, amount } = props;
  const handlePayment = async () => {
    setIsPaymentLoading(true);

    const orderId = v4();
    const transactionId = v4();
    const merchantId = process.env.NEXT_PUBLIC_MarchantId;
    const baseUrl = process.env.NEXT_PUBLIC_BASE_URL;

    const paymentRequest: IPaymentRequest = {
      apiOperation: "PAY",
      order: {
        amount: amount,
        currency: "USD",
      },
      sourceOfFunds: {
        type: "CARD",
        provided: {
          card: {
            number: cardNumber,
            expiry: {
              month: cardExpiration.split("-")[1].slice(-1),
              year: cardExpiration.split("-")[0].slice(-2),
            },
            securityCode: cardCVV,
          },
        },
      },
    };

    await axios
      .put(
        `${baseUrl}/pay/merchant/${merchantId}/order/${orderId}/transaction/${transactionId}`,
        paymentRequest
      )
      .then(() => {
        setIsPaymentLoading(false);
        setPaymentSuccess(true);
        setPaymentError(null);
      })
      .catch((error) => {
        console.error(error);
        setIsPaymentLoading(false);
        setPaymentError(error.response.data.message);
        setPaymentSuccess(false);
      }).finally(() => {
        setIsPaymentLoading(false);
      });
  };
  return {
    handlePayment,
    isPaymentLoading,
    paymentError,
    paymentSuccess,
  };
};
