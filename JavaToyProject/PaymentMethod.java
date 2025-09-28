class PaymentMethod {

    public void payment(String name, int amount) {
        System.out.println(name + "로 " + amount + "원 결제 완료");
    }
}

class CreditCard extends PaymentMethod {

    @Override
    public void payment(String name, int amount) {
        System.out.println(name + "로 " + amount + "원 결제 완료");
    }
}

class PayPal extends PaymentMethod {

    @Override
    public void payment(String name, int amount) {
        System.out.println(name + "로 " + amount + "원 결제 완료");
    }

}

class Pay extends PaymentMethod {

    @Override
    public void payment(String name, int amount) {
        System.out.println(name + "로 " + amount + "원 결제 완료");
    }

}
public class Main {
    public static void main(String[] args) {
        PaymentMethod paymentMethod1 = new CreditCard();
        PaymentMethod paymentMethod2 = new PayPal();
        PaymentMethod paymentMethod3 = new Pay();

        paymentMethod1.payment("신용카드", 5000);
        paymentMethod2.payment("PayPal", 7500);
        paymentMethod3.payment("네이버페이", 10000);
    }
}
