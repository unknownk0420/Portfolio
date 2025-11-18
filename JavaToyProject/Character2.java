import java.util.Scanner;

abstract class Character {
    private int num;
    private String name;
    private int attack;
    private int hp;
    private String skill;

    public Character(int num, String name, int attack, int hp, String skill) {
        this.num = num;
        this.name = name;
        this.attack = attack;
        this.hp = hp;
        this.skill = skill;
    }

    public int getNum(){
        return num;
    }

    public String getName(){
        return name;
    }

    public int getAttack() {
        return attack;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp){
        this.hp = hp;
    }

    public String getSkill() {
        return skill;
    }

    public void characterPrint(){
        System.out.println(name + " - HP: " + hp + ", 공격력: " + attack);
        System.out.println(name + "이(가) " + skill + "사용!");
    }

    abstract public void statusPrint();
}

class Battle extends Character {

    public Battle(int num, String name, int attack, int hp, String skill) {
        super(num, name, attack, hp, skill);
    }

    public void takeDamage(Character character, int damage) {
        int hp = character.getHp() - damage;

        if(hp < 0) {
            System.out.println("체력은 음수가 될 수 없습니다.");
            hp = 0;
        }
        character.setHp(hp);
        System.out.println(character.getName() + " - HP: " + hp + ", 공격력: " + character.getAttack());
    }

    public void attack(Character selected, Character[] characters) {
        if(selected == null){
            System.out.println("존재하지 않은 캐릭터 번호입니다.");
            return;
        }

        for (int i = 0; i < characters.length; i++) {
            if (i != 3 && characters[i] != selected) {
                System.out.println(selected.getName() + "이(가) " + characters[i].getName() + "에게 기본 공격!");
                takeDamage(characters[i], selected.getAttack());
            }
        }
    }
    public Character characterSelect(Character[] characters, int choice) {
        for (int i = 0; i < characters.length; i++) {
            if (choice == characters[i].getNum()) {
                return characters[i];
            }
        }
        return null;
    }

    @Override
    public void statusPrint() {

    }
}

class Warrior extends Character {

    public Warrior(int num, String name, int attack, int hp, String skill) {
        super(num, name, attack, hp, skill);
    }

    @Override
    public void statusPrint() {
        characterPrint();
        System.out.println(super.getName() + " - HP: " + super.getHp() + ", 공격력: " + (super.getAttack() + 10));
    }

}
class Mage extends Character{

    public Mage(int num, String name, int attack, int hp, String skill){
        super(num, name, attack, hp, skill);
    }

    @Override
    public void statusPrint(){
        characterPrint();
        System.out.println(super.getName() + " - HP: " + (super.getHp() + 10) + ", 공격력: " + super.getAttack());
    }
}
class Archer extends Character{
    public Archer(int num, String name, int attack, int hp, String skill) {
        super(num, name, attack, hp, skill);
    }

    @Override
    public void statusPrint() {
        characterPrint();
        System.out.println(super.getName() + " - HP: " + super.getHp() + ", 공격력: " + (super.getAttack() * 2));
    }
}

public class Main {
    static void main() {
        Scanner sc = new Scanner(System.in);
        Character[] characters = {new Warrior(1, "전사", 10, 200, "브랜디쉬"),
                new Mage(2, "마법사", 15, 100, "블리자드"),
                new Archer(3, "궁수", 20, 150, "폭풍의 시"),
                new Battle(0, null, 0, 0, null)
        };
        while (true) {
            System.out.println("1. 캐릭터 선택 및 전투시작");
            System.out.println("3. 프로그램 종료");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    System.out.println("어느 직업을 고르겠습니까?");
                    System.out.println("1. 전사");
                    System.out.println("2. 마법사");
                    System.out.println("3. 궁수");
                    int choice = sc.nextInt();
                    Character selected = ((Battle) characters[3]).characterSelect(characters, choice);
                    ((Battle)characters[3]).attack(selected, characters);
                    break;
                case 2:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("올바른 숫자를 다시 입력해주세요.");
            }
        }
    }
}
