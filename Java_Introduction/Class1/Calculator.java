//public: 접근 제어자로 해당 메서드가 어디에서든 접근 가능
//static: 메서드가 클래스에 속한다는 의미 즉 객체를 생성하지 않고도 메서드 사용가능 만일 객체를 생성해서 사용할 거라면 static이 필요없음
import java.util.*;

public class Calculator {
    public static int add(int number1, int number2) {
        return number1 + number2;
    }

    public static int subtract(int number1, int number2) {
        return number1 - number2;
    }

    public static int multiply(int number1, int number2) {
        return number1 * number2;
    }

    public static double divide(int number1, int number2) {
        if (number2 == 0) {
            System.out.println("0으로 나눌 수 없습니다.");
            return 0;
        } else {
            return (double) number1 / number2;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("첫 번째 숫자를 입력해 주세요.: ");
        int num1 = sc.nextInt();
        System.out.print("연산자(+, -, *, /)를 입력해 주세요.: ");
        String mark = sc.next();
        System.out.print("두 번째 숫자를 입력해 주세요.:");
        int num2 = sc.nextInt();
        switch (mark) {
            case "+":
                System.out.println(add(num1, num2));
                break;
            case "-":
                System.out.println(subtract(num1, num2));
                break;
            case "*":
                System.out.println(multiply(num1, num2));
                break;
            default:
                System.out.println(divide(num1, num2));
                break;
        }
    }
}
