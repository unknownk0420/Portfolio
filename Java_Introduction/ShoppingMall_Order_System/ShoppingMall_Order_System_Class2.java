public class ShoppingMall_Order_System_Class2 {
    ShoppingMall_Order_System_Class1[] items = new ShoppingMall_Order_System_Class1[10];
    int count = 0;

    public void setPrint_Order_Detail(ShoppingMall_Order_System_Class1 s) {
        items[count++] = s;
    }

    public int getPrint_Total_Order_Price() {
        int result = 0;
        for (int i = 0; i < count; i++) {
            result += items[i].getTotal();
        }
        return result;
    }

    public void setPrint_Total_Order_Price() {
        System.out.println("주문 내역: ");
        for (int i = 0; i < count; i++) {
            items[i].Order_Detail();
        }
        System.out.println("총 주문 금액: " + getPrint_Total_Order_Price() + "원");
    }
}
