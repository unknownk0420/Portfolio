import java.util.*;

interface PlayerGameSystem {
    void playerBattle(Monster monster);
    void playerTakeDamage(Monster monster, int damage);
    void playerGameOver(Monster monster);
}

interface MonsterGameSystem {
    void monsterBattle(CharacterInfo characterInfo);
    void monsterTakeDamage(Item item, int damage);
}

class CharacterInfo {
    private final String playerName;
    private int playerHeathPoint;
    private int playerAttack;

    public CharacterInfo(String playerName, int playerHeathPoint, int playerAttack) {
        this.playerName = playerName;
        this.playerHeathPoint = playerHeathPoint;
        this.playerAttack = playerAttack;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getPlayerHeathPoint() {
        return playerHeathPoint;
    }

    public int getPlayerAttack() {
        return playerAttack;
    }

    public void setPlayerHeathPoint(Monster monster, int decreasePlayerHeathPoint) {
        this.playerHeathPoint -= decreasePlayerHeathPoint;
        if (this.playerHeathPoint > 0) {
            System.out.println(this.getPlayerName() + "님의 체력은 " + this.playerHeathPoint + "입니다.");
        } else {
            this.playerHeathPoint = 0;
            System.out.println(this.getPlayerName() + "님의 체력은 " + this.playerHeathPoint + "입니다.");
        }
    }

    public void setPlayerAttack(int itemAttackStatus) {
        this.playerAttack += itemAttackStatus;
    }
}

class Warrior extends CharacterInfo implements PlayerGameSystem {

    public Warrior(String playerName, int playerHeathPoint, int playerAttack) {
        super(playerName, playerHeathPoint, playerAttack);
    }

    @Override
    public void playerBattle(Monster monster) {
        System.out.println(this.getPlayerName() + " -> " + monster.getMonsterName() + "를 공격합니다.");
    }

    @Override
    public void playerTakeDamage(Monster monster, int damage) {
        this.setPlayerHeathPoint(monster, damage);
    }

    @Override
    public void playerGameOver(Monster monster) {
        if (this.getPlayerHeathPoint() < 1) {
            System.out.println("Game Over");
            return;
        }
        System.out.println();
    }
}

class Archer extends CharacterInfo implements PlayerGameSystem {

    public Archer(String playerName, int playerHeathPoint, int playerAttack) {
        super(playerName, playerHeathPoint, playerAttack);
    }

    @Override
    public void playerBattle(Monster monster) {
        System.out.println(this.getPlayerName() + " -> " + monster.getMonsterName() + "를 공격합니다.");
    }

    @Override
    public void playerTakeDamage(Monster monster, int damage) {
        this.setPlayerHeathPoint(monster, damage);
    }

    @Override
    public void playerGameOver(Monster monster) {
        if (this.getPlayerHeathPoint() < 1) {
            System.out.println("Game Over");
            return;
        }
        System.out.println();
    }

}

class Mage extends CharacterInfo implements PlayerGameSystem {

    public Mage(String playerName, int playerHeathPoint, int playerAttack) {
        super(playerName, playerHeathPoint, playerAttack);
    }

    @Override
    public void playerBattle(Monster monster) {
        System.out.println(this.getPlayerName() + " -> " + monster.getMonsterName() + "를 공격합니다.");
    }

    @Override
    public void playerTakeDamage(Monster monster, int damage) {
        this.setPlayerHeathPoint(monster, damage);
    }

    @Override
    public void playerGameOver(Monster monster) {
        if (this.getPlayerHeathPoint() < 1) {
            System.out.println("Game Over");
            return;
        }
        System.out.println();
    }
}

class Monster {
    private final String monsterName;
    private int monsterHeathPoint;
    private final int monsterAttack;

    public Monster(String monsterName, int monsterHeathPoint, int monsterAttack) {
        this.monsterName = monsterName;
        this.monsterHeathPoint = monsterHeathPoint;
        this.monsterAttack = monsterAttack;
    }

