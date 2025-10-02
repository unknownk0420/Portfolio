abstract class Employee {
    private String name;
    private String id;

    public Employee(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public abstract int calculatePay();

    public String getName() {
        return name;
    }
}

class FullTimeEmployee extends Employee {

    private int monthlySalary;

    public FullTimeEmployee(String name, String id, int monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public int calculatePay() {
        return monthlySalary;
    }
}

class ContractEmployee extends Employee {

    private int hourlyRate;
    private int hoursWorked;

    public ContractEmployee(String name, String id, int hourlyRate, int hoursWorked) {
        super(name, id);
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
    }

    @Override
    public int calculatePay() {
        return hourlyRate * hoursWorked;
    }
}

class Freelancer extends Employee {

    private int projectFee;

    public Freelancer(String name, String id, int projectFee) {
        super(name, id);
        this.projectFee = projectFee;
    }

    @Override
    public int calculatePay() {
        return projectFee;
    }
}

public class Main {
    public static void main(String[] args) {
        FullTimeEmployee fullTimeEmployee = new FullTimeEmployee("Alice", "1", 3000);
        ContractEmployee contractEmployee = new ContractEmployee("Bob", "2", 20, 120);
        Freelancer freelancer = new Freelancer("Jack", "3", 1500);

        System.out.println(fullTimeEmployee.getName() + "'s pay: $" + fullTimeEmployee.calculatePay());
        System.out.println(contractEmployee.getName() + "'s pay: $" + contractEmployee.calculatePay());
        System.out.println(freelancer.getName() + "'s pay: $" + freelancer.calculatePay());
    }
}
