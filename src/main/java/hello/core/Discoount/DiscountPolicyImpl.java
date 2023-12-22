package hello.core.Discoount;

import hello.core.Discoount.DiscountPolicy;
import hello.core.Member.Grade;
import hello.core.Member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("fixRatePolicy")
public class DiscountPolicyImpl implements DiscountPolicy {
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
