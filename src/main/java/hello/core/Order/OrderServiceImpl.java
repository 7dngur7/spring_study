package hello.core.Order;

import hello.core.Annotaion.MainRatePolicy;
import hello.core.Discoount.DiscountPolicy;
import hello.core.Member.Member;
import hello.core.Member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;



    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,@MainRatePolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.getMember(memberId);
        int myItemPrice = discountPolicy.discount(member, itemPrice);


        return new Order(memberId, itemName, itemPrice, myItemPrice);
    }

    @Override
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }


}
