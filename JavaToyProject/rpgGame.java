import java.util.*;

interface AttackAble {
    void attackAble(Character target);
}

abstract class Character {
    private String name;
    private int hp;
    private int attack;

    public Character(String name, int hp, int attack) {
        this.name = name;
        this.hp = hp;
        this.attack = attack;
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setHp(int damage) {
        this.hp -= damage;
        if (this.hp < 0) {
            this.hp = 0;
        }
    }
}

class Warrior extends Character implements AttackAble {

    public Warrior(String name, int hp, int attack) {
        super(name, hp, attack);
    }

    @Override
    public void attackAble(Character target) {
        System.out.println(getName() + "이 " + target.getName() + "을 공격합니다.");
        target.setHp(getAttack());
        System.out.println(target.getName() + "의 남은 HP: " + target.getHp());
    }
}

class Mage extends Character implements AttackAble {

    public Mage(String name, int hp, int attack) {
        super(name, hp, attack);
    }

    @Override
    public void attackAble(Character target) {
        System.out.println(getName() + "이 " + target.getName() + "을 공격합니다.");
        target.setHp(getAttack());
        System.out.println(target.getName() + "의 남은 HP: " + target.getHp());
    }

}

class Monster extends Character implements AttackAble {

    public Monster(String name, int hp, int attack) {
        super(name, hp, attack);
    }

    @Override
    public void attackAble(Character target) {
        System.out.println(getName() + "이 " + target.getName() + "을 공격합니다.");
        target.setHp(getAttack());
        System.out.println(target.getName() + "의 남은 HP: " + target.getHp());
    }
}

class CharacterFactory {
    public static Character create(int type, String name) {
        switch (type) {
            case 1:
                return new Warrior(name, 100, 30);
            case 2:
                return new Mage(name, 90, 40);
            default:
                System.out.println("없는 직업입니다.");
        }
        return null;
    }
}

class GameClass {
    private Scanner sc = new Scanner(System.in);
    private Character job;
    private Monster monster;

    public void start() {
        System.out.println("캐릭터를 선택하세요.");
        System.out.println("1. 전사 2. 마법사");
        int select = sc.nextInt();
        System.out.print("캐릭터 이름을 입력하세요: ");
        String name = sc.next();
        job = CharacterFactory.create(select, name);
        monster = new Monster("슬라임", 100, 10);
    }

    public void battle() {
        System.out.println("전투 시작!");
        AttackAble player = (AttackAble) job;
        AttackAble enemy = monster;
        while (job.getHp() > 0 && monster.getHp() > 0) {
            player.attackAble(monster);
            if (monster.getHp() <= 0) {
                break;
            }
            enemy.attackAble(job);
            System.out.println();
        }

        if (job.getHp() > 0) {
            System.out.println(job.getName() + "가 승리했습니다.");
        } else {
            System.out.println("슬라임이 승리했습니다.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        GameClass gameClass = new GameClass();
        gameClass.start();
        gameClass.battle();
    }
}

// AttackAble player = (AttackAble) job;
// job 변수는 Character 타입이므로,
// Warrior 객체 내부의 Character 부분만 참조할 수 있다.
// 따라서 attackAble() 메서드를 사용하려면 형 변환이 필요하다.
// 이때 형변환은 "업캐스팅"이 아닌 "다운캐스팅"이다.
// 왜냐하면 Character와 AttackAble은 서로 상속 관계(부모-자식)가 아니기 때문이다.
// 단, Warrior 클래스가 둘 다를 상속/구현하고 있기 때문에,
// job을 AttackAble로 안전하게 형변환할 수 있다.
