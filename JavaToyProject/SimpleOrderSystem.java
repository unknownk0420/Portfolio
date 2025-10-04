interface Store {
    void processOrder(Customer customer, Product product);

    void processPayment(int amount);

    void orderInfo(Product product);
}

class Customer {
    private Store store;
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getName() {
        return name;
    }

    public void printInfo(Product product) {
        store.processOrder(this, product);
        store.processPayment(product.getPrice() * product.getQuantity());
        store.orderInfo(product);
    }
}

class SimpleStore implements Store {

    @Override
    public void processOrder(Customer customer, Product product) {
        System.out.println(customer.getName() + "님이 주문을 시작합니다.");
        System.out.println(product.getProductName() + "가 주문이 완료되었습니다.");
    }

    @Override
    public void processPayment(int amount) {
        System.out.println("결제 요청 금액: " + amount + "원");
        System.out.println("결제가 완료되었습니다.");
    }

    @Override
    public void orderInfo(Product product) {
        System.out.println("주문 내역: " + product.getProductName() + " * " + product.getQuantity() + " = " + product.getPrice() * product.getQuantity() + "원");
    }
}

class Product {
    private int price;
    private int quantity;
    private String productName;

    public Product(int price, int quantity, String productName) {
        this.price = price;
        this.quantity = quantity;
        this.productName = productName;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getProductName() {
        return productName;
    }
}
public class Main {
    public static void main(String[] args) {
        Product product = new Product(5000, 1, "커피");
        Customer customer = new Customer("Alice");
        SimpleStore simpleStore = new SimpleStore();
        customer.setStore(simpleStore);
        customer.printInfo(product);
    }
}
