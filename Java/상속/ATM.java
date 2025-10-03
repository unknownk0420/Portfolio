import java.util.*;

class Constants {
    final static int MAX_COUNT = 100;
}

interface Account {
    void deposit(int amount);

    void withdraw(int amount);
}

class User implements Account {
    private int balance;
    private String id;
    private String pw;

    public User(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    @Override
    public void deposit(int amount) {
        balance += amount;
    }

    @Override
    public void withdraw(int amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("잔액이 부족합니다.");
        }
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public void transfer(Scanner sc, UserManager userManager) {
        User target = null;
        System.out.println("송금할 아이디를 입력해주세요.");
        String id = sc.next();
        for (int i = 0; i < userManager.count; i++) {
            if (userManager.users[i].getId().equals(id)) {
                target = userManager.users[i];
                break;
            }
        }
        if (target == null) {
            System.out.println("해당 아이디가 존재하지 않습니다.");
            return;
        }
        System.out.println("송금할 금액을 입력하세요.");
        int transfer = sc.nextInt();
        if (transfer <= 0) {
            System.out.println("금액은 0보다 커야 합니다.");
            return;
        }
        if (this.balance < transfer) {
            System.out.println("잔액이 부족합니다.");
            return;
        }
        this.balance -= transfer;
        target.balance += transfer;
        System.out.println(id + "님에게 " + transfer + "송금 완료. 현재 잔액: " + this.balance);
    }
}

class UserManager {
    User[] users = new User[Constants.MAX_COUNT];
    int count = 0;

    public void addUser(User user) {
        if (count < users.length) {
            users[count++] = user;
        } else {
            System.out.println("더이상 사용자 정보를 입력할 수 없습니다.");
        }
    }

    public boolean duplication(String id) {
        for (int i = 0; i < count; i++) {
            if (users[i].getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void signUp(Scanner sc) {
        System.out.println("아이디를 입력해주세요.");
        String id = sc.next();
        if (duplication(id)) {
            System.out.println("중복된 아이디입니다.");
            return;
        }
        System.out.println("비밀번호를 입력해주세요.");
        String pw = sc.next();
        addUser(new User(id, pw));
    }

    public User signIn(Scanner sc) {
        System.out.println("아이디를 입력해주세요.");
        String id = sc.next();
        System.out.println("비밀번호를 입력해주세요.");
        String pw = sc.next();
        for (int i = 0; i < count; i++) {
            if (users[i].getId().equals(id) && users[i].getPw().equals(pw)) {
                System.out.println("로그인 성공");
                return users[i];
            }
        }
        System.out.println("로그인 실패");
        return null;
    }

    public User getSignOut() {
        System.out.println("로그아웃 되었습니다.");
        return null;
    }
}

class Client {
    private Account account;
    private Scanner sc = new Scanner(System.in);

    public void setAccount(Account account) {
        this.account = account;
    }

    public void deposit() {
        System.out.println("입금 금액을 입력해주세요.");
        int depositAmount = sc.nextInt();
        account.deposit(depositAmount);
    }

    public void withdraw() {
        System.out.println("출금 금액을 입력해주세요.");
        int withdrawAmount = sc.nextInt();
        account.withdraw(withdrawAmount);
    }

    public void run(UserManager userManager) {
        User userLogin = null;
        while (true) {
            System.out.println("1. 회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 로그아웃");
            System.out.println("4. 입금");
            System.out.println("5. 출금");
            System.out.println("6. 송금");
            System.out.println("7. 종료");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    userManager.signUp(sc);
                    break;
                case 2:
                    userLogin = userManager.signIn(sc);
                    if (userLogin != null) {
                        setAccount(userLogin);
                    }
                    break;
                case 3:
                    userLogin = userManager.getSignOut();
                    setAccount(null);
                    break;
                case 4:
                    if (userLogin != null) {
                        deposit();
                    } else {
                        System.out.println("로그인 후 이용해주세요.");
                    }
                    break;
                case 5:
                    if (userLogin != null) {
                        withdraw();
                    } else {
                        System.out.println("로그인 후 이용해주세요.");
                    }
                    break;
                case 6:
                    if (userLogin != null) {
                        userLogin.transfer(sc, userManager);
                    } else {
                        System.out.println("로그인 후 이용해주세요.");
                    }
                    break;
                case 7:
                    System.out.println("ATM을 종료합니다.");
                    return;
                default:
                    System.out.println("다시 올바른 숫자를 입력해주세요.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        Client client = new Client();
        client.run(userManager);
    }
}
