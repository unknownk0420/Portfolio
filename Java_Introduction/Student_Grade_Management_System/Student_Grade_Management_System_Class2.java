public class Student_Grade_Management_System_Class2 {
    Student_Grade_Management_System_Class1[] students = new Student_Grade_Management_System_Class1[10];
    int count = 0;

    public void setPrintStudentInfo(Student_Grade_Management_System_Class1 s) {
        if (count < students.length) {
            students[count++] = s;
            System.out.println(s.getStudentName() + " 학생이 등록되었습니다.");
        } else {
            System.out.println("학생 등록 한도 초과");
        }
    }

    public void setPrintGradeModification(int id, int newGrade) {
        for (int i = 0; i < count; i++) {
            if (students[i].getStudentID() == id) {
                students[i].setGrade(newGrade);
                return;
            }
        }
        System.out.println("해당 학번을 찾을 수 없습니다.");
    }

    public void setPrintStudentInfo() {
        for (int i = 0; i < count; i++) {
            students[i].setStudentInfo();
        }
    }
}
