package hello.core.Discoount;


import hello.core.Annotaion.MainRatePolicy;
import hello.core.Discoount.DiscountPolicy;
import hello.core.Member.Grade;
import hello.core.Member.Member;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@MainRatePolicy
//@Primary
public class DiscountPolicyRateImpl implements DiscountPolicy {

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
