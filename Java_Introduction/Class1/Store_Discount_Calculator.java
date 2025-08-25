class Discount {
    //상수는 객체가 필요없고 여러 개의 값을 가져서는 안된다.
    public static final double VAT_RATE = 0.10;
    public static final int FLAT_DISCOUNT = 2000;
    public static final double MEMBER_DISCOUNT_RATE = 0.05;
}
class Product {
    //private는 접근 제어자로 외부에서 접근하지 못하게 하는 역할을 함 단, 같은 클래스 내부에서는 접근 가능하다.
    private String model;
    private int price;

    public Product(String model, int price) {
        this.model = model;
        this.price = price;
    }

    public String getModel() {
        return this.model;
    }

    public int getPrice() {
        return this.price;
    }
}
class Service {
    public int finalPrice(Product product, boolean isMember) {
        int price = product.getPrice();//매개변수로 전달된 Product 객체에서 가격을 가져와 price 변수에 저장
        price -= Discount.FLAT_DISCOUNT;//상수는 static의 특징을 가지므로 클래스명으로 참조한다.
        if (isMember) {
            price -= (int) (product.getPrice() * Discount.MEMBER_DISCOUNT_RATE);
        }
        price += (int) (price * Discount.VAT_RATE);
        return price;
    }
}
public class Store_Discount_Calculator {
    public static void main(String[] args) {
        Product product = new Product("무선 이어폰", 50000);//객체를 생성하면서 동시에 생성자의 매개 변수를 초기화 한다.
        Service service = new Service();
        System.out.println("제품명: " + product.getModel());
        System.out.println("원가: " + product.getPrice());
        //Service 클래스의 finalPrice 메서드를 호출하면서 메서드의 매개 변수를 초기화하고 있음 이때 product는 new Product("무선 이어폰", 50000)을 의미 즉 객체(인스턴스)를 참조하는 것을 의미함
        System.out.println("회원 최종 가격: " + service.finalPrice(product, true));
        System.out.println("비회원 최종 가격: " + service.finalPrice(product, false));
    }
}
