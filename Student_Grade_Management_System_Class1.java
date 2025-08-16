public class Student_Grade_Management_System_Class1 {
    private String studentName;
    private int studentID;
    private int grade;

    public Student_Grade_Management_System_Class1(String s, int i, int g) {
        studentName = s;
        studentID = i;
        grade = g;
    }

    public void setStudentInfo() {
        System.out.println("학생: " + studentName + " 학번: " + studentID + " 성적: " + grade);
    }

    public String getStudentName() {
        return studentName;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setGrade(int newGrade) {
        if (newGrade >= 0 && newGrade <= 100) {
            grade = newGrade;
        } else {
            System.out.println("0 ~ 100까지의 값을 입력하세요.");
        }
    }
}
