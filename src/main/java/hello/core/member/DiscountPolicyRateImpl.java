package hello.core.Member;

public class DiscountPolicyRateImpl implements DiscountPolicy{

    int discountRateAmount = 10;
    @Override
    public int discount(Member member, int price) {
        Grade memberGrade = member.getGrade();
        if(memberGrade==Grade.num1){
            return price*discountRateAmount/100;
        }
        else{
            return 0;

        }
    }
}
