import java.util.*;

class Constants {
    final static int MAX_COUNT = 10;
}

abstract class Staff {
    protected String name;
    protected int age;
    protected String department;
    protected static int count = 0;
    protected static Staff[] staffList = new Staff[Constants.MAX_COUNT];

    public Staff(String name, int age, String department) {
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public static void allStaffList() {
        for (int i = 0; i < count; i++) {
            if (staffList[i] != null) {
                System.out.println(staffList[i].list());
            }
        }
    }

    public static void employeeList() {
        for (int i = 0; i < count; i++) {
            if ("사원".equals(staffList[i].getRole())) {
                System.out.println(staffList[i].list());
            }
        }
    }

    public static void managerList() {
        for (int i = 0; i < count; i++) {
            if ("매니저".equals(staffList[i].getRole())) {
                System.out.println(staffList[i].list());
            }
        }
    }

    public static void taskList() {
        for (int i = 0; i < count; i++) {
            if (staffList[i] != null) {
                System.out.println((i + 1) + ". " + staffList[i].name + "(" + staffList[i].getRole() + ")");
            }
        }
    }

    public void staffAdd(Staff employee) {
        if (count < Constants.MAX_COUNT) {
            staffList[count++] = employee;
        } else {
            System.out.println("정원 초과로 직원 추가 불가!");
        }
    }

    public void callMeeting() {
        System.out.println(name + "이(가) " + department + "의 회의를 소집합니다.");
        for (int i = 0; i < count; i++) {
            if (this.department.equals(staffList[i].department))
                System.out.print(staffList[i].name + " ");
        }
        System.out.println("(이)가 소집되었습니다.");
    }

    public abstract String list();

    public abstract String getRole();

}

class Employee extends Staff {

    public Employee(String name, int age, String department) {
        super(name, age, department);
    }
    
    @Override
    public String list() {
        return "이름: " + name + ", 나이: " + age + ", 부서: " + department;
    }

    @Override
    public String getRole() {
        return "사원";
    }

}

class Manager extends Staff {
    protected String project;

    public Manager(String name, int age, String department, String project) {
        super(name, age, department);
        this.project = project;
    }
    
    @Override
    public String list() {
        return "이름: " + name + ", 나이: " + age + ", 부서: " + department + ", 담당 프로젝트: " + project;
    }

    @Override
    public String getRole() {
        return "매니저";
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("----------직원 관리 프로그램----------");
            System.out.println("1. 직원 고용");
            System.out.println("2. 직원 목록");
            System.out.println("3. 업무 지시");
            System.out.println("4. 종료");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    System.out.println("직원의 직책을 입력해주세요.");
                    System.out.println("(1. 사원, 2. 매니저)");
                    int choice1 = sc.nextInt();
                    switch (choice1) {
                        case 1:
                            System.out.print("이름 입력: ");
                            String name1 = sc.next();
                            System.out.print("나이 입력: ");
                            int age1 = sc.nextInt();
                            System.out.print("부서 입력: ");
                            String department1 = sc.next();
                            Employee employee = new Employee(name1, age1, department1);
                            employee.staffAdd(employee);
                            break;
                        case 2:
                            System.out.print("이름 입력: ");
                            String name2 = sc.next();
                            System.out.print("나이 입력: ");
                            int age2 = sc.nextInt();
                            System.out.print("부서 입력: ");
                            String department2 = sc.next();
                            System.out.println("프로젝트 입력: ");
                            String project = sc.next();
                            Manager manager = new Manager(name2, age2, department2, project);
                            manager.staffAdd(manager);
                            break;
                        default:
                            System.out.println("다시 올바른 번호를 입력해주세요.");
                    }
                    break;
                case 2:
                    System.out.println("직원 목록");
                    System.out.println("(1. 모든 직급, 2. 사원, 3. 매니저)");
                    int choice2 = sc.nextInt();
                    switch (choice2) {
                        case 1:
                            Staff.allStaffList();
                            break;
                        case 2:
                            Staff.employeeList();
                            break;
                        case 3:
                            Staff.managerList();
                            break;
                        default:
                            System.out.println("다시 올바른 번호를 입력해주세요.");
                    }
                    break;
                case 3:
                    System.out.println("업무 지시");
                    Staff.taskList();
                    System.out.println("몇 번 직원에게 업무를 지시하시겠습니까?");
                    int i = sc.nextInt();
                    if (i < 1 || Staff.count < i) {
                        System.out.println("다시 올바른 번호를 입력해주세요.");
                        break;
                    }
                    System.out.println("어떤 업무를 지시하시겠습니까?");
                    System.out.println("(1. 일반 업무, 2. 회의 모집)");
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            System.out.println(Staff.staffList[i - 1].name + "(이)가 프로젝트 P" + i + "관련 업무를 봅니다.");
                            break;
                        case 2:
                            Staff selected = Staff.staffList[i - 1];
                            selected.callMeeting();
                            break;
                        default:
                            System.out.println("다시 올바른 번호를 입력해주세요.");

                    }
                    break;
                case 4:
                    System.out.println("종료");
                    return;
                default:
                    System.out.println("다시 번호를 눌려주세요.");
            }
        }
    }
}
