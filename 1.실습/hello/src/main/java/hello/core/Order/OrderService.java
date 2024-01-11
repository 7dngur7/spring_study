package hello.core.Order;

import hello.core.Member.MemberRepository;
import hello.core.Order.Order;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);
    MemberRepository getMemberRepository();
}
