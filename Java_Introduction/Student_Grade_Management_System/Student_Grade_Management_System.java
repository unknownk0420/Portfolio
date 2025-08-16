import java.util.*;

public class Student_Grade_Management_System {
    public static void main(String[] args) {
        Student_Grade_Management_System_Class2 studentClass = new Student_Grade_Management_System_Class2();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. 학생 등록");
            System.out.println("2. 성적 수정");
            System.out.println("3. 전체 학생 출력");
            System.out.println("4. 종료");
            System.out.println("메뉴 선택:");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("이름 입력:");
                    String name = sc.next();
                    System.out.println("학번 입력:");
                    int id = sc.nextInt();
                    System.out.println("성적 입력:");
                    double grade = sc.nextDouble();
                    Student_Grade_Management_System_Class1 c1 = new Student_Grade_Management_System_Class1(name, id, (int) grade);
                    studentClass.setPrintStudentInfo(c1);
                    break;
                case 2:
                    System.out.println("수정할 학번 입력:");
                    int modificationID = sc.nextInt();
                    System.out.println("새 성적 입력:");
                    int newGrade = sc.nextInt();
                    studentClass.setPrintGradeModification(modificationID, newGrade);
                    break;
                case 3:
                    studentClass.setPrintStudentInfo();
                    break;
                case 4:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("1~4 중에서 선택하세요.");
            }
        }
    }
}
