import java.util.*;

interface Question {
    void generate();
}

class MultiplicationQuestion implements Question {
    private int currentAnswer;

    @Override
    public void generate() {
        int dan = (int) (Math.random() * 8) + 2;
        int num = (int) (Math.random() * 9) + 1;
        currentAnswer = dan * num;
        System.out.println(dan + " * " + num + " = ?");
    }

    public void currentAnswer(int answer) {
        if (currentAnswer == answer) {
            System.out.println("맞았습니다!");
        } else {
            System.out.println("틀렸습니다!");
        }
    }
}

class ReverseQuestion implements Question {
    private int num;
    private int dan;
    private int answer;

    @Override
    public void generate() {
        dan = (int) (Math.random() * 8) + 2;
        num = (int) (Math.random() * 9) + 1;
        answer = dan * num;

        System.out.println("? * " + num + " = " + answer);

    }

    public void danAnswer(int dan) {
        if (dan == answer / num) {
            System.out.println("맞았습니다!");
        } else {
            System.out.println("틀렸습니다!");
        }
    }
}

class Exception {

    public void exceptionCurrentAnswer(Scanner sc, Question[] question) {
        try {
            int answer = sc.nextInt();
            ((MultiplicationQuestion) question[0]).currentAnswer(answer);
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해야됩니다.");
            sc.nextLine();
        }
    }

    public void exceptionDanAnswer(Scanner sc, Question[] question) {
        try {
            int dan = sc.nextInt();
            ((ReverseQuestion) question[1]).danAnswer(dan);
        } catch (InputMismatchException e) {
            System.out.println("숫자를 입력해야됩니다.");
            sc.nextLine();
        }
    }
}

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        Question[] question = {new MultiplicationQuestion(),
                new ReverseQuestion()};
        Exception exception = new Exception();
        while (true) {
            System.out.println("1. 일반 곱셈 문제");
            System.out.println("2. 빈칸 맞추기 곱셈 문제");
            System.out.println("3. 종료");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    question[0].generate();
                    exception.exceptionCurrentAnswer(sc, question);
                    break;
                case 2:
                    question[1].generate();
                    exception.exceptionDanAnswer(sc, question);
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("다시 정확한 숫자를 입력해주세요.");
            }
        }
    }
}
