package hello.core;

import hello.core.Member.*;

public class OrderApp {

    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderServiceImpl();

        memberService.join(new Member(1L, "member1", Grade.num1));
        Order order = orderService.createOrder(1L, "item1", 10000);

        System.out.println("order : "+ order);
    }

}
