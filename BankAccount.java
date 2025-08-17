class Bank {
    private String accountNumber;
    private String ownerName;
    private int balance;

    public Bank(String a, String o, int b) {
        accountNumber = a;
        ownerName = o;
        balance = b;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getBalance() {
        return balance;
    }

    public void setOwnerName(String o) {
        ownerName = o;
    }

    public void setDeposit(int amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("입금 후 잔액: " + balance);
        } else {
            System.out.println("입금 실패: 잘못된 금액입니다.");
        }
    }

    public void setWithdraw(int amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            System.out.println("출금 후 잔액: " + balance);
        } else {
            System.out.println("출금 실패: 잔액 부족 또는 잘못된 금액입니다.");
        }
    }

    public void printAccountList() {
        System.out.println("계좌 번호: " + getAccountNumber());
        System.out.println("예금주: " + getOwnerName());
        System.out.println("잔액: " + getBalance());
    }
}
public class BankAccount {
    public static void main(String[] args) {
        Bank bank = new Bank("123-456-789", "홍길동", 100000);
        bank.printAccountList();
        bank.setDeposit(50000);
        bank.setWithdraw(30000);
        bank.setOwnerName("김철수");
        bank.printAccountList();
    }
}
