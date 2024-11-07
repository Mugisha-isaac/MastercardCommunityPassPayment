"use client";

import { useEffect, useState } from "react";
import toast, { Toaster } from "react-hot-toast";
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
import { Label } from "@radix-ui/react-label";

import { useCardPayment } from "@/hooks/useCardPayment";
import { IPaymentFormData } from "@/types/payment-form-data";

export default function Pay() {
  const [formData, setFormData] = useState<IPaymentFormData>({
    cardNumber: "",
    cardHolder: "",
    cardExpiration: "",
    cardCVV: "",
    amount: 0,
  });

  const { handlePayment, isPaymentLoading, paymentError, paymentSuccess } =
    useCardPayment(formData);

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    await handlePayment();
  };

  useEffect(() => {
    if (paymentError !== null && paymentError !== "" && !isPaymentLoading && !paymentSuccess) {
      toast.error(paymentError);
    } else if (paymentSuccess && !isPaymentLoading && paymentError === null) {
      toast.success("Payment successful");
    } else if (isPaymentLoading) {
      const toastId = toast.loading("Processing payment...");
      return () => {
        toast.dismiss(toastId);
      };
    }
  }, [isPaymentLoading, paymentError, paymentSuccess]);

  return (
    <div className="items-center justify-items-center min-h-screen p-10">
      <Toaster position="top-right" reverseOrder={false} />
      <main className="flex items-center justify-center  gap-10 ">
        <Card className="w-[400px]">
          <CardHeader>
            <CardTitle>Checkout</CardTitle>
            <CardDescription>
              Enter your payment details to complete the purchase.
            </CardDescription>
          </CardHeader>
          <CardContent>
            <form>
              <div className="grid w-full items-center gap-4">
                <div className="flex flex-col space-y-1.5">
                  <Label htmlFor="name">Name</Label>
                  <Input
                    id="name"
                    placeholder="Enter your name"
                    onChange={(e) =>
                      setFormData({ ...formData, cardHolder: e.target.value })
                    }
                  />
                </div>
                <div className="flex flex-col space-y-1.5">
                  <Label htmlFor="cardNumber">Card Number</Label>
                  <Input
                    id="cardNumber"
                    type="number"
                    placeholder="Enter your card number"
                    onChange={(e) =>
                      setFormData({ ...formData, cardNumber: e.target.value })
                    }
                  />
                </div>
                <div>
                  <Label htmlFor="amount">Amount</Label>
                  <Input
                    id="amount"
                    type="number"
                    placeholder="Enter the amount"
                    onChange={(e) =>
                      setFormData({
                        ...formData,
                        amount: parseInt(e.target.value),
                      })
                    }
                  />
                </div>
                <div className="flex flex-row gap-5">
                  <div>
                    <Label htmlFor="expiry">Expiry Date</Label>
                    <Input
                      id="expiry"
                      type="date"
                      placeholder="MM/YY"
                      onChange={(e) =>
                        setFormData({
                          ...formData,
                          cardExpiration: e.target.value,
                        })
                      }
                    />
                  </div>
                  <div>
                    <Label htmlFor="cvc">CVC</Label>
                    <Input
                      id="cvc"
                      type="number"
                      placeholder="Enter your CVC"
                      onChange={(e) =>
                        setFormData({ ...formData, cardCVV: e.target.value })
                      }
                    />
                  </div>
                </div>
              </div>
            </form>
          </CardContent>
          <CardFooter className="flex justify-between">
            <Button variant="outline">Cancel</Button>
            <Button onClick={handleSubmit}>Pay</Button>
          </CardFooter>
        </Card>
      </main>
    </div>
  );
}