    public String getMonsterName() {
        return monsterName;
    }

    public int getMonsterHeathPoint() {
        return monsterHeathPoint;
    }

    public int getMonsterAttack() {
        return monsterAttack;
    }

    public void setMonsterHeathPoint(Item item, int decreaseMonsterHeathPoint) {
        this.monsterHeathPoint -= decreaseMonsterHeathPoint;
        if (this.monsterHeathPoint < 0) {
            System.out.println("격파 완료! 아이템 창을 확인해주세요.");
            item.setCount();
        } else {
            System.out.println(this.getMonsterName() + "의 체력은 " + this.monsterHeathPoint + "입니다.");
        }
    }

}

class Dragon extends Monster implements MonsterGameSystem {

    public Dragon(String monsterName, int monsterHeathPoint, int monsterAttack) {
        super(monsterName, monsterHeathPoint, monsterAttack);
    }

    @Override
    public void monsterBattle(CharacterInfo characterInfo) {
        if (this.getMonsterHeathPoint() > 0) {
            System.out.println(this.getMonsterName() + " -> " + characterInfo.getPlayerName() + "를 공격합니다.");
        }
    }

    @Override
    public void monsterTakeDamage(Item item, int damage) {
        this.setMonsterHeathPoint(item, damage);
    }
}

class Ghost extends Monster implements MonsterGameSystem {

    public Ghost(String monsterName, int monsterHeathPoint, int monsterAttack) {
        super(monsterName, monsterHeathPoint, monsterAttack);
    }

    @Override
    public void monsterBattle(CharacterInfo characterInfo) {
        System.out.println(this.getMonsterName() + " -> " + characterInfo.getPlayerName() + "를 공격합니다.");
    }

    @Override
    public void monsterTakeDamage(Item item, int damage) {
        this.setMonsterHeathPoint(item, damage);
    }

}

abstract class Item {
    private int count;

    public Item(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount() {
        this.count++;
    }

    public void setInitCount() {
        this.count = 0;
    }

    abstract public void fitItem(CharacterInfo characterInfo, String itemName);
}

class LegendaryItem extends Item {

    public LegendaryItem(int count) {
        super(count);
    }

    @Override
    public void fitItem(CharacterInfo characterInfo, String itemName) {
        if (this.getCount() > 0) {
            System.out.println(characterInfo.getPlayerName() + "님이 " + itemName + " 아이템을 착용했습니다.");
            characterInfo.setPlayerAttack(50);
            this.setInitCount();
        } else {
            System.out.println("몬스터를 먼저 처치하세요.");
        }
    }
}

class UniqueItem extends Item {

    public UniqueItem(int count) {
        super(count);
    }

    @Override
    public void fitItem(CharacterInfo characterInfo, String itemName) {
        if (this.getCount() > 0) {
            System.out.println(characterInfo.getPlayerName() + "님이 " + itemName + " 아이템을 착용했습니다.");
            characterInfo.setPlayerAttack(50);
            this.setInitCount();
        } else {
            System.out.println("몬스터를 먼저 처치하세요.");
        }
    }
}

public class Main {
    private static ArrayList<CharacterInfo> characterInfos = new ArrayList<>();
    private static ArrayList<PlayerGameSystem> playerGameSystems = new ArrayList<>();
    private static ArrayList<MonsterGameSystem> monsterGameSystems = new ArrayList<>();
    private static ArrayList<Monster> monsters = new ArrayList<>();
    private static ArrayList<Item> items = new ArrayList<>();

    private static void choiceNumVar(int choiceNum) {
        System.out.println(characterInfos.get(choiceNum - 1).getPlayerName() + "님이 전투합니다.");
    }

