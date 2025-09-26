class Student {
    private String name;
    private int grade;

    Student(String name, int grade) {
        this.name = name;
        this.grade = grade;
    }

    String getName() {
        return name;
    }

    int getGrade() {
        return grade;
    }
}
class School {
    Student[] students = new Student[5];
    int count;

    void studentInfo(Student student) {
        if (count < students.length) {
            students[count++] = student;
        } else {
            System.out.println("더이상 학생을 등록할 수 없습니다.");
        }
    }
}
class Teacher {
    void assignScore(Student student, School school) {
        for (int i = 0; i < school.count; i++) {
            if (school.students[i].getName().equals(student.getName())) {
                System.out.println(student.getName() + "의 점수: " + student.getGrade());
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Student student = new Student("Alice", 85);
        School school = new School();
        Teacher teacher = new Teacher();

        school.studentInfo(student);
        teacher.assignScore(student, school);
    }
}
