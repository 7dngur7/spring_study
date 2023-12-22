package hello.core;

import hello.core.Member.*;
import hello.core.Order.Order;
import hello.core.Order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = applicationContext.getBean("MemberService",MemberService.class);
        OrderService orderService = applicationContext.getBean("OrderService",OrderService.class);

        memberService.join(new Member(1L, "member1", Grade.num1));
        Order order = orderService.createOrder(1L, "item1", 10000);

        System.out.println("order : "+ order);
    }

}
