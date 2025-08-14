class Book {
    String title;
    String author;
    int price;
    int isbn;

    public Book(String t, String a, int p, int i) {
        title = t;
        author = a;
        price = p;
        isbn = i;
    }

    public void bookInfo() {
        System.out.println("제목: " + title + " 저자: " + author + " 가격: " + price + " ISBN: " + isbn);
    }

    public boolean isExpensiveBook() {
        return price >= 20000;
    }
}
class Library {
    Book[] bookList = new Book[100];
    int count = 0;

    public void addBooks(Book b) {
        bookList[count++] = b;
    }

    public void printBookInfo() {
        System.out.println("=== 전체 책 ===");
        for (int i = 0; i < count; i++) {
            bookList[i].bookInfo();
        }
    }

    public void printExpensiveBook() {
        System.out.println("=== 2만원 이상 책 ===");
        for (int i = 0; i < count; i++) {
            if (bookList[i].isExpensiveBook()) {
                bookList[i].bookInfo();
            }
        }
    }
}
public class Book_Management_System {
    public static void main(String[] args) {
        Library lib = new Library();
        Book b1 = new Book("자바의 정석", "남궁성", 20000, 123456);
        Book b2 = new Book("이펙티브 자바", "조슈아 블로크", 30000, 789123);
        Book b3 = new Book("Do it! 자바 프로그래밍 입문", "박은종", 29000, 654321);

        lib.addBooks(b1);
        lib.addBooks(b2);
        lib.addBooks(b3);

        lib.printBookInfo();
        lib.printExpensiveBook();
    }
}
