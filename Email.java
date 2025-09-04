import java.util.Scanner;

class Constants {
    public static final int MAX_COUNT = 100;
    public static final int MAX_MEMBER_COUNT = 3;
}
class EmailSetting {
    private String email;

    public EmailSetting(String email) {
        this.email = email;
    }

    public String getEmail() {
        return this.email;
    }
}
class EmailManagement {
    private EmailSetting[] emailSettings = new EmailSetting[Constants.MAX_COUNT];
    private int count = 0;

    public void emailIdSignUp(EmailSetting emailSetting) {
        if (count < emailSettings.length) {
            emailSettings[count++] = emailSetting;
            System.out.println("이메일이 완성 되었습니다.");
        } else {
            System.out.println("더 이상 이메일을 만들 수 없습니다.");
        }
    }
}
class EmailMember {
    private EmailSetting[][] emailSettings = new EmailSetting[Constants.MAX_COUNT][Constants.MAX_MEMBER_COUNT];
    private String[] names = new String[Constants.MAX_COUNT];
    private int count = 0;

    public void addName(String name, EmailSetting[] settings) {
        if (count < Constants.MAX_COUNT) {
            names[count] = name;//현재 회원의 이름을 저장(1차원 인덱스 = 회원 번호)
            for (int i = 0; i < settings.length; i++) {
                if (i < Constants.MAX_MEMBER_COUNT) {
                    // 해당 회원의 이메일들을 2차원 배열에 저장
                    emailSettings[count][i] = settings[i];
                }
            }
            System.out.println("이름이 저장되었습니다.");
            count++;
        } else {
            System.out.println("더 이상 이름을 저장할 수 없습니다.");
        }
    }

    public void memberInfoModify(Scanner sc) {
        System.out.println("회원 정보를 변경하기 위해 0을 눌려 이름을 입력해 주세요.(그외 경우는 0을 제외한 숫자):");
        int select = sc.nextInt();
        if (select == 0) {
            System.out.println("이름을 입력해 주세요:");
            String name = sc.next();
            boolean found = false;
            for (int i = 0; i < count; i++) {
                if (names[i].equals(name)) {
                    found = true;
                    for (int j = 0; j < Constants.MAX_MEMBER_COUNT; j++) {
                        if (emailSettings[i][j] != null) {
                            String email = emailSettings[i][j].getEmail();
                            if (email.contains("@")) {
                                String localPart = email.substring(0, email.indexOf("@"));
                                String domainPart = email.substring(email.indexOf("@"));
                                System.out.println("현재 이메일은 " + email + "이고 현재 아이디는 " + localPart + "입니다.");
                                System.out.println("새 이메일로 변경 하시려면 0을 누르세요.");
                                int number = sc.nextInt();
                                if (number == 0) {
                                    System.out.println("새 아이디를 입력해 주세요.");
                                    String newId = sc.next();
                                    String newEmail = newId + domainPart;
                                    emailSettings[i][j] = new EmailSetting(newEmail);// 기존 객체를 새 EmailSetting 객체로 대체 (수정 반영)
                                    System.out.println("새 이메일은 " + newEmail + "이고 새 아이디는 " + newId + "입니다.");
                                }
                            } else {
                                System.out.println("잘못된 이메일 형식 입니다.");
                            }
                        }
                    }
                }
            }
            if (!found) {
                System.out.println("해당 이름이 없습니다.");
            }
        }
    }
}
public class Email {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EmailManagement emailManagement = new EmailManagement();
        EmailMember emailMember = new EmailMember();
        while (true) {
            System.out.println("1. 이메일 입력 및 정보 입력");
            System.out.println("2. 종료");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("이메일을 입력해 주세요:");
                    String email = sc.next();
                    EmailSetting emailSetting = new EmailSetting(email);
                    emailManagement.emailIdSignUp(emailSetting);
                    System.out.println("이름을 입력해 주세요:");
                    String name = sc.next();
                    EmailSetting[] emails = {
                            new EmailSetting(emailSetting.getEmail()),
                    };
                    emailMember.addName(name, emails);
                    emailMember.memberInfoModify(sc);
                    break;
                case 2:
                    System.out.println("프로그램을 종료합니다.");
                    return;
            }
        }
    }
}