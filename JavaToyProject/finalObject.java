import java.util.*;

class Point{
    private final int x;
    private final int y;


    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Point move(int dx, int dy){
        return new Point(this.x + dx, this.y + dy);
    }

    @Override
    public String toString(){
        return "x Point: " + this.x + ", y Point: " + this.y;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Point point = (Point) object;
        return x == point.x && y == point.y;
    }
}

class PointSet {
    private List<Point> list = new ArrayList<>();

    public void add(Point p) {
        list.add(p);
    }

    public void remove(Point p) {
        list.remove(p);
    }

    public void contains(Point p) {
        if (list.contains(p)) {
            System.out.println("해당 X좌표가 존재합니다.");
        } else {
            System.out.println("해당 Y좌표가 존재합니다.");
        }
    }

    public void printAll() {
        System.out.println("현재 좌표를 출력합니다.");
        for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i) + " ");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Point point = new Point(-1, 5);
        Point move = point.move(-1, -2);
        PointSet set = new PointSet();

        System.out.println(point.equals(move));
        set.add(point);
        set.add(move);
        set.contains(point);
        set.remove(move);
        set.printAll();
    }
}

