import java.util.Scanner;

class Constants {
    protected final static int MAX_ORDER_COUNT = 100;
}

class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }
}
class OrderInfo {
    //여러 객체를 받기 위해 배열을 씀
    private Product[] products = new Product[Constants.MAX_ORDER_COUNT];
    private int count = 0;

    public void orderList(Product product) {
        if (count < products.length) {
            products[count++] = product;
        } else {
            System.out.println("주문 내역이 가득 찼습니다.");
        }
    }

    public boolean orderList() {
        return count > 0;
    }

    public void clearOrderList() {
        count = 0;
        //새 객체로 초기화
        products = new Product[Constants.MAX_ORDER_COUNT];
    }

    public void printOrderList() {
        if (count == 0) {
            System.out.println("주문 내역이 없습니다.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "번째 주문 내역은 " + products[i].getName() + ", " + products[i].getPrice() + "입니다.");
        }
    }

    //Product의 참조 변수의 값을 함수 호출을 통해 가져옴
    public void processOrder(Scanner sc, Product product) {
        System.out.println("몇 개를 주문할 건가요?");
        int numberCount = sc.nextInt();
        System.out.println("수량 감소를 할건 가요? 없으면 0을 입력하세요.");
        int numberCountMinus = sc.nextInt();
        numberCount -= numberCountMinus;
        if (numberCount < 0) {
            System.out.println("0개 미만으로는 주문할 수 없습니다.");
            return;
        }
        int totalPrice = product.getPrice() * numberCount;
        System.out.println(product.getName() + "를 " + numberCount + "개 주문했습니다. 가격은 " + product.getPrice() + "입니다. 총 가격은 " + totalPrice + "입니다.");
        for (int i = 0; i < numberCount; i++) {
            orderList(product);
        }
    }
}

public class Kiosk {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Product[] products = new Product[4];
        OrderInfo orderInfo = new OrderInfo();
        //여러 개의 객체를 입력하기 위해 배열을 씀
        products[0] = new Product("아메리카노", 1500);
        products[1] = new Product("딸기 스무디", 3800);
        products[2] = new Product("초코 프라페", 4000);
        products[3] = new Product("망고 스무디", 3800);
        while (true) {
            System.out.println("1. 메뉴 선택");
            System.out.println("2. 결제");
            System.out.println("3. 종료");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("1. 아메리카노");
                    System.out.println("2. 딸기 스무디");
                    System.out.println("3. 초코 프라페");
                    System.out.println("4. 망고 스무디");
                    System.out.println("메뉴를 골라 주세요.(1~4중 클릭):");
                    int select = sc.nextInt();
                    if (select >= 1 && select <= 4) {
                        orderInfo.processOrder(sc, products[select - 1]);
                        orderInfo.printOrderList();
                    } else {
                        System.out.println("다시 선택 해주세요.");
                    }
                    break;
                case 2:
                    if (orderInfo.orderList()) {
                        System.out.println("결제가 되었습니다.");
                        orderInfo.clearOrderList();
                    } else {
                        System.out.println("주문 내역을 추가 해주세요");
                    }
                    break;
                case 3:
                    System.out.println("키오스크를 종료합니다.");
                    return;
                default:
                    System.out.println("1~5중 하나를 눌려주세요.");
                    break;
            }
        }
    }
}
