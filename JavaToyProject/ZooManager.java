import java.util.*;

class Animal {
    String name;
    String kind;
    int age;

    Animal(String name, String kind, int age) {
        this.name = name;
        this.kind = kind;
        this.age = age;
    }

    void printInfo() {
        System.out.println("이름: " + name + " | 종류: " + kind + " | 나이: " + age);
    }
}

class ZooManager {
    Animal[] animals = new Animal[5];
    int count = 0;

    void animalList(Animal animal) {
        if (count < animals.length) {
            animals[count++] = animal;
        } else {
            System.out.println("더 이상 동물원 동물 목록을 추가할 수 없습니다.");
        }
    }

    void animalsListPrint() {
        for (int i = 0; i < count; i++) {
            animals[i].printInfo();
        }
    }

    void animalsAgeListPrint() {
        for (int i = 0; i < count; i++) {
            if (animals[i].age >= 5) {
                animals[i].printInfo();
            }
        }
    }

    boolean isDuplication(String name) {
        for (int i = 0; i < count; i++) {
            if (animals[i].name.equals(name)) {
                return true;
            }
        }
        return false;
    }

    void findAnimalName(Scanner sc) {
        System.out.print("동물의 이름을 입력해주세요: ");
        String name = sc.next();
        boolean found = false;
        System.out.println("=== 해당 동물 이름 목록 ===");
        for (int i = 0; i < count; i++) {
            if (animals[i].name.equals(name)) {
                animals[i].printInfo();
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("해당 동물의 이름을 찾을 수 없습니다.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ZooManager zooManager = new ZooManager();
        while (true) {
            System.out.println("1. 동물 목록");
            System.out.println("2. 5살 이상 동물 목록");
            System.out.println("3. 해당 동물 이름 목록");
            System.out.println("4. 종료");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    System.out.println("동물 이름을 입력하세요.");
                    String name = sc.next();
                    if(zooManager.isDuplication(name)){
                        System.out.println("중복된 동물 이름입니다.");
                        break;
                    }
                    System.out.println("동물의 종류를 입력하세요.");
                    String kind = sc.next();
                    System.out.println("동물의 나이를 입력하세요.");
                    int age = sc.nextInt();
                    Animal animal = new Animal(name, kind, age);
                    zooManager.animalList(animal);
                    System.out.println("===== 동물원 동물 목록 =====");
                    zooManager.animalsListPrint();
                    break;
                case 2:
                    System.out.println("=== 5살 이상 동물 ===");
                    zooManager.animalsAgeListPrint();
                    break;
                case 3:
                    zooManager.findAnimalName(sc);
                    break;
                case 4:
                    System.out.println("프로그램 종료");
                    return;
                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}
