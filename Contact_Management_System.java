import java.util.*;

class Contact {
    private String name;
    private String contact;
    private String email;

    public Contact(String n, String c, String e) {
        name = n;
        contact = c;
        email = e;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public String getEmail() {
        return email;
    }
}
class Contact_Management {
    private Contact[] contacts = new Contact[100];
    private int count = 0;

    public void setAddContact(Contact contact) {
        if (count < contacts.length) {
            contacts[count++] = contact;
            System.out.println("연락처가 추가되었습니다.");
        } else {
            System.out.println("저장 공간이 가득 찼습니다.");
        }
    }

    public void setContactsList() {
        if (count == 0) {
            System.out.println("연락처가 없습니다.");
            return;
        }
        System.out.println("연락처 목록: ");
        for (int i = 0; i < count; i++) {
            System.out.println("이름: " + contacts[i].getName() + ", 전화번호: " + contacts[i].getContact() + ", 이메일: " + contacts[i].getEmail());
        }
    }

    public void setNameSearch(String name) {
        for (int i = 0; i < count; i++) {
            if (name.equals(contacts[i].getName())) {
                System.out.println("검색 결과: ");
                System.out.println("이름: " + contacts[i].getName() + ", 전화번호: " + contacts[i].getContact() + ", 이메일: " + contacts[i].getEmail());
            }
        }
        System.out.println("해당 이름의 연락처를 찾을 수 없습니다.");
    }

    public void setDelete(String name) {
        for (int i = 0; i < count; i++) {
            if (name.equals(contacts[i].getName())) {
                for (int j = i; j < count - 1; j++) {
                    contacts[j] = contacts[j + 1];
                }
                contacts[count - 1] = null;
                count--;
                System.out.println("연락처가 삭제되었습니다.");
                return;
            }
        }
        System.out.println("해당 이름의 연락처가 존재하지 않습니다.");
    }
}

public class Contact_Management_System {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Contact_Management contactManagement = new Contact_Management();
        while (true) {
            System.out.println("=== 연락처 관리 시스템 ===");
            System.out.println("1. 연락처 추가");
            System.out.println("2. 연락처 목록 보기");
            System.out.println("3. 이름으로 검색");
            System.out.println("4. 연락처 삭제");
            System.out.println("5. 종료");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("선택: " + choice);
                    System.out.println("이름: ");
                    String name1 = sc.next();
                    System.out.println("전화번호: ");
                    String contact = sc.next();
                    System.out.println("이메일: ");
                    String email = sc.next();
                    Contact contactClass = new Contact(name1, contact, email);
                    contactManagement.setAddContact(contactClass);
                    break;
                case 2:
                    System.out.println("선택: " + choice);
                    contactManagement.setContactsList();
                    break;
                case 3:
                    System.out.println("검색할 이름: ");
                    String name2 = sc.next();
                    contactManagement.setNameSearch(name2);
                    break;
                case 4:
                    System.out.println("선택: " + choice);
                    System.out.println("삭제할 이름: ");
                    String name3 = sc.next();
                    contactManagement.setDelete(name3);
                    break;
                case 5:
                    System.out.println("프로그램 종료");
                    return;
                default:
                    System.out.println("1~5 중 눌려주세요.");
                    break;
            }

        }
    }
}
