import java.util.*;

class Constants{
    final static int MAX_COUNT = 100;
}
class ServerSetting {
    private String id;
    private String pw;
    private String name;
    private String tel;
    private int birthDate;
    private String gameId;

    public ServerSetting(String gameId) {
        this(null, null, null, null, 0, gameId);
    }

    public ServerSetting(String id, String pw, String name, String tel, int birthDate, String gameId) {
        this.id = id;
        this.pw = pw;
        this.name = name;
        this.tel = tel;
        this.birthDate = birthDate;
        this.gameId = gameId;
    }

    public String getId() {
        return this.id;
    }

    public String getPw() {
        return this.pw;
    }

    public String getName() {
        return this.name;
    }

    public String getTel() {
        return this.tel;
    }

    public int getBirthDate() {
        return this.birthDate;
    }

    public String getGameId() {
        return this.gameId;
    }

    public void setModify(String newId, String newPw, String newName, String newTel, int newBirthDate, String newGameId) {
        this.id = newId;
        this.pw = newPw;
        this.name = newName;
        this.tel = newTel;
        this.birthDate = newBirthDate;
        this.gameId = newGameId;
    }
}

class ServerManagement {
    private final ServerSetting[] serverSettings = new ServerSetting[Constants.MAX_COUNT];
    private int count = 0;

    public void addServerInfo(ServerSetting serverSetting) {
        if (count < serverSettings.length) {
            serverSettings[count++] = serverSetting;
        } else {
            System.out.println("회원 가입을 실패 했습니다.");
        }
    }

    public void signIn(Scanner sc) {
        if (count == 0) {
            System.out.println("회원가입을 먼저 해주세요.");
        } else {
            System.out.println("아이디를 입력해 주세요:");
            String id = sc.next();
            for (int i = 0; i < count; i++) {
                if (serverSettings[i].getId().equals(id)) {
                    System.out.println("이미 존재하는 아이디입니다.");
                }
            }
            System.out.println("비밀번호를 입력해 주세요:");
            String pw = sc.next();
            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (serverSettings[i].getId().equals(id) && serverSettings[i].getPw().equals(pw)) {
                    System.out.println("로그인이 완료 되었습니다.");
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("로그인을 다시 해 주세요.");
            }
        }
    }

    public void myInfo(Scanner sc) {
        int info = 0;
        if (count == 0) {
            System.out.println("내 정보 조회를 실패하였습니다.");
        } else {
            System.out.println("이름을 입력해 주세요.");
            String name = sc.next();
            for (int i = 0; i < count; i++) {
                if (serverSettings[i].getName().equals(name)) {
                    System.out.println("이름: " + serverSettings[i].getName());
                    System.out.println("아이디: " + serverSettings[i].getId());
                    System.out.println("비밀번호: " + serverSettings[i].getPw());
                    System.out.println("전화 번호: " + serverSettings[i].getTel());
                    System.out.println("생년월일: " + serverSettings[i].getBirthDate());
                    info++;
                }
            }
        }
        System.out.println("동일 사용자 정보가 " + info + "개 존재합니다.");
    }

    public void delete(Scanner sc) {
        if (count == 0) {
            System.out.println("사용자 정보가 없습니다.");
        } else {
            boolean found = false;
            System.out.println("아이디를 입력해 주세요.");
            String id = sc.next();
            for (int i = 0; i < count; i++) {
                if (serverSettings[i].getId().equals(id)) {
                    for (int j = i; j < count - 1; j++) {
                        serverSettings[j] = serverSettings[j + 1];
                    }
                    serverSettings[count - 1] = null;
                    count--;
                    found = true;
                    System.out.println(id + "님이 회원을 탈퇴하였습니다.");
                    break;
                }
            }
            if (!found) {
                System.out.println("해당 아이디를 찾을 수 없습니다.");
            }
        }
    }

    public void myInfoModify(Scanner sc) {
        if (count == 0) {
            System.out.println("수정할 데이터가 없습니다.");
        } else {
            System.out.println("이름을 입력해주세요.");
            String name = sc.next();
            for (int i = 0; i < count; i++) {
                if (serverSettings[i].getName().equals(name)) {
                    System.out.println("회원 정보를 수정해 주세요.");
                    System.out.println("새 아이디:");
                    String newId = sc.next();
                    System.out.println("새 비밀번호:");
                    String newPw = sc.next();
                    System.out.println("새 이름:");
                    String newName = sc.next();
                    System.out.println("새 전화번호:");
                    String newTel = sc.next();
                    System.out.println("새 생년월일:");
                    int newBirthDate = sc.nextInt();
                    System.out.println("새 게임 아이디:");
                    String newGameId = sc.next();
                    serverSettings[i].setModify(newId, newPw, newName, newTel, newBirthDate, newGameId);
                }
            }
        }
    }

    public void gameId(Scanner sc) {
        if (count == 0) {
            System.out.println("회원가입을 먼저 해주세요.");
        } else {
            System.out.println("게임 아이디를 입력해주세요:");
            String gameId = sc.next();
            ServerSetting serverGameIdSetting = new ServerSetting(gameId);
            System.out.println(serverGameIdSetting.getGameId() + "가 생성되었습니다.");
        }
    }
}

public class GameServer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ServerManagement serverManagement = new ServerManagement();
        while (true) {
            System.out.println("1. 로그인");
            System.out.println("2. 회원 가입");
            System.out.println("3. 내 정보 조회");
            System.out.println("4. 내 정보 삭제");
            System.out.println("5. 내 정보 수정");
            System.out.println("6. 게임 아이디 만들기");
            System.out.println("7. 종료");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    serverManagement.signIn(sc);
                    break;
                case 2:
                    System.out.println("회원 가입");
                    System.out.println("이름을 입력해 주세요.");
                    String name = sc.next();
                    System.out.println("아이디를 입력해 주세요.");
                    String id = sc.next();
                    System.out.println("비밀번호를 입력해 주세요.");
                    String pw = sc.next();
                    System.out.println("전화번호를 입력해 주세요.");
                    String tel = sc.next();
                    System.out.println("생년월일을 입력해 주세요.");
                    int birthDate = sc.nextInt();
                    ServerSetting serverSetting = new ServerSetting(id, pw, name, tel, birthDate, null);
                    serverManagement.addServerInfo(serverSetting);
                    break;
                case 3:
                    serverManagement.myInfo(sc);
                    break;
                case 4:
                    serverManagement.delete(sc);
                    break;
                case 5:
                    serverManagement.myInfoModify(sc);
                    break;
                case 6:
                    serverManagement.gameId(sc);
                    break;
                case 7:
                    System.out.println("게임 서버를 닫습니다.");
                    return;
                default:
                    System.out.println("1~6 중 눌려주세요.");
            }
        }
    }
}
