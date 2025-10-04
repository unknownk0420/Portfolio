interface Store {
    void processOrder(Customer customer, Product product);

    void processPayment(int amount);

    void orderInfo(Product product);
}

interface Product {
    String getProductName();

    int getProductPrice();

    int getProductQuantity();
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
        store.processPayment(product.getProductPrice() * product.getProductQuantity());
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
        System.out.println("주문 내역: " + product.getProductName() + " * " + product.getProductQuantity() + " = " + product.getProductPrice() * product.getProductQuantity() + "원");
    }
}

class Coffee implements Product {
    private int price;
    private int quantity;

    public Coffee(int price, int quantity){
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String getProductName() {
        return "커피";
    }

    @Override
    public int getProductPrice() {
        return price;
    }

    @Override
    public int getProductQuantity() {
        return quantity;
    }
}

class Sandwich implements Product {
    private int price;
    private int quantity;

    public Sandwich (int price, int quantity){
        this.price = price;
        this.quantity = quantity;
    }

    @Override
    public String getProductName() {
        return "샌드위치";
    }

    @Override
    public int getProductPrice() {
        return price;
    }

    @Override
    public int getProductQuantity() {
        return quantity;
    }
}

public class Main {
    public static void main(String[] args) {
        SimpleStore simpleStore = new SimpleStore();
        Product coffee = new Coffee(5000, 1);
        Product sandwich = new Sandwich(3000, 2);
        Customer customer = new Customer("Alice");
        customer.setStore(simpleStore);
        customer.printInfo(coffee);
        System.out.println("----------");
        customer.printInfo(sandwich);
    }
}
