import java.util.*;

class Car {
    String modelName;
    String company;
    int year;
    double price;

    public Car(String modelName, String company) {
        this(modelName, company, 0, 0.0);
    }

    public Car(String modelName, String company, int year) {
        this(modelName, company, year, 0.0);

    }

    public Car(String modelName, String company, int year, double price) {
        this.modelName = modelName;
        this.company = company;
        this.year = year;
        this.price = price;
    }

    public void printCarList() {
        System.out.println("모델명: " + modelName);
        System.out.println("제조사: " + company);
        System.out.println("연식: " + year);
        System.out.println("가격: " + price);
    }

}
public class Car_Management_System {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("등록할 자동차 수를 입력하세요:");
        int count = sc.nextInt();
        Car[] cars = new Car[count];
        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + "번째 자동차 정보 입력");
            System.out.println("모델명:");
            String modelName = sc.next();
            System.out.println("제조사:");
            String company = sc.next();
            System.out.println("연식 (숫자 입력, 없으면 0):");
            int year = sc.nextInt();
            System.out.println("가격 (만원 단위, 없으면 0.0):");
            double price = sc.nextDouble();
            if (year == 0 && price == 0.0) {
                cars[i] = new Car(modelName, company);
            } else if (price == 0.0) {
                cars[i] = new Car(modelName, company, year);
            } else {
                cars[i] = new Car(modelName, company, year, price);
            }
        }
        System.out.println("=== 등록된 자동차 목록 ===");
        for (int i = 0; i < count; i++) {
            cars[i].printCarList();
        }
        sc.close();
    }
}
