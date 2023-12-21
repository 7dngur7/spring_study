package hello.core.Member;

public interface OrderService {
    Order createOrder(Long memberId, String itemName, int itemPrice);

}
