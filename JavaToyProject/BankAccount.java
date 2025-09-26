class Bank {
    private static int totalAssets;
    private static int totalAccount;

    static void assetsUpdate(int amount) {
        totalAssets += amount;
    }

    static void incrementAccount() {
        totalAccount++;
    }

    static int getTotalAssets() {
        return totalAssets;
    }

    static int getTotalAccounts() {
        return totalAccount;
    }
}
class Account {
    private int balance;

    Account() {
        this.balance = 0;
        Bank.incrementAccount();
    }

    void deposit(int amount) {
        balance += amount;
        Bank.assetsUpdate(amount);
    }

    void withdraw(int amount) {
        if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            Bank.assetsUpdate(-amount);
        }
    }

    int getBalance() {
        return balance;
    }
}
class UserClass {
    private String name;
    private Account account;

    UserClass(String name) {
        this.name = name;
        this.account = new Account();

    }

    void deposit(int amount) {
        account.deposit(amount);
    }

    void withdraw(int amount) {
        account.withdraw(amount);
    }

    void showBalance() {
        System.out.println(name + "'s Balance: " + account.getBalance());
    }
}

public class Main {
    public static void main(String[] args) {
        UserClass userClass1 = new UserClass("Alice");
        UserClass userClass2 = new UserClass("Bob");

        userClass1.deposit(1000);
        userClass2.deposit(500);

        userClass1.withdraw(200);
        userClass2.withdraw(1000);

        userClass1.showBalance();
        userClass2.showBalance();

        System.out.println("Total bank assets: " + Bank.getTotalAssets());
        System.out.println("Total accounts: " + Bank.getTotalAccounts());
    }
}
