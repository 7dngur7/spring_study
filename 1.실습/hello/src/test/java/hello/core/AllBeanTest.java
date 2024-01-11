package hello.core;

import hello.core.Discoount.DiscountPolicy;
import hello.core.Member.Grade;
import hello.core.Member.Member;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ApplicationObjectSupport;

import java.util.List;
import java.util.Map;

public class AllBeanTest {

    @Test
    void findAllBean() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
        DiscountService discountService = applicationContext.getBean(DiscountService.class);

        Member member = new Member(1L, "woohyuk", Grade.num1);
        int discountPrice = discountService.Discount(member, 80000, "discountPolicyImpl");
        int discountRatePrice = discountService.Discount(member, 50000, "discountPolicyRateImpl");

        System.out.println("discountPrice = " + discountPrice);
        System.out.println("discountRatePrice = " + discountRatePrice);
    }

    //    @RequiredArgsConstructor
    static class DiscountService {
        private final Map<String, DiscountPolicy> beanMap;
        private final List<DiscountPolicy> beanList;

        public DiscountService(Map<String, DiscountPolicy> policyMap,
                               List<DiscountPolicy> policies) {
            this.beanMap = policyMap;
            this.beanList = policies;
            System.out.println("policyMap = " + policyMap);
            System.out.println("policies = " + policies);
        }


        public int Discount(Member member, int i, String discountPolicyRateImpl) {
            return beanMap.get(discountPolicyRateImpl).discount(member, i);

        }
    }
}
