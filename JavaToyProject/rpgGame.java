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

// job은 Character 타입이므로, 현재는 Character 타입만 참조하고 있다.
// 하지만 Warrior 클래스는 Character를 상속하고, AttackAble 인터페이스도 구현하고 있기 때문에,
// job이 참조하는 실제 객체는 Warrior 타입이다.
// 따라서 AttackAble 인터페이스에 정의된 attackAble() 메서드를 사용하려면,
// job을 AttackAble 타입으로 형 변환(캐스팅) 해야 한다.
