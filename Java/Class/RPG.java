import java.util.*;
class Constants{
    protected final static int MAX_CHARACTER_COUNT = 100;
    protected final static int MAX_LEVEL = 10;
}
class Character {
    private String name;
    private int level;
    private int exp = 0;

    public Character(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setExp(int amount) {
        if (level >= Constants.MAX_LEVEL) {
            System.out.println("레벨업을 더 이상 할 수 없습니다.");
            return;
        }
        exp += amount;
        System.out.println("경험치 +" + amount + "획득!");
        while (exp >= 100 && level < Constants.MAX_LEVEL) {
            exp -= 100;
            level += 1;
            System.out.println("레벨업을 하였습니다! 현재 레벨 LV: " + level);
        }
    }
}
class CharacterInfo {
    private Character[] characters = new Character[Constants.MAX_CHARACTER_COUNT];
    private int count = 0;
    private static CharacterInfo characterInfo = new CharacterInfo();

    private CharacterInfo() {

    }

    public void characterCreation(Character character) {
        if (count < characters.length) {
            characters[count++] = character;
        } else {
            System.out.println("캐릭터 생성할 수 없습니다.");
        }
    }

    public Character modify(String name) {
        for (int i = 0; i < count; i++) {
            if (characters[i].getName().equals(name)) {
                return characters[i];
            }
        }
        return null;
    }

    public boolean isNameExists(String name) {
        return modify(name) != null;
    }

    public void deleteCharacter(String name) {
        for (int i = 0; i < count; i++) {
            if (characters[i].getName().equals(name)) {
                for (int j = i; j < count - 1; j++) {
                    characters[j] = characters[j + 1];
                }
                characters[count--] = null;
                System.out.println("해당 캐릭터가 삭제되었습니다.");
                return;
            }
        }
        System.out.println("해당 캐릭터가 없습니다.");
    }

    public void characterList() {
        for (int i = 0; i < count; i++) {
            System.out.println("이름: " + characters[i].getName() + ", 레벨: " + characters[i].getLevel());
        }
    }

    public static CharacterInfo getInstance() {
        return characterInfo;
    }
}
public class RPG {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CharacterInfo characterInfo = CharacterInfo.getInstance();
        while (true) {
            System.out.println("1. 캐릭터 생성");
            System.out.println("2. 캐릭터 이름 바꾸기");
            System.out.println("3. 캐릭터 삭제");
            System.out.println("4. 몬스터 처치");
            System.out.println("5. 캐릭터 정보 조회");
            System.out.println("6. 게임 종료");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("메뉴 선택: " + choice);
                    System.out.println("캐릭터를 생성해 주세요.");
                    System.out.println("이름:");
                    String name = sc.next();
                    if (characterInfo.isNameExists(name)) {
                        System.out.println("해당 캐릭터 이름을 쓸 수 없습니다.");
                        break;
                    }
                    Character character = new Character(name, 1);
                    characterInfo.characterCreation(character);
                    System.out.println("캐릭터 생성 완료!");
                    break;
                case 2:
                    System.out.println("메뉴 선택: " + choice);
                    System.out.println("기존 캐릭터 이름을 입력해 주세요:");
                    String oldName = sc.next();
                    Character characterNameModify = characterInfo.modify(oldName);
                    if (characterNameModify != null) {
                        System.out.println("기존 캐릭터 이름을 찾았습니다! 새로운 캐릭터 이름을 생성하세요!");
                        String nameModify = sc.next();
                        characterNameModify.setName(nameModify);
                    } else {
                        System.out.println("해당 이름을 찾지 못했습니다.");
                    }
                    break;
                case 3:
                    System.out.println("메뉴 선택: " + choice);
                    System.out.println("삭제할 캐릭터 이름을 입력하세요:");
                    String deleteName = sc.next();
                    characterInfo.deleteCharacter(deleteName);
                    break;
                case 4:
                    System.out.println("메뉴 선택: " + choice);
                    System.out.println("어떤 캐릭터로 몬스터 처지를 할 것인가요?");
                    String namePlay = sc.next();
                    Character monster = characterInfo.modify(namePlay);
                    if (monster == null) {
                        System.out.println("해당 이름의 캐릭터가 없습니다.");
                        break;
                    }
                    System.out.println("다음 중 도전하고 싶은 몬스터를 고르세요.");
                    System.out.println("1. 보스 몬스터");
                    System.out.println("2. 일반 몬스터");
                    int select = sc.nextInt();
                    switch (select) {
                        case 1:
                            if (monster.getLevel() == Constants.MAX_LEVEL) {
                                System.out.println("보스 몬스터 격파!");
                            } else {
                                System.out.println("레벨업을 더하세요!");
                            }
                            break;
                        case 2:
                            monster.setExp(50);
                            break;
                    }
                    break;
                case 5:
                    characterInfo.characterList();
                    break;
                case 6:
                    System.out.println("게임을 종료합니다.");
                    return;
                default:
                    System.out.println("1~6중에서 눌려주세요.");
                    break;
            }
        }
    }
}
