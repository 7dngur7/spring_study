package hello.core;

import hello.core.Discoount.DiscountPolicy;
import hello.core.Discoount.DiscountPolicyRateImpl;
import hello.core.Member.Grade;
import hello.core.Member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RateDiscountTest {
    DiscountPolicy discountPolicy = new DiscountPolicyRateImpl();
    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.") void vip_o() {
//given
        Member member = new Member(1L, "memberVIP", Grade.num1);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(1000);
    }
    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.") void vip_x() {
//given
        Member member = new Member(2L, "memberBASIC", Grade.num2);
        //when
        int discount = discountPolicy.discount(member, 10000);
        //then
        assertThat(discount).isEqualTo(0);
    }
}
