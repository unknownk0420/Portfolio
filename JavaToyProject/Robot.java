interface Robot{
    void task();
}
class CleaningRobot implements Robot{
    private String name;

    public CleaningRobot(String name){
        this.name = name;
    }

    @Override
    public void task(){
        System.out.println(name + " is vacuuming the floor.");
    }
}

class GuardRobot implements Robot{
    private String name;

    public GuardRobot(String name){
        this.name = name;
    }

    @Override
    public void task(){
        System.out.println(name + " is patrolling the area.");
    }
}

class DeliverRobot implements Robot{
    private String name;

    public DeliverRobot(String name){
        this.name = name;
    }

    @Override
    public void task(){
        System.out.println(name + " is delivering a package.");
    }
}

public class Main {
    public static void main(String[] args) {
        Robot cleaningRobot = new CleaningRobot("CleanBot-01");
        Robot guardRobot = new GuardRobot("GuardBot-02");
        Robot deliverRobot = new DeliverRobot("DeliverBot-03");

        cleaningRobot.task();
        guardRobot.task();
        deliverRobot.task();
    }
}
