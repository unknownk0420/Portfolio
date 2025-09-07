import java.util.*;

class Constants {
    public final static int MAX_COUNT = 100;
    public final static String ADMIN_PIN = "1234";
}

class User {
    private String id;
    private String pw;
    private int balance;
    public static User[] users = new User[Constants.MAX_COUNT];
    public static int count = 0;

    public User(String id, String pw, int balance) {
        this.id = id;
        this.pw = pw;
        this.balance = balance;
    }

    public String getId() {
        return this.id;
    }

    public void addBalance(int amount) {
        this.balance += amount;
    }

    public void diffBalance(int amount) {
        this.balance -= amount;
    }

    public int getBalance() {
        return this.balance;
    }

    public void setBalance(int amount) {
        if (amount >= 0) {
            this.balance = amount;
        } else {
            System.out.println("잔액은 0보다 작을 수 없습니다.");
        }
    }

    public void modify(String id, String newPw) {
        for (int i = 0; i < count; i++) {
            if (users[i].getId().equals(id)) {
                users[i].pw = newPw;
                System.out.println("비밀번호 변경 완료");
                return;
            }
        }
        System.out.println("해당 아이디를 찾을 수 없습니다.");
    }


    public boolean isDuplication(String id) {
        for (int i = 0; i < count; i++) {
            if (users[i].getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void signUp(Scanner sc) {
        System.out.println("회원가입을 시작합니다.");
        System.out.println("아이디를 입력해주세요.");
        String id;
        while (true) {
            id = sc.next();
            if (isDuplication(id)) {
                System.out.println("중복된 아이디입니다. 다시 아이디를 입력해주세요.");
            } else {
                break;
            }
        }
        System.out.println("비밀번호를 입력해주세요.");
        String pw = sc.next();
        System.out.println("회원가입이 성공하였습니다.");
        users[count++] = new User(id, pw, 0);
    }

    public static User signIn(Scanner sc) {
        System.out.println("로그인을 시작합니다.");
        System.out.println("아이디를 입력해주세요.");
        String id = sc.next();
        System.out.println("비밀번호를 입력해주세요.");
        String pw = sc.next();
        for (int i = 0; i < count; i++) {
            if (users[i].id.equals(id) && users[i].pw.equals(pw)) {
                System.out.println("로그인이 성공하였습니다.");
                return users[i];
            }
        }
        System.out.println("로그인이 실패하였습니다.");
        return null;
    }

    public void deposit(Scanner sc) {
        System.out.println("입금할 금액을 입력해주세요.");
        int deposit = sc.nextInt();
        if (deposit <= 0) {
            System.out.println("금액은 0보다 커야합니다.");
            return;
        }
        addBalance(deposit);
        System.out.println(deposit + "원이 입금되었습니다. 현재 잔액: " + this.balance + "원");
    }

    public void withdraw(Scanner sc) {
        System.out.println("출금할 금액을 입력해주세요.");
        int withdraw = sc.nextInt();
        if (withdraw <= 0) {
            System.out.println("금액은 0보다 커야합니다.");
            return;
        }
        if (balance >= withdraw) {
            diffBalance(withdraw);
            System.out.println(withdraw + "원이 출금되었습니다. 현재 잔액: " + this.balance + "원");
        } else {
            System.out.println("잔액이 부족합니다.");
        }
    }

    public void transfer(Scanner sc) {
        System.out.println("송금할 아이디를 입력해주세요.");
        String targetId = sc.next();
        User targetUser = null;
        for (int i = 0; i < count; i++) {
            if (users[i].getId().equals(targetId)) {
                targetUser = users[i];
                break;
            }
        }
        if (targetUser == null) {
            System.out.println("해당 아이디가 존재하지 않습니다.");
            return;
        }
        System.out.println("송금할 금액을 입력하세요.");
        int transfer = sc.nextInt();
        if (transfer <= 0) {
            System.out.println("금액은 0보다 커야합니다.");
            return;
        }
        if (this.balance < transfer) {
            System.out.println("잔액이 부족합니다.");
            return;
        }
        this.diffBalance(transfer);
        targetUser.addBalance(transfer);
        System.out.println(targetId + "님에게 " + transfer + "송금 완료. 현재 잔액: " + this.balance + "원");
    }
}
class AdminUser extends User {

    public AdminUser() {
        super(null, null, 0);
    }

    public void adminMode(Scanner sc) {
        System.out.println("관리자 PIN을 입력해주세요.");
        String pin = sc.next();
        if (!pin.equals(Constants.ADMIN_PIN)) {
            System.out.println("잘못된 PIN입니다.");
            return;
        }
        while (true) {
            System.out.println("1. 모든 사용자 목록 보기");
            System.out.println("2. 비밀번호 변경");
            System.out.println("3. 사용자 아이디 삭제");
            System.out.println("4. 사용자 잔액 변경");
            System.out.println("5. 사용자 현재 잔액 보기");
            System.out.println("6. 종료");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    viewAllUser();
                    break;
                case 2:
                    resetUserPw(sc);
                    break;
                case 3:
                    deleteUser(sc);
                    break;
                case 4:
                    setUserBalance(sc);
                    break;
                case 5:
                    viewUserBalance(sc);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }

    public void viewAllUser() {
        System.out.println("총 사용자 수: " + count);
        for (int i = 0; i < count; i++) {
            System.out.println(" - " + users[i].getId());
        }
    }


    public void resetUserPw(Scanner sc) {
        System.out.println("아이디를 입력해주세요.");
        String id = sc.next();
        System.out.println("새 비밀번호를 입력해주세요.");
        String newPw = sc.next();
        modify(id, newPw);
    }

    public void deleteUser(Scanner sc) {
        System.out.println("삭제할 사용자 아이디:");
        String id = sc.next();
        for (int i = 0; i < count; i++) {
            if (users[i].getId().equals(id)) {
                for (int j = i; j < count - 1; j++) {
                    users[j] = users[j + 1];
                }
                users[count - 1] = null;
                count--;
                System.out.println(id + "님의 아이디를 삭제하였습니다.");
                return;
            }
        }
        System.out.println("해당 아이디가 존재하지 않습니다.");
    }

    public void setUserBalance(Scanner sc) {
        System.out.println("잔액 설정할 사용자 아이디:");
        String id = sc.next();
        System.out.println("새 잔액:");
        int amount = sc.nextInt();
        for (int i = 0; i < count; i++) {
            if (users[i].getId().equals(id)) {
                users[i].setBalance(amount);
                System.out.println("현재 " + id + "님의 잔액은 " + amount + "원으로 설정되었습니다.");
                return;
            }
        }
        System.out.println("해당 아이디를 찾지 못했습니다.");
    }

    public void viewUserBalance(Scanner sc) {
        System.out.println("조회할 사용자 아이디:");
        String id = sc.next();
        for (int i = 0; i < count; i++) {
            if (users[i].getId().equals(id)) {
                System.out.println(id + "님의 잔액은 " + users[i].getBalance() + "원입니다.");
                return;
            }
        }
        System.out.println("해당 아이디를 찾을 수 없습니다.");
    }
}
public class ATM {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AdminUser adminUser = new AdminUser();
        User login = null;

        while (true) {
            System.out.println("1.회원가입");
            System.out.println("2. 로그인");
            System.out.println("3. 로그아웃");
            System.out.println("4. 입금");
            System.out.println("5. 출금");
            System.out.println("6. 송금");
            System.out.println("7. 관리자 모드");
            System.out.println("8. 종료");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    adminUser.signUp(sc);
                    break;
                case 2:
                    login = User.signIn(sc);
                    break;
                case 3:
                    login = null;
                    System.out.println("로그아웃 되었습니다.");
                    break;
                case 4:
                    if (login != null) {
                        login.deposit(sc);
                    } else {
                        System.out.println("로그인 후 이용해주세요.");
                    }
                    break;
                case 5:
                    if (login != null) {
                        login.withdraw(sc);
                    } else {
                        System.out.println("로그인 후 이용해주세요.");
                    }
                    break;
                case 6:
                    if (login != null) {
                        login.transfer(sc);
                    } else {
                        System.out.println("로그인 후 이용해주세요.");
                    }
                    break;
                case 7:
                    adminUser.adminMode(sc);
                    break;
                case 8:
                    System.out.println("시스템을 종료합니다.");
                    sc.close();
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
