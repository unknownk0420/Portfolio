import java.util.*;

class Constants {
    public final static int MAX_COUNT = 100;
    public final static int MAX_PRODUCT_INFO = 2;
}

class Setting {

    private String product;
    private int price;
    private String productName;

    public Setting(String product, int price, String productName) {
        this.product = product;
        this.price = price;
        this.productName = productName;
    }

    public String getProduct() {
        return this.product;
    }

    public void productInfo(int number) {
        System.out.println("상품번호: " + (number + 1) + ", 상품명: " + getProduct());
    }

    @Override
    public String toString() {
        return "상품명: " + this.productName + ", 가격: " + this.price;
    }
}

class ShoppingMall {

    private Setting[] settings = new Setting[Constants.MAX_COUNT];
    private Setting[][] multiArraySettings = new Setting[Constants.MAX_COUNT][Constants.MAX_PRODUCT_INFO];
    private String[] products = new String[Constants.MAX_COUNT];
    private int count = 0;
    private int cnt = 0;

    public void bookMark(Setting setting) {
        if (count < Constants.MAX_COUNT) {
            settings[count++] = setting;
            System.out.println(setting.getProduct() + "를 즐겨찾기에 추가하였습니다.");
        } else {
            System.out.println("더 이상 즐겨찾기에 추가 할 수 없습니다.");
        }
    }

    public void bookMarkManagementList(String product, Setting[] setting, Scanner sc) {
        for (int i = 0; i < count; i++) {
            settings[i].productInfo(i);
        }
        if (cnt < Constants.MAX_COUNT) {
            products[cnt] = product;
            for (int i = 0; i < setting.length && i < Constants.MAX_PRODUCT_INFO; i++) {
                multiArraySettings[cnt][i] = setting[i];
            }
            System.out.println("상품이 저장되었습니다.");
            cnt++;
        } else {
            System.out.println("더 이상 상품을 저장할 수 없습니다.");
        }

        System.out.println("즐겨찾기 된 상품을 바로 조회하려면 0번을 누르세요:");
        int number = sc.nextInt();
        if (number == 0) {
            System.out.println("조회하려는 상품명을 입력해 주세요:");
            String productName = sc.next();
            for (int i = 0; i < cnt; i++) {
                if (productName.equals(products[i])) {
                    for (int j = 0; j < multiArraySettings[i].length; j++) {
                        if (multiArraySettings[i][j] != null) {
                            System.out.println(multiArraySettings[i][j]);
                        }
                    }
                }
            }
        }
    }

    public void deleteBookMark(Scanner sc) {
        System.out.println("즐겨찾기를 삭제할 상품 이름을 입력해주세요.");
        String productName = sc.next();
        boolean found = false;
        for (int i = 0; i < count; i++) {
            if (settings[i].getProduct().equals(productName)) {
                for (int j = i; j < count - 1; j++) {
                    settings[j] = settings[j + 1];
                }
                settings[--count] = null;
                found = true;
                System.out.println("상품이 삭제가 완료되었습니다.");
                break;
            }
        }
        if (!found) {
            System.out.println("해당 상품 이름이 존재하지 않습니다.");
        }
    }
}
public class miniShoppingMall {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ShoppingMall shoppingMall = new ShoppingMall();
        while (true) {
            System.out.println("1. 즐겨찾기 추가 및 조회");
            System.out.println("2. 즐겨찾기 삭제");
            System.out.println("3. 쇼핑몰 종료");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("즐겨찾기에 추가할 상품을 입력하세요.");
                    String product = sc.next();
                    System.out.println("즐겨찾기에 추가할 " + product + "의 가격을 입력하세요.");
                    int price = sc.nextInt();
                    Setting setting = new Setting(product, price, product);
                    shoppingMall.bookMark(setting);
                    Setting[] settings = {
                            new Setting(product, price, product)
                    };
                    shoppingMall.bookMarkManagementList(product, settings, sc);
                    break;
                case 2:
                    shoppingMall.deleteBookMark(sc);
                    break;
                case 3:
                    System.out.println("쇼핑몰을 종료합니다.");
                    return;
            }
        }
    }
}
