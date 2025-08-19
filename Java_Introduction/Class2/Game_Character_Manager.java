import java.util.Scanner;

class Games {
    private String characterName;
    private int bLevel;
    private int bHp;

    public Games(String characterName, int bLevel, int bHp) {
        this.characterName = characterName;
        this.bLevel = bLevel;
        this.bHp = bHp;
    }

    public String getCharacterName() {
        return this.characterName;
    }

    public int getBLevel() {
        return this.bLevel;
    }

    public int getBHp() {
        return this.bHp;
    }

    public void allCharacterList() {
        System.out.println("이름: " + getCharacterName() + ", 레벨: " + getBLevel() + ", HP: " + getBHp());
    }

    public void characterLevelUp() {
        this.bLevel++;
        this.bHp += 20;
        System.out.println(getCharacterName() + "이(가) 레벨업 했습니다. (레벨: " + getBLevel() + ", HP: " + getBHp());
    }

    public void characterHeal(){
        if(this.bHp < 100) {
            this.bHp = Math.min(this.bHp + 10, 100);
        } else{
            System.out.println("최대 체력입니다.");
        }
    }
}

public class Game_Character_Manager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Games[] games = new Games[3];
        int count = 0;
        while (true) {
            System.out.println("=== 게임 캐릭터 관리 시스템 ===");
            System.out.println("1. 캐릭터 생성");
            System.out.println("2. 전체 캐릭터 정보 보기");
            System.out.println("3. 캐릭터 레벨업");
            System.out.println("4. 캐릭터 HP 회복");
            System.out.println("5. 캐릭터 삭제");
            System.out.println("6. 종료");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("메뉴 선택: " + choice);
                    System.out.println("캐릭터 이름 입력:");
                    String name = sc.next();
                    System.out.println("초기 레벨 입력:");
                    int level = sc.nextInt();
                    System.out.println("초기 HP 입력:");
                    int hp = sc.nextInt();
                    Games game = new Games(name, level, hp);
                    games[count++] = game;
                    System.out.println("캐릭터가 생성 되었습니다.");
                    break;
                case 2:
                    if (count == 0) {
                        System.out.println("등록된 캐릭터가 없습니다.");
                    } else {
                        for (int i = 0; i < count; i++) {
                            games[i].allCharacterList();
                        }
                    }
                    break;
                case 3:
                    System.out.println("레벨업할 캐릭터 번호 입력 (1~" + count + "):");
                    int upIndex = sc.nextInt() - 1;
                    if (upIndex >= 0 && upIndex < count) {
                        games[upIndex].characterLevelUp();
                    } else {
                        System.out.println("잘못된 번호입니다.");
                    }
                    break;
                case 4:
                    int healIndex = sc.nextInt() - 1;
                    if (healIndex >= 0 && healIndex < count) {
                        games[healIndex].characterHeal();
                    } else {
                        System.out.println("잘못된 번호입니다.");
                    }
                    break;
                case 5:
                    System.out.println("삭제할 캐릭터 이름 입력:");
                    String nameCharacter = sc.next();
                    for (int i = 0; i < count; i++) {
                        if (nameCharacter.equals(games[i].getCharacterName())) {
                            for (int j = i; j < count - 1; j++) {
                                games[j] = games[j + 1];
                            }
                            games[count - 1] = null;
                            count--;
                            System.out.println("캐릭터가 삭제되었습니다.");
                            break;
                        }
                    }
                    System.out.println("해당 이름의 캐릭터가 없습니다.");
                    break;
                default:
                    System.out.println("1~6중 눌려주세요.");
                    break;
            }
        }
    }
}
