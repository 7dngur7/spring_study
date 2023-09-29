package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class OrderApp {
    public static void main(String[] args) {

        OrderService orderService = new OrderServiceImpl();
        MemberService memberService = new MemberServiceImpl();
        Long memberId = 1L;
        Member memberA= new Member(memberId, "wooHyuk", Grade.VIP);
        memberService.join(memberA);



        Order order = orderService.createOrder(memberId, "itemA", 10000);
        System.out.println(order.calculatePrice());
    }
}
