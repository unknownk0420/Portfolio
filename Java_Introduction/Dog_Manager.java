//메서드에 매개 변수가 없는 경우는 메서드 외부에 변수를 선언 했기 때문에 생긴다.
//하나의 클래스로부터 여러 개의 인스턴스를 생성할 수 있고 각 인스턴스는 자신만의 인스턴스 변수(멤버 변수)를 가진다.
//참조 변수는 클래스형 데이터 타입으로 생성된 인스턴스를 가리킨다. 즉 클래스 형의 변수 이름이다.
import java.util.*;

public class Dog_Manager {
    String dogName;
    int dogAge;
    String dogBreed;

    public void register() {
        System.out.println("이름: " + dogName);
        System.out.println("나이: " + dogAge);
        System.out.println("견종: " + dogBreed);
    }

    public void bark() {
        System.out.println(dogName + "가 멍멍 짖습니다!");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Dog_Manager[] dogs = new Dog_Manager[3];
        int count = 0;
        while (true) {
            System.out.println("1. 강아지 등록");
            System.out.println("2. 강아지 정보 보기");
            System.out.println("3. 강아지 짖기");
            System.out.println("4. 종료");
            int number = sc.nextInt();
            switch (number) {
                case 1:
                    if (dogs.length <= count) {
                        System.out.println("더 이상 등록 할 수 없습니다.");
                        continue;
                    }
                    Dog_Manager dog = new Dog_Manager();
                    System.out.print("이름 입력: ");
                    dog.dogName = sc.next();
                    System.out.print("나이 입력: ");
                    dog.dogAge = sc.nextInt();
                    System.out.println("견종 입력: ");
                    dog.dogBreed = sc.next();
                    dogs[count++] = dog;
                    System.out.println("강아지 등록 완료!");
                    break;
                case 2:
                    if (count == 0) {
                        System.out.println("등록된 강아지가 없습니다.");
                    }
                    for (int i = 0; i < count; i++) {
                        System.out.println((i + 1) + "번 강아지");
                        dogs[i].register();
                    }
                    break;
                case 3:
                    if (count == 0) {
                        System.out.println("등록된 강아지가 없습니다.");
                    }
                    for (int i = 0; i < count; i++) {
                        dogs[i].bark();
                    }
                    break;
                case 4:
                    System.out.println("프로그램을 종료합니다.");
                    sc.close();
                    return;
                default:
                    System.out.println("잘못된 선택입니다.");
                    break;
            }
        }
    }
}
