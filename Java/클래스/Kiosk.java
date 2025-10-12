import java.util.*;

class Constants {
    final static int MAX_COUNT = 100;
    final static int MAX_ORDER = 100;
}

class Menu {
    private String name;
    private int price;

    public Menu(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "메뉴 이름: " + name + ", 가격: " + price + "원";
    }

}

class MenuItem {
    private Menu[] menu = new Menu[Constants.MAX_COUNT];
    private int count = 0;

    public void addMenu(Menu menu) {
        if (count < this.menu.length) {
            this.menu[count++] = menu;
        } else {
            System.out.println("더이상 메뉴를 추가할 수 없습니다.");
        }
    }

    public void printAddMenu() {
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". 메뉴 이름: " + menu[i].getName() + ", 가격: " + menu[i].getPrice() + "원");
        }
    }

    public Menu getMenuArray(int index) {
        if (index >= 0 && index < count) {
            return menu[index];
        }
        return null;
    }

    public boolean isDuplication(String name) {
        for (int i = 0; i < count; i++) {
            if (menu[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

}

class Cart {
    private Menu[] carts = new Menu[Constants.MAX_ORDER];
    private int count = 0;

    public void orderList(MenuItem menuItem, int number) {
        if (number >= 0 && number < carts.length && menuItem.getMenuArray(number) != null) {
            carts[count++] = menuItem.getMenuArray(number);
        } else {
            System.out.println("잘못된 메뉴 번호입니다.");
        }
    }

    public void printOrderList() {
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". 메뉴 이름: " + carts[i].getName() + ", 가격: " + carts[i].getPrice() + "원");
        }
    }

    public void deleteOrder(String name) {
        boolean isTrue = false;
        for (int i = 0; i < count; i++) {
            if (carts[i].getName().equals(name)) {
                for (int j = i; j < count - 1; j++) {
                    carts[j] = carts[j + 1];
                }
                carts[count - 1] = null;
                count--;
                System.out.println("해당 주문 목록이 삭제되었습니다.");
                isTrue = true;
                break;
            }
        }
        if (!isTrue) {
            System.out.println("삭제할 목록이 없습니다.");
        }
    }

    public Menu getCartArray(int index) {
        if (index >= 0 && index < count) {
            return carts[index];
        }
        return null;
    }

    public int getCount() {
        return count;
    }

    public void clearCart() {
        for (int i = 0; i < count; i++) {
            carts[i] = null;
        }
        count = 0;
    }

}

class Order {
    public void orderComplete(Cart cart) {
        int total = 0;
        for (int i = 0; i < cart.getCount(); i++) {
            System.out.println(cart.getCartArray(i) + "을(를) 주문을 했습니다.");
            total += cart.getCartArray(i).getPrice();
        }
        System.out.println("총 가격은 " + total + "원 입니다.");
        System.out.println("주문이 완료되었습니다. 장바구니가 초기화 됩니다.");
        cart.clearCart();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        MenuItem menuItem = new MenuItem();
        Cart cart = new Cart();
        Order order = new Order();
        while (true) {
            System.out.println("1. 메뉴 추가");
            System.out.println("2. 메뉴 확인");
            System.out.println("3. 주문 내역");
            System.out.println("4. 주문 내역 삭제");
            System.out.println("5. 주문 완료");
            System.out.println("6. 종료");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    System.out.print("메뉴 이름 입력: ");
                    String name = sc.next();
                    if (menuItem.isDuplication(name)) {
                        System.out.println("이미 존재하는 메뉴입니다.");
                        break;
                    }
                    System.out.print("메뉴 가격 입력: ");
                    int price = sc.nextInt();
                    System.out.print("메뉴 수량 입력: ");
                    Menu menu = new Menu(name, price);
                    menuItem.addMenu(menu);
                    break;
                case 2:
                    menuItem.printAddMenu();
                    System.out.print("주문할 메뉴 번호를 입력해주세요: ");
                    int choice = sc.nextInt();
                    cart.orderList(menuItem, choice - 1);
                    break;
                case 3:
                    if (cart.getCount() == 0) {
                        System.out.println("주문한 항목이 없습니다.");
                    } else {
                        cart.printOrderList();
                    }
                    break;
                case 4:
                    System.out.println("삭제할 메뉴 이름을 입력해주세요.");
                    String n = sc.next();
                    cart.deleteOrder(n);
                    break;
                case 5:
                    order.orderComplete(cart);
                    break;
                case 6:
                    System.out.println("키오스크를 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 번호를 입력해주세요.");
            }
        }
    }
}
