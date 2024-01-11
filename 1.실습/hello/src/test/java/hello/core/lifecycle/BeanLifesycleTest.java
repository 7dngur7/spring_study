package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifesycleTest {

    @Test
    public void lifecycleTest(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(configLifecycle.class);

        NetworkClient networkClient = applicationContext.getBean(NetworkClient.class);
        networkClient.disconnect();
    }

    @Configuration
    public static class configLifecycle{
//        @Bean(initMethod = "init", destroyMethod = "disconnect")
        @Bean
        NetworkClient printString(){
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("www.woohyuk.com");
            return networkClient;
        }
    }
}
