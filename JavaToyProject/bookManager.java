import java.util.*;

class Constants {
    final static int BOOK_COUNT = 10;
    final static int EBOOK_COUNT = 10;
}

class Book {
    private String name;
    private int price;
    private static int bookCount;
    private static Book[] books = new Book[Constants.BOOK_COUNT];
    private static int indexBook;

    public Book(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public static int getBookCount() {
        return bookCount;
    }

    public void addBook(Book book) {
        if (indexBook < Constants.BOOK_COUNT) {
            books[indexBook++] = book;
            bookCount++;
            System.out.println("책을 추가하였습니다.");
        } else {
            System.out.println("더이상 책을 추가할 수 없습니다.");
        }
    }

    public void inputBook(Scanner sc) {
        System.out.print("책 이름 입력: ");
        String name = sc.next();
        System.out.print("책 가격 입력: ");
        int price = sc.nextInt();
        addBook(new Book(name, price));
    }

    public static void printBook() {
        System.out.println("----- 일반 책 -----");
        for (int i = 0; i < indexBook; i++) {
            System.out.println(books[i]);
        }
    }

    @Override
    public String toString() {
        return "책 이름: " + name + ", 책 가격: " + price;
    }
}

class Ebook extends Book {
    private static Ebook[] ebooks = new Ebook[Constants.EBOOK_COUNT];
    private static int indexEbook;
    private static int sum;
    private String security;

    public Ebook(String name, int price, String security) {
        super(name, price);
        this.security = security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public static int getSum() {
        return sum;
    }

    public void addEbook(Ebook ebook) {
        if (indexEbook < Constants.EBOOK_COUNT) {
            ebooks[indexEbook++] = ebook;
            sum++;
            System.out.println("책을 추가하였습니다.");
        } else {
            System.out.println("더이상 전자책을 추가할 수 없습니다.");
        }
    }

    public void inputEbook(Scanner sc) {
        System.out.print("책 이름 입력: ");
        String name = sc.next();
        System.out.print("책 가격 입력: ");
        int price = sc.nextInt();
        System.out.print("보안 키 입력: ");
        String security = sc.next();
        setSecurity(security);
        addEbook(new Ebook(name, price, this.security));
    }

    @Override
    public String toString() {
        return "책 이름: " + super.getName() + ", 책 가격: " + super.getPrice() + ", 보안 키: " + this.security;
    }

    public static void printEbook() {
        System.out.println("----- 전자 책 -----");
        for (int i = 0; i < indexEbook; i++) {
            System.out.println(ebooks[i]);
        }
    }

}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Ebook ebook = new Ebook(null, 0, null);
        while (true) {
            System.out.println("----- 책 관리 프로그램 -----");
            System.out.println("1. 책 등록");
            System.out.println("2. 책 목록");
            System.out.print("입력: ");
            int select = sc.nextInt();
            switch (select) {
                case 1:
                    System.out.println("어떤 유형의 책을 등록하시겠습니까? (1. 책, 2. 전자 책)");
                    System.out.print("입력: ");
                    int choice = sc.nextInt();
                    switch (choice) {
                        case 1:
                            ebook.inputBook(sc);
                            break;
                        case 2:
                            ebook.inputEbook(sc);
                            break;
                    }
                    break;
                case 2:
                    System.out.println("책은 총 " + (Ebook.getSum() + Book.getBookCount()) + "권 입니다.");
                    Ebook.printBook();
                    Ebook.printEbook();
                    break;
                case 3:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("다시 입력해주세요.");
            }
        }
    }
}
