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
