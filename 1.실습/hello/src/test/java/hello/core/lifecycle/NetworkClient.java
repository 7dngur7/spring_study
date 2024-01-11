package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class NetworkClient {
    String url;

    public NetworkClient() {
        System.out.println("url = " + url);



    }

    public void connect(){
        System.out.println(
                "connect"+url
        );
    }

    public void call(String message){
        System.out.println("message = " + message + " "+url);
    }

    @PreDestroy

    public void disconnect(){
        System.out.println("disconnect"+url);
    }

    @PostConstruct
    public void init(){
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
