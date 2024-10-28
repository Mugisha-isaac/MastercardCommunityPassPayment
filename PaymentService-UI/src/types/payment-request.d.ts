export interface IPaymentRequest{
    apiOperation: string;
    order: {
        amount: string;
        currency: string;
    };
    sourceOfFunds: {
        type: string;
        provided: {
            card: {
                number: string;
                expiry:{
                    month: string;
                    year: string;
                },
                securityCode: string;
            }
        }
    }
}