package hello.itemservice.messagesource;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@SpringBootTest
public class MessageSourceTest {
    @Autowired
    MessageSource ms;

    @Test
    public void helloMessage(){
        String hello = ms.getMessage("hello", null, null);
        assertThat(hello).isEqualTo("안녕");
    }

    @Test
    void notFoundMessageCode() {
        assertThatThrownBy(()->ms.getMessage("no_message", null,null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    void notFoundMessageCodeDefaultMessage() {
        String message = ms.getMessage("no_message", null, "no message", null);
        assertThat("no message").isEqualTo(message);

    }
    @Test
    void argumentMessage() {
        String message = ms.getMessage("hello.name", new Object[]{"Spring"}, null);
        assertThat("안녕 Spring").isEqualTo(message);


    }

    @Test
    void enLang() {
        String message = ms.getMessage("hello", null, Locale.US);
        assertThat("hello").isEqualTo(message);
    }

}
