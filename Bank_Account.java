class Account {
    //인스턴스 변수(개별 계좌가 가지는 정보)
    private int accountNumber;
    private String owner;
    private int balance;
    //인스턴스 변수와 같이 생성자를 만들어 인스턴스 변수를 초기화를 하는 것이 아니므로 정적 멤버를 사용
    //정적 멤버는 모든 계좌가 공유하는 정보를 나타냄
    static private int totalAccounts = 0;
    static private int totalBalance = 0;
    static private int nextAccountNumber = 1001;

    //객체 자기 자신을 가리키고 매개 변수와 구분을 명확히 하기 위해 this를 사용함
    //인스턴스 변수를 초기화 하기 위해 생성자를 만듬
    //매개 변수는 값을 전달하기 위해 쓰임
    public Account(String owner, int initialDeposit) {
        this.owner = owner;
        this.balance = initialDeposit;
        this.accountNumber = Account.nextAccountNumber++;//정적 멤버는 클래스 이름으로 접근하는 것이 좋다.
        Account.totalAccounts++;
        Account.totalBalance += initialDeposit;
        //인스턴스 변수임을 명확히 하기 위해 this 사용
        System.out.println("계좌 생성 완료 - 계좌 번호: " + this.accountNumber + ", 예금주: " + this.owner + ",초기입금액: " + this.balance);

    }

    public void deposit(int amount) {
        balance += amount;
        totalBalance += amount;
    }

    public void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
            totalBalance -= amount;
        } else {
            System.out.println("잔액 부족");
        }
    }

    public void showAccount() {
        System.out.println("계좌 번호: " + this.accountNumber + ", 예금주: " + this.owner + ", 초기입금액: " + this.balance);
    }

    public static void showBankStats() {
        System.out.println("전체 계좌 수: " + Account.totalAccounts + ", 전체 예치금: " + Account.totalBalance);
    }
}
public class Bank_Account {
    public static void main(String[] args) {
        //객체 생성 단, 객체는 순서대로 실행됨
        Account a1 = new Account("홍길동", 5000);
        Account a2 = new Account("김철수", 10000);

        a1.deposit(3000);
        a2.withdraw(4000);

        a1.showAccount();
        a2.showAccount();
        //showBankStats는 인스턴스 멤버가 없는 정적 메소드이므로 클래스로 접근함
        Account.showBankStats();
    }
}
