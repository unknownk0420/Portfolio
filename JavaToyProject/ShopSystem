class ProductClass {
    private static int productCount;
    private static int totalPrice;
    private static int registerCount;
    private final static double DISCOUNT_RATE = 0.30;
    private final String name;
    private final int count;
    private final int price;

    public ProductClass(String name, int count, int price) {
        this.name = name;
        this.count = count;
        this.price = price;
        totalPrice += price;
        productCount += count;
        registerCount++;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getCount() {
        return count;
    }

    public double getNotebookAmount() {
        return price * DISCOUNT_RATE;
    }

    public double getDiscountPrice() {
        return price - getNotebookAmount();
    }

    public static void showTotalPriceInfo() {
        System.out.println("총 매출: " + totalPrice);
    }

    public void notebookInfo() {
        System.out.println("노트북 할인 후 가격: " + getDiscountPrice() + "원");
        System.out.println("할인 금액: " + getNotebookAmount() + "원 (" + (DISCOUNT_RATE * 100) + "% 할인)");
    }

    public static void showShopInfo() {
        System.out.println("=== JavaShop 매장 상태 ===");
        System.out.println("총 판매 수량: " + productCount + "개");
        System.out.println("총 매출: " + totalPrice + "원");
        System.out.println("등록된 상품 개수: " + registerCount + "개");
    }
}
public class Main {
    public static void main(String[] args) {
        ProductClass notebook = new ProductClass("노트북", 1, 1500000);
        ProductClass mouse = new ProductClass("마우스", 3, 270000);
        ProductClass keyboard = new ProductClass("키보드", 2, 140000);

        System.out.println(notebook.getName() + notebook.getCount() + "개 판매됨 (총 " + notebook.getPrice() + "원)");
        System.out.println(mouse.getName() + mouse.getCount() + "개 판매됨 (총 " + mouse.getPrice() + "원)");
        System.out.println(keyboard.getName() + keyboard.getCount() + "개 판매됨 (총 " + keyboard.getPrice() + "원)");
        System.out.println();
        ProductClass.showTotalPriceInfo();
        notebook.notebookInfo();
        System.out.println();
        ProductClass.showShopInfo();
    }
}
