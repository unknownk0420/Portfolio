class Book {
    //private는 접근 제어자로 클래스 외부에서는 직접 접근이 불가능하고 클래스 내부에서만 접근이 가능하다.
    //필드 선언
    private String customerName;
    private int[] seats;
    private int seatCount;

    //전체 필드를 초기화하는 주 생성자
    public Book(String customerName, int[] seats, int seatCount) {
        this.customerName = customerName;
        this.seats = seats;
        this.seatCount = seatCount;
    }

    //this()를 사용하여 주 생성자를 호출하고 중복된 코드를 줄이고 하나의 생성자에 초기화를 집중 시켜준다.
    public Book(String customerName, int... seats) {
        this(customerName, new int[10], 0);
        for (int i = 0; i < seats.length; i++) {
            this.seats[seatCount++] = seats[i];//만일 생성자 호출에서 customerName만 초기화 한다면 int seats은 new int[10]으로 초기화 된 상태이기 때문에 0으로 배열이 초기화가 된다.
        }
    }

    //메소드를 호출할 때 전달한 매개값을 받기 위해 매개 변수를 사용한다.
    public void bookSeat(int seatNumber) {//하나의 좌석을 예약하는 경우
        if (seatCount < seats.length) {
            seats[seatCount++] = seatNumber;
        } else {
            System.out.println("더 이상 좌석을 예약할 수 없습니다.");
        }
    }

    public void bookSeat(int... seatNumbers) {//여러 좌석을 예약하는 경우
        for (int i = 0; i < seatNumbers.length; i++) {
            if (seatCount < seats.length) {
                seats[seatCount++] = seatNumbers[i];
            } else {
                System.out.println("더 이상 좌석을 예약할 수 없습니다.");
            }
        }
    }

    public void printBookInfo() {
        System.out.println("예약자: " + this.customerName);
        System.out.println("좌석 번호:");
        for (int i = 0; i < seatCount; i++) {
            System.out.print(seats[i] + " ");
        }
        System.out.println();
    }
}
public class Cinema_Book_System {
    public static void main(String[] args) {
        Book b1 = new Book("홍길동");//Book 객체 생성과 생성자 매개 변수 대입
        b1.bookSeat(5);
        b1.bookSeat(6, 7, 8);

        Book b2 = new Book("이영희", 1, 2, 3);
        b1.printBookInfo();
        b2.printBookInfo();

    }
}
