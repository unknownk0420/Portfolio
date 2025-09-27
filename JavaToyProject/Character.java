class Character {
    protected final String name;
    protected final int attack;
    protected int hp;
    protected final String skill;

    public Character(String name, int attack, int hp, String skill) {
        this.name = name;
        this.attack = attack;
        this.hp = hp;
        this.skill = skill;
    }

    public void takeDamage(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }

    public void statusPrint() {
        System.out.println(name + " - HP: " + hp + ", 공격력: " + attack);
        System.out.println(name + "이(가) " + skill + "사용!");
    }
}
class Warrior extends Character {

    public Warrior(String name, int attack, int hp, String skill) {
        super(name, attack, hp, skill);
    }

    @Override
    public void statusPrint() {
        super.statusPrint();
        System.out.println(super.name + " - HP: " + super.hp + ", 공격력: " + (super.attack + 10));
    }

    public void attack(Mage mage) {
        System.out.println(name + "이(가) " + mage.name + "에게 기본 공격!");
        mage.takeDamage(this.attack);
        System.out.println(mage.name + " - HP: " + mage.hp + ", 공격력: " + mage.attack);
    }
}
class Mage extends Character{

    public Mage(String name, int attack, int hp, String skill){
        super(name, attack, hp, skill);
    }

    @Override
    public void statusPrint(){
        super.statusPrint();
        System.out.println(super.name + " - HP: " + (super.hp + 10) + ", 공격력: " + super.attack);
    }
}
class Archer extends Character {
    public Archer(String name, int attack, int hp, String skill) {
        super(name, attack, hp, skill);
    }

    @Override
    public void statusPrint() {
        super.statusPrint();
        System.out.println(super.name + " - HP: " + super.hp + ", 공격력: " + (super.attack * 2));
    }
}

public class Main {
    public static void main(String[] args) {
        Warrior warrior = new Warrior("전사", 15, 120, "분노의 일격");
        Mage mage = new Mage("마법사", 20, 80, "파이어볼");
        Archer archer = new Archer("궁수", 12, 100, "치명적인 화살");

        System.out.println();

        warrior.statusPrint();
        mage.statusPrint();
        archer.statusPrint();

        System.out.println();

        warrior.attack(mage);
    }
}
