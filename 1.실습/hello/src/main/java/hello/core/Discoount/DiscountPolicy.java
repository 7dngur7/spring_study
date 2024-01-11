package hello.core.Discoount;

import hello.core.Member.Member;

public interface DiscountPolicy {
    int discount(Member member, int price);
}
