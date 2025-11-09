import java.util.*;

class Customer {
    private static int deposit;

    public void deposit(int sum, Scanner sc) {
        if(deposit == 0 || deposit < sum) {
            System.out.println("입금액을 입력해주세요.");
            deposit = sc.nextInt();
            System.out.println("입금이 완료되었습니다.");
        }
        if (deposit < 0) {
            System.out.println("입금액은 음수가 될 수 없습니다.");
            return;
        }
        payment(sum);
    }

    public void payment(int sum) {
        for (int i = 0; i < Product.index; i++) {
            if (!Product.isValid[i]) {
                sum += Product.products[i].getPrice();
                deposit -= sum;
            }
            Product.isValid[i] = true;
        }
        System.out.println("결제에 성공했습니다. 남은 금액은 " + deposit + "원 입니다.");
    }

    public void order(Product[] product, Scanner sc) {
        for (int i = 0; i < product.length; i++) {
            System.out.println(product[i].printOrderList());
        }

        System.out.println("어느 물건을 주문 할 건가요? 물건 번호를 입력해주세요.");
        int select = sc.nextInt();
        for (int i = 0; i < product.length; i++) {
            if (select == product[i].getNumber()) {
                orderList(product[i]);
            }
        }
    }
    public void orderList(Product product) {
        if (Product.index < Constants.MAX_COUNT) {
            Product.products[Product.index++] = product;
            System.out.println("해당 번호의 물건이 상품 목록에 추가 되었습니다.");
        } else {
            System.out.println("더 이상 상품을 추가할 수 없습니다.");
        }
    }

    public void productList(){
        for(int i = 0; i < Product.index; i++){
            System.out.println(Product.products[i].printOrderList());
        }
    }

    public void deleteProductList(Scanner sc) {
        String productName = sc.next();
        boolean isDeleteTrue = false;
        for (int i = 0; i < Product.index; i++) {
            if (Product.products[i].getName().equals(productName)) {
                for (int j = i; j < Product.index - 1; j++) {
                    Product.products[j] = Product.products[j + 1];
                }
                Product.products[Product.index] = null;
                Product.index--;
                isDeleteTrue = true;
                System.out.println("상품 삭제가 완료되었습니다.");
                break;
            }
        }
        if(!isDeleteTrue){
            System.out.println("삭제할 항목이 없습니다.");
        }
    }
}

class Constants {
    final static int MAX_COUNT = 10;
}

class Product {
    private int number;
    private String name;
    private int price;
    static boolean[] isValid = new boolean[Constants.MAX_COUNT];
    static Product[] products = new Product[Constants.MAX_COUNT];
    static int index;

    public Product (int number, String name, int price) {
       this.number = number;
       this.name = name;
       this.price = price;
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String printOrderList() {
        return "번호: " + number + ", 이름: " + this.getName() + ", 가격: " + price + "원";
    }

}

class Coffee extends Product {

    public Coffee(int number, String name, int price) {
        super(number, name, price);
    }
}

class Book extends Product {

    public Book(int number, String name, int price) {
        super(number, name, price);
    }
}

class Phone extends Product {

    public Phone(int number, String name, int price) {
        super(number, name, price);
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product[] product = {new Coffee(1, "커피", 3000),
                new Book(2, "책", 10000),
                new Phone(3, "핸드폰", 1000000),
                };
        Customer customer = new Customer();
        while (true) {
            System.out.println("1. 상품 주문하기");
            System.out.println("2. 입금/결제하기");
            System.out.println("3. 상품 목록 조회하기");
            System.out.println("4. 상품 목록 제거하기");
            System.out.println("5. 종료");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    customer.order(product, sc);
                    break;
                case 2:
                    customer.deposit(0, sc);
                    break;
                case 3:
                    customer.productList();
                    break;
                case 4:
                    customer.deleteProductList(sc);
                    break;
                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("다시 올바른 번호를 입력해주세요.");
            }
        }
    }
}
