package hello.core.Order;

public class Order {
    private Long memberId;
    private String itemName;
    private int itemPrice;
    private int discountItemPrice;

    public Order(Long memberId, String itemName, int itemPrice, int discountItemPrice) {
        this.memberId = memberId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.discountItemPrice = discountItemPrice;
    }

    public int calculateDiscount() {
        return itemPrice - discountItemPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public int getDiscountItemPrice() {
        return discountItemPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemName='" + itemName + '\n' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountItemPrice +
                '}';
    }
}