    private static void battle(int choiceNum, int monsterChoice) {
        System.out.println(monsters.get(monsterChoice - 1).getMonsterName() + "과 전투합니다.");
        playerGameSystems.get(choiceNum - 1).playerBattle(monsters.get(monsterChoice - 1));
        monsterGameSystems.get(monsterChoice - 1).monsterTakeDamage(items.get(monsterChoice - 1), characterInfos.get(choiceNum - 1).getPlayerAttack());
        monsterGameSystems.get(monsterChoice - 1).monsterBattle(characterInfos.get(choiceNum - 1));
        playerGameSystems.get(choiceNum - 1).playerTakeDamage(monsters.get(monsterChoice - 1), monsters.get(monsterChoice - 1).getMonsterAttack());
        playerGameSystems.get(choiceNum - 1).playerGameOver(monsters.get(monsterChoice - 1));
    }

    private static void itemFit(Item item, String itemName, int itemChoice) {
        item.fitItem(characterInfos.get(itemChoice - 1), itemName);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CharacterInfo warrior = new Warrior("전사", 1000, 30);
        CharacterInfo archer = new Archer("궁수", 800, 50);
        CharacterInfo mage = new Mage("마법사", 650, 65);

        Monster dragon = new Dragon("드래곤", 10000, 100);
        Monster ghost = new Ghost("고스트", 8000, 80);

        Item legendaryItem = new LegendaryItem(0);
        Item uniqueItem = new UniqueItem(0);

        characterInfos.add(warrior);
        characterInfos.add(archer);
        characterInfos.add(mage);

        playerGameSystems.add((PlayerGameSystem) warrior);
        playerGameSystems.add((PlayerGameSystem) archer);
        playerGameSystems.add((PlayerGameSystem) mage);


        monsterGameSystems.add((MonsterGameSystem) dragon);
        monsterGameSystems.add((MonsterGameSystem) ghost);

        monsters.add(dragon);
        monsters.add(ghost);

        items.add(legendaryItem);
        items.add(uniqueItem);

        int choiceNum = 0;

        while (true) {
            System.out.println("게임에 오신것을 환영합니다.");
            System.out.println("1. 캐릭터 선택");
            System.out.println("2. 전투");
            System.out.println("3. 아이템 착용");
            System.out.println("4. 게임 종료");

            int select = sc.nextInt();

            switch (select) {
                case 1:
                    System.out.println("캐릭터 선택을 해주세요.");
                    System.out.println("1. 전사");
                    System.out.println("2. 궁수");
                    System.out.println("3. 마법사");
                    int choice = sc.nextInt();

                    switch (choice) {
                        case 1:
                            System.out.println("전사를 선택하였습니다.");
                            choiceNum = 1;
                            break;
                        case 2:
                            System.out.println("궁수를 선택하였습니다.");
                            choiceNum = 2;
                            break;
                        case 3:
                            System.out.println("마법사를 선택하였습니다.");
                            choiceNum = 3;
                            break;
                        default:
                            System.out.println("잘못된 선택입니다.");
                    }
                    break;
                case 2:
                    choiceNumVar(choiceNum);
                    System.out.println("누구와 전투를 하시겠습니까?");
                    System.out.println("1. 드래곤");
                    System.out.println("2. 고스트");
                    int monsterChoice = sc.nextInt();
                    switch (monsterChoice) {
                        case 1:
                            battle(choiceNum, monsterChoice);
                            break;
                        case 2:
                            battle(choiceNum, monsterChoice);
                            break;
                        default:
                            System.out.println("잘못된 선택입니다.");
                    }
                    break;
                case 3:
                    System.out.println("어떤 아이템을 장착하시겠습니까?");
                    System.out.println("1. 전설");
                    System.out.println("2. 유니크");
                    int itemChoice = sc.nextInt();
                    switch (itemChoice) {
                        case 1:
                            itemFit(items.get(itemChoice - 1), "전설", itemChoice);
                            break;
                        case 2:
                            itemFit(items.get(itemChoice - 1), "유니크", itemChoice);
                            break;
                        default:
                            System.out.println("잘못된 선택입니다.");
                    }
                    break;
                case 4:
                    System.out.println("게임 종료를 합니다.");
                    break;
                default:
                    System.out.println("잘못된 선택입니다.");
            }
        }
    }
}
