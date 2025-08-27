import java.util.*;

class Constants{
    //같은 패키지 내에서 사용 가능 따라서 protected 접근 제어자를 씀
    protected final static int MAX_USER = 100;
    protected final static String DEFAULT_ROLE = "Guest";
    protected final static String ADMIN_ID = "admin";
    protected final static String ADMIN_PASSWORD = "1234";
}
class User{
    //동일 클래스 내부에서 사용하기 때문에 private 접근 제어자를 씀
    private String userName;
    private String userRole;
    private int userAge;
    //생성자 오버로딩
    public User(String userName, int userAge){
        this(userName, userAge, Constants.DEFAULT_ROLE);//중복된 코드를 줄이고 다른 생성자에 집중하여 초기화 하기 위해 this()를 씀
    }
    public User(String userName, int userAge, String userRole){//this()에서 괄호 매개 변수 전부 초기화 됨
        this.userName = userName;
        this.userRole = userRole;
        this.userAge = userAge;
    }
    //private 접근 제어자를 외부에서 쓰기위해 public 접근 제어자 메소드에 get을 씀
    public String getUserName(){
        return this.userName;
    }
    public String getUserRole(){
        return this.userRole;
    }
    public int getUserAge(){
        return this.userAge;
    }
}
class UserInfo{
    //전체 코드에서 하나의 객체만 생성하여 사용하기 위해 싱글톤 패턴을 함
    private final static UserInfo userInfo = new UserInfo();
    private User[] users = new User[Constants.MAX_USER];//참조 자료형이 User인 배열 선언
    private int count = 0;

    public void userAdd(User user){//USER의 객체를 담기 위한 매개 변수
        if(count < Constants.MAX_USER) {
            users[count++] = user;
        } else {
            System.out.println("저장 공간이 없습니다.");
        }
    }
    public void allUserList(){
        for(int i = 0; i < count; i++){
            System.out.println("이름: " + users[i].getUserName() + ", 나이: " + users[i].getUserAge() + ", 역할: " + users[i].getUserRole());
        }
    }
    public void nameSearch(String name){
        boolean found = false;
        for(int i = 0; i < count; i++){//count만큼 순회하여 i번째 객체를 조건에 맞게 꺼내 get메서드에 반환함
            if(users[i].getUserName().equals(name)){
                System.out.println("이름: " + users[i].getUserName() + ", 나이: " + users[i].getUserAge() + ", 역할: " + users[i].getUserRole());
                found = true;
            }
        }
        if(!found){//false가 참이면 조건문 실행
            System.out.println("해당 이름을 가진 사용자가 없습니다.");
        }
    }
    public void printUserRole(String role){
        boolean found = false;
        for(int i = 0; i < count; i++){
            if(users[i].getUserRole().equals(role)){
                System.out.println("이름: " + users[i].getUserName() + ", 나이: " + users[i].getUserAge() + ", 역할: " + users[i].getUserRole());
                found = true;
            }
        }
        if(!found) {
            System.out.println("해당 역할을 가진 사용자가 없습니다.");
        }
    }
    public int getCheckCount(){
        return count;
    }
    public static UserInfo getUserInfo() {
        return userInfo;
    }
    public void deleteUser(String name){
        boolean found = false;
        for(int i = 0; i < count; i++){
            if(users[i].getUserName().equals(name)){
                for (int j = i; j < count - 1; j++){//
                    users[j] = users[j + 1];//뒤에 있는 데이터를 한 칸씩 당겨서 맨 뒤에 빈 배열을 만듬
                }
                users[count--] = null;//빈 배열을 삭제
                System.out.println(name + " 사용자 삭제 완료!");
                found = true;
                break;
            }
        }
        if(!found){
            System.out.println("해당 사용자를 찾을 수 없습니다.");
        }
    }
    public void sortUserAge(){
        for(int i = 0; i < count; i++){//버블정렬
            for(int j = i + 1; j < count; j++){
                if(users[i].getUserAge() > users[j].getUserAge()){
                    User temp = users[i];
                    users[i] = users[j];
                    users[j] = temp;
                }
            }
        }
        System.out.println("나이순 정렬 완료");
    }
}
public class UserManager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserInfo userInfo = UserInfo.getUserInfo();//싱글톤 패턴을 호출하기 위해 객체를 호출하지 않고 get메서드를 호출
        while (true) {
            System.out.println("=== 사용자 관리 메뉴 ===");
            System.out.println("1. 사용자 등록");
            System.out.println("2. 이름으로 사용자 검색");
            System.out.println("3. 역할별 사용자 출력");
            System.out.println("4. 총 사용자 수 확인");
            System.out.println("5. 사용자 삭제");
            System.out.println("6. 사용자 나이 정렬");
            System.out.println("7. 관리자 로그인");
            System.out.println("8. 전체 사용자 출력");
            System.out.println("9. 종료");
            int choice = sc.nextInt();
            switch (choice){
                case 1:
                    System.out.println("메뉴 선택: " + choice);
                    System.out.println("이름 입력:");
                    String name = sc.next();
                    System.out.println("나이 입력:");
                    int age = sc.nextInt();
                    sc.nextLine();//입력 버퍼 정리
                    System.out.println("역할 입력(없으면 엔터):");
                    String role = sc.nextLine();
                    if(role.trim().isEmpty()){//trim()으로 양끝 공백을 삭제를 하고 빈공간(isEmpty())이면 조건문이 실행
                        userInfo.userAdd(new User(name, age));//메서드 오버로딩을 하기 위해 if문을 씀
                    } else {
                        userInfo.userAdd(new User(name, age, role));
                    }
                    break;
                case 2:
                    System.out.println("메뉴 선택: " + choice);
                    System.out.println("이름 입력:");
                    String nameSearch = sc.next();
                    userInfo.nameSearch(nameSearch);
                    break;
                case 3:
                    System.out.println("메뉴 선택: " + choice);
                    System.out.println("역할 입력:");
                    String roleSearch = sc.next();
                    userInfo.printUserRole(roleSearch);
                    break;
                case 4:
                    System.out.println("총 사용자 수: " + userInfo.getCheckCount());
                    break;
                case 5:
                    System.out.println("삭제할 사용자의 입력을 입력 해주세요:");
                    String deleteName = sc.next();
                    userInfo.deleteUser(deleteName);
                    break;
                case 6:
                    userInfo.sortUserAge();
                    break;
                case 7:
                    System.out.println("관리자의 ID를 입력해주세요:");
                    String id = sc.next();
                    System.out.println("관리자의 PW를 입력해주세요:");
                    String pw = sc.next();
                    if(id.equals(Constants.ADMIN_ID) && pw.equals(Constants.ADMIN_PASSWORD)){
                        System.out.println("로그인이 되었습니다.");
                    } else {
                        System.out.println("다시 로그인 해주세요.");
                    }
                    break;
                case 8:
                    userInfo.allUserList();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("1~9중 하나를 입력해주세요.");
                    break;
            }
        }
    }
}
