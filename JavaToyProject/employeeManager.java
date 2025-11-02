import java.util.*;
class Constants {
    public final static int MAX_COUNT = 100;
}

class Staff {
    private String name;
    private int age;
    private int salary;
    private static Staff[] staffs = new Staff[Constants.MAX_COUNT];
    private static int index;
    private static int totalSalary;
    private static int staffCount;

    public Staff(String name, int age, int salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        staffCount++;
        totalSalary += this.salary;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public static int getStaffCount() {
        return staffCount;
    }

    public static int getTotalSalary() {
        return totalSalary;
    }

    @Override
    public String toString() {
        return "이름: " + this.getName() + ", 나이: " + this.getAge() + ", 급여: " + this.getSalary() + "만원";
    }

    public void staffEmployee(Staff staff) {
        if (index < Constants.MAX_COUNT) {
            staffs[index++] = staff;
        } else {
            System.out.println("더이상 직원을 등록 할 수 없습니다.");
        }
    }

    public static void printStaffList() {
        for (int i = 0; i < index; i++) {
            System.out.println(staffs[i]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("----- 직원 관리 프로그램 -----");
            System.out.println("1. 직원 고용");
            System.out.println("2. 직원 목록");
            System.out.println("3. 고용 비용");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    System.out.println("--- 직원 고용 ---");
                    System.out.print("직원 이름: ");
                    String name = sc.next();
                    System.out.print("직원 나이: ");
                    int age = sc.nextInt();
                    System.out.print("직원 급여: ");
                    int salary = sc.nextInt();
                    Staff staff = new Staff(name, age, salary);
                    staff.staffEmployee(staff);
                    break;
                case 2:
                    System.out.println("--- 직원 목록 ---");
                    Staff.printStaffList();
                    System.out.println();
                    break;
                case 3:
                    System.out.println("--- 고용 비용 ---");
                    System.out.print("현재 직원의 수: " + Staff.getStaffCount());
                    System.out.println();
                    System.out.print("지출되는 고용 비용: " + Staff.getTotalSalary());
                    System.out.println();
                    break;
                default:
                    System.out.println("다시 입력해주세요.");
            }
        }
    }
}

