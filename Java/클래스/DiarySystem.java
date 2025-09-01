import java.util.Scanner;

class Constants {
    protected final static int MAX_MEMBERSHIP_COUNT = 100;
}
class Server {
    private String id;
    private String pw;


    public Server(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String newId) {
        this.id = newId;
    }

    public String getPw() {
        return this.pw;
    }

    public void setPw(String newPw) {
        this.pw = newPw;
    }
}

class Management {
    private Server[] servers = new Server[Constants.MAX_MEMBERSHIP_COUNT];
    private int count = 0;
    public void SignUp(Server server) {
        if (count < servers.length) {
            servers[count++] = server;
            System.out.println("회원 가입이 되었습니다.");
        } else {
            System.out.println("회원 가입이 불가능 합니다.");
        }
    }

    public void deleteMemberShip(Scanner sc) {
        System.out.println("회원탈퇴를 하려면 아이디와 비밀번호를 입력해 주세요.");
        System.out.println("아이디 입력:");
        String id = sc.next();
        System.out.println("비밀번호 입력:");
        String pw = sc.next();
        for (int i = 0; i < count; i++) {
            if (servers[i].getId().equals(id) && servers[i].getPw().equals(pw)) {
                for (int j = i; j < count - 1; j++) {
                    servers[j] = servers[j + 1];
                }
                servers[count - 1] = null;
                count--;
                System.out.println("회원탈퇴 완료 했습니다.");
                return;
            }
        }
    }

    public void modifyMembership(Scanner sc) {
        System.out.println("아이디를 수정하려면 1번을 비밀번호를 수정하려면 2번을 누르세요.");
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                System.out.println("기존 아이디를 입력 해주세요.");
                String id = sc.next();
                for(int i = 0; i < count; i++) {
                    if (servers[i].getId().equals(id)) {
                        System.out.println("기존 아이디 확인 완료했습니다.");
                        System.out.println("새로운 아이디를 입력 해주세요.");
                        String newId = sc.next();
                        servers[i].setId(newId);
                        System.out.println("아이디 변경이 완료되었습니다.");
                    }
                }
                break;
            case 2:
                System.out.println("기존 비밀번호를 입력 해주세요.");
                String pw = sc.next();
                for(int i = 0; i < count; i++) {
                    if (servers[i].getPw().equals(pw)) {
                        System.out.println("기존 비밀번호를 확인 완료했습니다.");
                        System.out.println("새로운 비밀번호를 입력 해주세요.");
                        String newPw = sc.next();
                        servers[i].setPw(newPw);
                        System.out.println("비밀번호 변경이 완료되었습니다.");
                    }
                }
                break;
        }
    }
}

public class DiarySystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Management management = new Management();
        System.out.println("회원 가입을 해주세요.");
        System.out.println("아이디 입력:");
        String id = sc.next();
        System.out.println("비밀번호 입력:");
        String pw = sc.next();
        Server server = new Server(id, pw);
        management.SignUp(server);
        System.out.println("회원 탈퇴를 하시려면 0을 눌려주세요.");
        int select = sc.nextInt();
        if (select == 0) {
            management.deleteMemberShip(sc);
        }
        System.out.println("아이디와 비밀번호를 수정 하시려면 0을 눌려주세요.");
        int choice = sc.nextInt();
        if (choice == 0) {
            management.modifyMembership(sc);
        }
        System.out.println("메모장을 켭니다.");
    }
}
