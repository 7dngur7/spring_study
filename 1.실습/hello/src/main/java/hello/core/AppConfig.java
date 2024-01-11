package hello.core;

import hello.core.Discoount.DiscountPolicy;
import hello.core.Discoount.DiscountPolicyRateImpl;
import hello.core.Member.*;
import hello.core.Order.OrderService;
import hello.core.Order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(
                memberRepository()
        );
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy()
        );
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemberRepositoryImpl();
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        return new DiscountPolicyRateImpl();
    }
}
