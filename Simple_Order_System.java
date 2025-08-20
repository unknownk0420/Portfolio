class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void orderProduct(Store store, Product product, int quantity) {
        store.processOrder(this, product, quantity);
    }

}
class Order {
    private Product product;
    private int quantity;

    public Order(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public int getOrderList() {
        return product.getPrice() * quantity;
    }

    public void summaryOrder() {
        System.out.println("주문 내역: " + quantity + " * " + product.getProduct() + " = " + getOrderList() + "원");
    }

}
class Product {
    private String product;
    private int price;

    public Product(String product, int price) {
        this.product = product;
        this.price = price;
    }

    public String getProduct() {
        return product;
    }

    public int getPrice() {
        return price;
    }


}
class Payment {

    public void requestPrice(int amount) {
        System.out.println("결제 요청 금액: " + amount + "원");
        System.out.println("결제가 완료되었습니다.");
    }
}
class Store {
    private Payment payment;

    public Store() {
        this.payment = new Payment();
    }

    public void processOrder(Customer customer, Product product, int quantity) {
        System.out.println(customer.getName() + "님이 주문을 시작합니다.");
        Order order = new Order(product, quantity);
        order.summaryOrder();
        payment.requestPrice(order.getOrderList());
        System.out.println("주문이 완료되었습니다.");

    }
}
public class Simple_Order_System {
    public static void main(String[] args) {
        Product coffee = new Product("아메리카노", 4500);
        Product sandwich = new Product("샌드위치", 5500);
        Customer customer = new Customer("홍길동");
        Store store = new Store();

        customer.orderProduct(store, coffee, 2);
        customer.orderProduct(store, sandwich, 1);

    }
}
