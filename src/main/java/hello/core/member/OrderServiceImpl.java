package hello.core.Member;

public class OrderServiceImpl implements OrderService{
    MemberRepository memberRepository = new MemberRepositoryImpl();
    DiscountPolicy discountPolicy = new DiscountPolicyImpl();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.getMember(memberId);
        int myItemPrice = discountPolicy.discount(member, itemPrice);


        return new Order(memberId, itemName,itemPrice, myItemPrice);
    }
}
