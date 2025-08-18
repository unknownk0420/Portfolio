public class ShoppingMall_Order_System {
    public static void main(String[] args) {
        ShoppingMall_Order_System_Class1 item1 = new ShoppingMall_Order_System_Class1("무선 마우스", 25000, 2);
        ShoppingMall_Order_System_Class1 item2 = new ShoppingMall_Order_System_Class1("USB-C 케이블", 10000, 3);
        ShoppingMall_Order_System_Class1 item3 = new ShoppingMall_Order_System_Class1("노트북 거치대", 35000, 1);

        ShoppingMall_Order_System_Class2 s = new ShoppingMall_Order_System_Class2();

        s.setPrint_Order_Detail(item1);
        s.setPrint_Order_Detail(item2);
        s.setPrint_Order_Detail(item3);

        s.setPrint_Total_Order_Price();

    }
}
