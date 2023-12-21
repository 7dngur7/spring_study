package hello.core.Member;

public class OrderServiceImpl implements OrderService{
    MemberRepository memberRepository;

//    DiscountPolicy discountPolicy = new DiscountPolicyImpl();
    DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.getMember(memberId);
        int myItemPrice = discountPolicy.discount(member, itemPrice);


        return new Order(memberId, itemName,itemPrice, myItemPrice);
    }
}
