package hotel_managment_system.payment;

public class PaymentFactory {
    public static Payment getPaymentMethod(PaymentType type) {
//        switch (type) {
//            case PaymentType.CREDIT_CARD:
                return new CreditCardPayment();
//            case PaymentType.CASH:
//                return new CashPayment();
//            case PaymentType.NET_BANKING:
//                return new NetBankingPayment();
//            default:
//                throw new IllegalArgumentException("Invalid payment type: " + type);
//        }
    }
}
