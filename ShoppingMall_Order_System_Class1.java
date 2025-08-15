public class ShoppingMall_Order_System_Class1 {
    String item;
    int price;
    int amount;

    public ShoppingMall_Order_System_Class1(String i, int p, int a) {
        item = i;
        price = p;
        amount = a;
    }

    public int getTotal() {
        return price * amount;
    }

    public void Order_Detail() {
        System.out.println(item + " | 가격: " + price + " | 수량: " + amount + " | 합계: " + getTotal() + "원");
    }
}
