package hello.core.Member;

public class DiscountPolicyImpl implements DiscountPolicy{
    private int discountFixAmount = 1000;

    @Override
    public int discount(Member member, int price) {
        Grade memberGrade = member.getGrade();
        if(memberGrade==Grade.num1){
            return discountFixAmount;
        }
        else{
            return 0;

        }
    }
}
