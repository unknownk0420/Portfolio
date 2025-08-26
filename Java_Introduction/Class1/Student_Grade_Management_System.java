//get -> 값을 꺼냄, set -> 값을 저장, 일반 메서드 -> 기능 수행
import java.util.*;

class Student_Grade_Management {
    private String studentName;
    private int studentId;
    private int korScore;
    private int engScore;
    private int mathScore;

    public Student_Grade_Management(String studentName, int studentId, int korScore, int engScore, int mathScore) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.korScore = korScore;
        this.engScore = engScore;
        this.mathScore = mathScore;
    }

    public int getStudentId() {
        return this.studentId;
    }

    public int getKorScore() {
        return this.korScore;
    }

    public int getEngScore() {
        return this.engScore;
    }

    public int getMathScore() {
        return this.mathScore;
    }

    public void setGrade(int newKorScore, int newEngScore, int newMathScore) {
        this.korScore = newKorScore;
        this.engScore = newEngScore;
        this.mathScore = newMathScore;
    }

    public double getScoreAverage() {
        return (getKorScore() + getEngScore() + getMathScore()) / 3.0;
    }

    public void printStudentList() {
        System.out.println("이름: " + this.studentName);
        System.out.println("학번: " + this.studentId);
        System.out.println("국어 점수: " + this.korScore);
        System.out.println("영어 점수: " + this.engScore);
        System.out.println("수학 점수: " + this.mathScore);
        System.out.println("평균: " + getScoreAverage());
    }
}
public class Student_Grade_Management_System {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Student_Grade_Management[] studentGradeManagements = new Student_Grade_Management[100];
        int count = 0;
        while (true) {
            System.out.println("=== 학생 성적 관리 시스템 ===");
            System.out.println("1. 학생 등록");
            System.out.println("2. 학생 목록 출력");
            System.out.println("3. 학생 검색");
            System.out.println("4. 성적 수정");
            System.out.println("5. 종료");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("메뉴 선택: " + choice);
                    System.out.println("이름:");
                    String name = sc.next();
                    System.out.println("학번:");
                    int id = sc.nextInt();
                    System.out.println("국어 점수:");
                    int kor = sc.nextInt();
                    System.out.println("영어 점수:");
                    int eng = sc.nextInt();
                    System.out.println("수학 점수:");
                    int math = sc.nextInt();
                    Student_Grade_Management studentGradeManagement = new Student_Grade_Management(name, id, kor, eng, math);
                    studentGradeManagements[count++] = studentGradeManagement;
                    break;
                case 2:
                    System.out.println("메뉴 선택: " + choice);
                    for (int i = 0; i < count; i++) {
                        studentGradeManagements[i].printStudentList();
                    }
                    break;
                case 3:
                    System.out.println("메뉴 선택: " + choice);
                    System.out.println("검색할 학번 입력: ");
                    int idSearch = sc.nextInt();
                    for (int i = 0; i < count; i++) {
                        if (studentGradeManagements[i].getStudentId() == idSearch) {
                            studentGradeManagements[i].printStudentList();
                        }
                    }
                    break;
                case 4:
                    System.out.println("메뉴 선택: " + choice);
                    System.out.println("수정할 학생의 학번 입력:");
                    int newIdSearch = sc.nextInt();
                    System.out.println("새 국어 점수:");
                    int newKorScore = sc.nextInt();
                    System.out.println("새 영어 점수:");
                    int newEngScore = sc.nextInt();
                    System.out.println("새 수학 점수:");
                    int newMathScore = sc.nextInt();
                    for (int i = 0; i < count; i++) {
                        if (studentGradeManagements[i].getStudentId() == newIdSearch) {
                            studentGradeManagements[i].setGrade(newKorScore, newEngScore, newMathScore);
                            System.out.println("성적이 수정되었습니다.");
                        }
                    }
                    break;
                case 5:
                    System.out.println("프로그램 종료!");
                    return;
                default:
                    System.out.println("1~5까지 중 눌려주세요!");
                    break;
            }
        }
    }
}
