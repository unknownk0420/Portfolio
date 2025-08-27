//set에서 갱신된 값을 다시 get으로 반환함
import java.util.*;

class GameSettings{
    private static GameSettings instance = new GameSettings();
    private int volume;
    private String resolution;

    private GameSettings(){
        this.volume = 70;
        this.resolution = "1080p";
    }
    public static GameSettings getInstance(){
        return instance;
    }
    public int getVolume(){
        return this.volume;
    }
    public String getResolution(){
        return this.resolution;
    }
    public void setVolume(int newVolume){
        this.volume = newVolume;
    }
    public void setResolution(String newResolution){
        this.resolution = newResolution;
    }
}
class Audio{
    GameSettings gameSettings = GameSettings.getInstance();
    public void printVolume(){
        System.out.println("현재 볼륨: " + gameSettings.getVolume());
    }
}
class Resolution{
    GameSettings gameSettings = GameSettings.getInstance();
    public void printResolution(){
        System.out.println("현재 해상도: " + gameSettings.getResolution());
    }
}
public class Game_Settings_Manager {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GameSettings gameSettings = GameSettings.getInstance();

        System.out.println("게임 설정을 시작합니다.");
        System.out.println("원하는 볼륨을 입력하세요(0~100):");
        int newVolume = sc.nextInt();
        gameSettings.setVolume(newVolume);

        System.out.println("원하는 해상도를 입력하세요(예: 1080p):");
        String newResolution = sc.next();
        gameSettings.setResolution(newResolution);

        Audio audio = new Audio();
        audio.printVolume();

        Resolution resolution = new Resolution();
        resolution.printResolution();

        GameSettings gameSettings1 = GameSettings.getInstance();
        GameSettings gameSettings2 = GameSettings.getInstance();

        if (gameSettings1 == gameSettings2){
            System.out.println("같은 GameSettings 인스턴스를 사용 중입니다.");
        } else{
            System.out.println("Not Found");
        }
    }
}
