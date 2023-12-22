package hello.core;

public class SingleToneService {

    private static final SingleToneService instance = new SingleToneService();

    public static SingleToneService getInstance() {
        return instance;
    }

    //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingleToneService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
