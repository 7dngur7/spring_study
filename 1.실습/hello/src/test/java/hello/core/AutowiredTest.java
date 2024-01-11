package hello.core;

import hello.core.Member.Member;
import jakarta.annotation.Nullable;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void AutowiredOption(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(TestBean.class);



    }

    static class TestBean{
        @Autowired(required = false)
        public void SetNoBean1(Member member){
            System.out.println("mem1"+member);
        }
        @Autowired
        public void SetNoBean2(@Nullable Member member){
            System.out.println("mem2"+member);
        }
        @Autowired
        public void SetNoBean3(Optional<Member> member){
            System.out.println("mem3"+member);
        }
    }

}
