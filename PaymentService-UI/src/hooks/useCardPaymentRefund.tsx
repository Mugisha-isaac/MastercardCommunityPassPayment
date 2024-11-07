import { useState } from "react";
import { v4 } from "uuid";
import axios from "axios";
import { IPaymentRefundFormData } from "@/types/payment-refund-form";

export const useCardPaymentRefund = (props: IPaymentRefundFormData) => {
  const [refundPending, setRefundPending] = useState(false);
  const [refundError, setRefundError] = useState<string | null>(null);
  const [refundSuccess, setRefundSuccess] = useState<boolean>(false);

  const { orderId, amount } = props;

  const handleRefund = async () => {
    setRefundPending(true);

    const transactionId = v4();
    const merchantId = process.env.NEXT_PUBLIC_MarchantId;
    const baseUrl = process.env.NEXT_PUBLIC_BASE_URL;

    const refundRequest = {
      apiOperation: "REFUND",
      transaction: {
        amount: amount,
        currency: "USD",
      },
    };

    await axios
      .put(
        `${baseUrl}/refund/merchant/${merchantId}/order/${orderId}/transaction/${transactionId}`,
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
      }).finally(() => {
        setRefundPending(false);
      });
  };

  return { refundPending, refundError, refundSuccess, handleRefund };
};
