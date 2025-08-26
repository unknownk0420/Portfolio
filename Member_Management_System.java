class Member {
    //외부에서 접근하지 못하고 같은 클래스 내부에서 사용하기 위해 private를 사용
    private String userName;
    private String email;
    private String password;

    //인스턴스 변수 즉 필드를 초기화 하기위해 생성자 생성
    public Member(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    //private는 public 메서드로 외부로부터 간접적인 접근이 가능하다. 따라서 get을 사용하여 외부로부터 값을 접근한다.
    public String getUserName() {
        return this.userName;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    //set도 위의 get 설명과 동일
    public void setEmailUpdate(String newEmail) {
        this.email = newEmail;
    }
}

class Member_Management {
    private Member[] members = new Member[100];
    private int count = 0;

    public void registerMember(Member member) {//참조 자료형을 매개 변수로 선언
        if (count < members.length) {
            members[count++] = member;
            System.out.println("회원가입 완료!");
        } else {
            System.out.println("회원가입 실패!");
        }
    }

    public Member getLogin(String userName, String password) {
        for (int i = 0; i < count; i++) {
            //member[i]에 저장된 Member 객체에서 userName과 password를 꺼내 비교
            if (members[i].getUserName().equals(userName) && members[i].getPassword().equals(password)) {
                System.out.println("로그인 성공!");
                return members[i];
            }
        }
        System.out.println("로그인 실패!");
        return null;//참조 자료형 배열의 초기값은 null이다.
    }

    public void printMemberList() {
        for (int i = 0; i < count; i++) {
            //members[i]에서 값을 가져와 getUserName에 값을 반환한다.
            System.out.println("Username: " + members[i].getUserName() + ", Email: " + members[i].getEmail());
        }
    }
}
public class Member_Management_System {
    public static void main(String[] args) {
        Member_Management member_management = new Member_Management();
        //참조 자료형 Member의 객체를 생성하여 registerMember의 매개 변수 값에 대입
        member_management.registerMember(new Member("kim123", "kim@example.com", "pass1"));
        member_management.registerMember(new Member("lee456", "lee@example.com", "pass2"));
        //Member의 메서드를 호출하기 위해 Member의 참조 자료형을 선언
        Member user = member_management.getLogin("kim123", "pass1");

        if (user != null) {
            System.out.println("이메일 변경 전: " + user.getEmail());
            user.setEmailUpdate("kim@newmail.com");
            System.out.println("이메일 변경 후: " + user.getEmail());
        }

        member_management.getLogin("kim123", "pass1");

        member_management.printMemberList();
    }
}
