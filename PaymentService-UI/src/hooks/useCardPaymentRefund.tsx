import { useState } from "react";
import { v4 } from "uuid";
import axios from "axios";

interface ICardPaymentRefundProps {
  orderId: string;
  amount: string;
}

export const useCardPaymentRefund = (props: ICardPaymentRefundProps) => {
  const [refundPending, setRefundPending] = useState(false);
  const [refundError, setRefundError] = useState<string | null>(null);
  const [refundSuccess, setRefundSuccess] = useState<boolean>(false);

  const { orderId, amount } = props;

  const handleRefund = async () => {
    setRefundPending(true);

    const transactionId = v4();
    const merchantId = process.env.NEXT_PUBLIC_MarchantId;

    const refundRequest = {
      apiOperation: "REFUND",
      order: {
        amount: amount,
        currency: "USD",
      },
    };

    await axios
      .put(
        `http://localhost:8080/api/v1/mastercard/refund/merchant/${merchantId}/order/${orderId}/transaction/${transactionId}`,
        refundRequest
      )
      .then(() => {
        setRefundPending(false);
        setRefundSuccess(true);
        setRefundError(null);
      })
      .catch((error) => {
        setRefundPending(false);
        setRefundError(error.message);
        setRefundSuccess(false);
      });
  };

  return { refundPending, refundError, refundSuccess, handleRefund };
};
