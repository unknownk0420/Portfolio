import java.util.*;

class User{
    private String id;
    private String pw;
    private static ArrayList<User> memberInfo = new ArrayList<>();
    private static User currentUser;

    public User(String id, String pw){
        this.id = id;
        this.pw = pw;
    }

    public String getId() {
        return id;
    }

    public String getPw() {
        return pw;
    }

    public void addFriend(Scanner sc){
        System.out.println("회원가입을 시작합니다.");
        System.out.println("아이디를 입력해주세요.");
        String id = sc.next();
        checkIdDuplicate(id);
        if(checkIdDuplicate(id)){
            System.out.println("중복된 아이디가 존재합니다.");
            return;
        }
        System.out.println("비밀번호를 입력해주세요.");
        String pw = sc.next();
        memberInfo.add(new User(id, pw));
        System.out.println("회원가입 성공!");
    }

    public void removeFriend(Scanner sc) {
        if (id != null && pw != null) {
            System.out.println("회원탈퇴를 시작합니다.");
            System.out.println("아이디를 입력해주세요.");
            String id = sc.next();
            System.out.println("비밀번호를 입력해주세요.");
            String pw = sc.next();
            memberInfo.remove(new User(id, pw));
            System.out.println("회원탈퇴 성공!");
            return;
        }
        System.out.println("회원가입을 먼저 해주세요.");
    }

    public boolean checkIdDuplicate(String id){
        for(int i = 0; i < memberInfo.size(); i++) {
            if (id.equals(memberInfo.get(i).getId())){
                return true;
            }
        }
        return false;
    }

    public void login(Scanner sc) {
        System.out.println("로그인을 시작합니다.");
        System.out.print("아이디: ");
        String id = sc.next();
        System.out.print("비밀번호: ");
        String pw = sc.next();
        for (int i = 0; i < memberInfo.size(); i++) {
            if (id.equals(memberInfo.get(i).getId()) && pw.equals(memberInfo.get(i).getPw())) {
                currentUser = memberInfo.get(i);
                System.out.println("로그인 성공!");
                return;
            }
        }
        System.out.println("로그인 실패!");
    }

    public void logOut() {
        if(currentUser != null){
            System.out.println(currentUser.getId() + "님이 로그아웃했습니다.");
            currentUser = null;
        } else {
            System.out.println("현재 로그인된 사용자가 없습니다.");
        }
    }

    public static boolean isLoggedIn(){
        return currentUser != null;
    }

}


abstract class Post{
    private LinkedList<String> comments = new LinkedList<>();

    public void addComment(Scanner sc){
        String e = sc.next();
        comments.push(e);
    }

    public void commentDisplay(){
        for(int i = 0; i < comments.size(); i++){
            System.out.println(comments.get(i));
        }
    }

    public abstract void displayPost(Scanner sc);
}

class TextPost extends Post{

    @Override
    public void displayPost(Scanner sc) {
        System.out.print("[Text]: ");
        addComment(sc);
    }
}

class ImagePost extends Post{

    @Override
    public void displayPost(Scanner sc) {
        System.out.print("[Image]: ");
        addComment(sc);
    }
}

class VideoPost extends Post{

    @Override
    public void displayPost(Scanner sc) {
        System.out.print("[Video]: ");
        addComment(sc);
    }
}

class Search {

    public void selectDisplay(Post[] post, Scanner sc){
        System.out.println("1. Text 댓글 보기");
        System.out.println("2. Image 댓글 보기");
        System.out.println("3. Video 댓글 보기");
        System.out.print("올바른 수를 입력해주세요: ");
        int choice = sc.nextInt();
        for(int i = 0; i < post.length; i++) {
            if(choice == i + 1) {
                post[i].commentDisplay();
            }
        }
    }
}
class Write{
    public void selectWrite(Post[] post, Scanner sc){
        System.out.println("1. Text 댓글 쓰기");
        System.out.println("2. Image 댓글 쓰기");
        System.out.println("3. Video 댓글 쓰기");
        System.out.print("올바른 수를 입력해주세요: ");
        int choice = sc.nextInt();
        for(int i = 0; i < post.length; i++) {
            if(choice == i + 1) {
                post[i].displayPost(sc);
            }
        }
    }
}
public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        Post[] post = {
            new TextPost(), new ImagePost(), new VideoPost()
        };
        User user = new User(null, null);
        Search search = new Search();
        Write write = new Write();
        while (true) {
            System.out.println("1. 회원가입");
            System.out.println("2. 회원탈퇴");
            System.out.println("3. 로그인");
            System.out.println("4. 로그아웃");
            System.out.println("5. 댓글 쓰기");
            System.out.println("6. 댓글 보기");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    user.addFriend(sc);
                    break;
                case 2:
                    user.removeFriend(sc);
                    break;
                case 3:
                    user.login(sc);
                    break;
                case 4:
                    user.logOut();
                    break;
                case 5:
                    if(User.isLoggedIn()) {
                        write.selectWrite(post, sc);
                    } else {
                        System.out.println("로그인 후 사용가능!");
                    }
                    break;
                case 6:
                    if(User.isLoggedIn()) {
                        search.selectDisplay(post, sc);
                    } else {
                        System.out.println("로그인 후 사용가능!");
                    }
                default:
                    System.out.println("올바른 숫자를 입력해주세요.");
            }
        }
    }
}
