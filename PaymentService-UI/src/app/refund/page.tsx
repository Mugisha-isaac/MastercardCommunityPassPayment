"use client";

import { Button } from "@/components/ui/button";
import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { Input } from "@/components/ui/input";
import { useCardPaymentRefund } from "@/hooks/useCardPaymentRefund";
import { IPaymentRefundFormData } from "@/types/payment-refund-form";
import { Label } from "@radix-ui/react-label";
import { useEffect, useState } from "react";
import toast, { Toaster } from "react-hot-toast";

export default function Refund() {
  const [refundFormData, setRefundFormData] = useState<IPaymentRefundFormData>({
    orderId: "",
    amount: 0,
  });

  const { handleRefund, refundPending, refundError, refundSuccess } =
    useCardPaymentRefund(refundFormData);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await handleRefund();
  };

  useEffect(() => {
    if (
      refundError != null &&
      refundError !== "" &&
      !refundPending &&
      !refundSuccess
    ) {
      toast.error(refundError);
    } else if (refundSuccess && !refundPending && refundError === null) {
      toast.success("Refund successful");
    } else if (refundPending) {
      const toastId = toast.loading("Processing refund...");
      return () => {
        toast.dismiss(toastId);
      };
    }
  }, [refundPending, refundError, refundSuccess]);

  return (
    <div className="grid grid-rows-[20px_1fr_20px] items-center justify-items-center min-h-screen p-8 pb-20 gap-16 sm:p-20 font-[family-name:var(--font-geist-sans)]">
      <Toaster position="top-right" reverseOrder={false} />
      <main className="flex items-center justify-center  mt-80 gap-10">
        <Card className="w-[400px]">
          <CardHeader>
            <CardTitle>Refund payment</CardTitle>
            <CardDescription>want to refund your transaction?</CardDescription>
          </CardHeader>
          <CardContent>
            <form>
              <div className="grid w-full items-center gap-4">
                <div className="flex flex-col space-y-1.5">
                  <Label htmlFor="name">Order Id</Label>
                  <Input
                    id="orderId"
                    placeholder="Enter order id"
                    onChange={(e) => {
                      setRefundFormData({
                        ...refundFormData,
                        orderId: e.target.value,
                      });
                    }}
                  />
                </div>
                <div className="flex flex-col space-y-1.5">
                  <Label htmlFor="name">Amount</Label>
                  <Input
                    id="amount"
                    placeholder="Enter amount to refund"
                    onChange={(e) => {
                      setRefundFormData({
                        ...refundFormData,
                        amount: parseInt(e.target.value),
                      });
                    }}
                  />
                </div>
              </div>
            </form>
          </CardContent>
          <CardFooter className="flex justify-between">
            <Button onClick={handleSubmit}>Refund</Button>
          </CardFooter>
        </Card>
      </main>
    </div>
  );
}
