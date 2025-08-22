class Track {
    //필드 선언
    private String title;
    private String artist;
    private int duration;

    //필드를 초기화 하기 위해 생성자 생성 단, this는 가독성 때문에 쓴다. 왜냐하면 title = title이 되면 자신 자신에다가 대입하는 것이니간
    public Track(String title, String artist, int duration) {
        this.title = title;
        this.artist = artist;
        this.duration = duration;
    }

    public void play() {
        System.out.println("Now playing: " + title + " by " + artist + " (" + duration + "s)");
    }

    //값은 반환하기 위해 get메서드를 쓴다.
    public String getTitle() {
        return title;
    }
}

class PlayList {
    //Track이 쓰인 이유는 Track 클래스를 참조하기 위해서고 private가 쓰인 이유는 클래스 외부에서는 직접 접근 불가능하고 클래스 내부에서만 접근이 가능하도록 제한한다.
    private Track[] tracks;
    private String name;

    //생성자 오버로딩은 객체의 필드 즉 인스턴스 변수를 다양하게 초기화하기 위해서 쓴다.
    //다른 생성자 호출은 중복된 코드를 줄이기 위해 쓰이고 한 생성자에 집중하여 초기화 하기 위해 쓴다.
    public PlayList() {
        this("Untitled Playlist");
    }

    //new Track[0]을 쓴 이유는 Track 객체(인스턴스)가 하나도 없는 상태에서도 안전하게 배열을 전달하기 위해 사용한다.
    public PlayList(String name) {
        this(name, new Track[0]);
    }

    //Track... tracks는 가변길이 매개 변수로 매개 변수의 갯수와 상관없이 매개값을 줄 수 있다.
    public PlayList(String name, Track... tracks) {
        this.name = name;
        this.tracks = tracks;
    }

    public void addTrack(Track track) {//메소드를 호출할 때 전달한 매개값을 받기 위해 매개 변수를 사용한다.
        Track[] newTracks = new Track[tracks.length + 1];//한 곡을 추가하기 위해 +1을 해준다.
        for (int i = 0; i < tracks.length; i++) {
            newTracks[i] = tracks[i];//기존의 곡들을 배열 newTracks에 대입한다.
        }
        newTracks[tracks.length] = track;//한 곡을 추가한다.
        tracks = newTracks;//기존의 배열 tracks와 일관성을 위해 newTracks 배열과 교환을 한다.
    }

    public void addTrack(Track... newTracks) {//가변길이 매개 변수는 여러 개의 값을 대입이 가능하다.
        Track[] updated = new Track[tracks.length + newTracks.length];//기존의 곡 + 새로운 여러 곡
        for (int i = 0; i < tracks.length; i++) {//기존의 곡들을 updated 배열에 대입
            updated[i] = tracks[i];
        }
        for (int i = 0; i < newTracks.length; i++) {
            updated[tracks.length + i] = newTracks[i];//새로운 곡들을 기존의 곡들에 추가한다. 그렇기 때문에 newTracks배열을 써준 것이다.
        }
        tracks = updated;
    }

    public void play() {
        System.out.println("Playing playlist" + name);
        if (tracks.length == 0) {
            System.out.println("Error");
        } else {
            for (int i = 0; i < tracks.length; i++) {
                tracks[i].play();//Track 클래스의 i번째 play 메소드를 호출
            }
        }
    }

    public void play(int index) {
        if (index >= 0 && index < tracks.length) {
            tracks[index].play();
        } else {
            System.out.println("Error");
        }
    }

    public void play(String title) {
        for (int i = 0; i < tracks.length; i++) {
            if (tracks[i].getTitle().equals(title)) {
                tracks[i].play();
            }
        }
        System.out.println("Not Found");
    }

}

public class Music_Player {
    public static void main(String[] args) {
        //참조 변수 t1~t3과 playList1~playList3는 생성자의 인스턴스 변수를 초기화를 하는 역할을 함
        Track t1 = new Track("Dreaming", "Nina", 180);
        Track t2 = new Track("Sky High", "Elevate", 210);
        Track t3 = new Track("Sunset Road", "Acoustic Vibe", 240);

        PlayList playList1 = new PlayList();
        PlayList playList2 = new PlayList("Chill Vibes");
        PlayList playList3 = new PlayList("Workout Hits", t1, t2);

        //PlayList 클래스의 addTrack의 매개변수에 t1을 대입한다. tracks[i]의 값은 t1~t3이고 newTracks[tracks.length] = track;는 t1의 곡 즉 한 곡을 대입하는 것이다.
        playList1.addTrack(t1);
        playList2.addTrack(t2, t3);

        playList1.play();
        System.out.println("---------");
        playList2.play(1);
        System.out.println("---------");
        playList3.play("Dreaming");
    }
}

