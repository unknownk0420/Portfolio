import java.util.*;

interface Constants {
    int MAX_COUNT = 10;
}

abstract class Company implements Constants{
    private String name;
    private int age;
    private String department;
    protected static Company[] staffs = new Company[Constants.MAX_COUNT];
    protected static int index;

    public Company(String name, int age, String department){
        this.name = name;
        this.age = age;
        this.department = department;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getDepartment() {
        return department;
    }

    public static void allStaff() {
        for(int i = 0; i < index; i++){
            if(staffs[i].getRole().equals("사원")) {
                System.out.println(staffs[i].list());
            } else {
                System.out.println(staffs[i].list());
            }
        }
    }

    abstract String getRole();
    abstract String list();
    abstract void inputStaff(Scanner sc);
    abstract void addStaff(Company company);
}

class Staff extends Company {
    public Staff(String name, int age, String department) {
        super(name, age, department);
    }

    @Override
    public String getRole(){
        return "사원";
    }

    @Override
    public String list(){
        return "이름: " + this.getName() + ", 나이: " + this.getAge() + ", 부서: " + this.getDepartment();
    }

    @Override
    public void inputStaff(Scanner sc) {
        System.out.print("이름 입력: ");
        String name = sc.next();
        System.out.print("나이 입력: ");
        int age = sc.nextInt();
        System.out.print("부서 입력: ");
        String department = sc.next();
        addStaff(new Staff(name, age, department));
    }

    @Override
    public void addStaff(Company company){
        if(index < Constants.MAX_COUNT){
            staffs[index++] = company;
            System.out.println("사원을 고용했습니다.");
        } else {
            System.out.println("더이상 직원을 추가할 수 없습니다.");
        }
    }

    public void allStaffTask() {
        for (int i = 0; i < index; i++) {
            if (staffs[i] != null) {
                System.out.println((i + 1) + ". " + staffs[i].getName() + "(" + staffs[i].getRole() + ")");
            }
        }
    }

    public static void staffList(){
        for(int i = 0; i < index; i++){
            if("사원".equals(staffs[i].getRole())){
                System.out.println(staffs[i].list());
            }
        }
    }
}

class Manager extends Company {
    private String project;

    public Manager(String name, int age, String department, String project) {
        super(name, age, department);
        this.project = project;
    }

    public String getProject() {
        return project;
    }

    @Override
    public String getRole() {
        return "매니저";
    }

    @Override
    public String list() {
        return "이름: " + this.getName() + ", 나이: " + this.getAge() + ", 부서: " + this.getDepartment() + ", 프로젝트: " + this.getProject();
    }

    @Override
    public void inputStaff(Scanner sc) {
        System.out.print("이름 입력: ");
        String name = sc.next();
        System.out.print("나이 입력: ");
        int age = sc.nextInt();
        System.out.print("부서 입력: ");
        String department = sc.next();
        System.out.print("프로젝트 입력: ");
        String project = sc.next();
        addStaff(new Manager(name, age, department, project));
    }

    @Override
    public void addStaff(Company company) {
        if (index < Constants.MAX_COUNT) {
            staffs[index++] = company;
            System.out.println("매니저를 고용했습니다.");
        } else {
            System.out.println("더이상 직원을 추가할 수 없습니다.");
        }
    }

    public static void managerList(){
        for(int i = 0; i < index; i++){
            if("매니저".equals(staffs[i].getRole())){
                System.out.println(staffs[i].list());
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Company staff = new Staff(null, 0, null);
        Company manager = new Manager(null, 0, null, null);
        while (true) {
            System.out.println("1. 직원 고용");
            System.out.println("2. 직원 목록");
            System.out.println("3. 업무 지시");
            System.out.println("4. 종료");
            System.out.print("입력: ");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    System.out.println("직원의 직책을 입력해주세요.");
                    System.out.println("(1. 사원, 2. 매니저)");
                    System.out.print("입력: ");
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            staff.inputStaff(sc);
                            break;
                        case 2:
                            manager.inputStaff(sc);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("(1. 모든 직급, 2. 사원, 3. 매니저)");
                    System.out.print("입력: ");
                    int pick = sc.nextInt();
                    switch (pick) {
                        case 1:
                            System.out.println("----- 모든 직급 -----");
                            Company.allStaff();
                            break;
                        case 2:
                            System.out.println("----- 사원 -----");
                            Staff.staffList();
                            break;
                        case 3:
                            System.out.println("----- 매니저 -----");
                            Manager.managerList();
                            break;
                    }
                    break;
                case 3:
                    ((Staff) staff).allStaffTask();
                    System.out.println("몇 번 직원에게 업무를 지시하시겠습니까?");
                    System.out.print("입력: ");
                    int num1 = sc.nextInt();
                    for (int i = 0; i < Company.index; i++) {
                        if (num1 == i + 1) {
                            if ("사원".equals(Company.staffs[i].getRole())) {
                                System.out.println(Company.staffs[i].getName() + "(이)가 " + Company.staffs[i].getDepartment() + "서의 업무를 봅니다.");
                            } else if ("매니저".equals(Company.staffs[i].getRole())) {
                                System.out.println("어떤 업무를 지시하시겠습니까?");
                                System.out.println("(1. 일반 업무, 2. 회의 모집)");
                                int num2 = sc.nextInt();
                                Manager m = (Manager) Company.staffs[i];
                                switch (num2) {
                                    case 1:
                                        System.out.println(Company.staffs[i].getName() + "(이)가 " + m.getProject() + "관련 업무를 봅니다.");
                                        break;
                                    case 2:
                                        System.out.println(Company.staffs[i].getName() + "(이)가 " + Company.staffs[i].getDepartment() + "의 회의를 소집합니다.");
                                        for (int j = 0; j < Company.index; j++) {
                                            if (Company.staffs[j].getDepartment().equals(Company.staffs[i].getDepartment())) {
                                                if (j == Company.index - 1) {
                                                    System.out.print(Company.staffs[j].getName());
                                                } else {
                                                    System.out.print(Company.staffs[j].getName() + ", ");
                                                }
                                            }
                                        }
                                        System.out.print("(이)가 소집되었습니다.");
                                        System.out.println();
                                        break;
                                }
                            }
                        }
                    }
                    break;
                case 4:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("다시 올바른 번호를 입력해주세요.");
            }
        }
    }
}
