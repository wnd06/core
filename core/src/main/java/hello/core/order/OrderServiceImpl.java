package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.*;

public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //고정적으로 1000원 할인하는 정책
    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // -> OCP 위반!!!, DIP도 위반!!(구체 클래스 RateDiscountPolicy 의존!!)
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
/**
 * OCP란 -> 개방폐쇠의 원칙이다.
 * -> 확장에 대해서는 개방(Open)되어야 하지만 변경에 대해서는 폐쇄(Close)되어야 한다는 의미이다.
 * 즉, 기존의 코드를 변경하지 않으면서 기능을 추가 할 수 있도록 설계 되어야 한다.

 * DIP란 -> 의존 역전 원칙
 * -> 고차원 모듈은 저차원 모듈에 의존해선 안된다.
 * 추상화된 것은 구체적인 것에 의존 하면 안된다. 구체적인 것이 추상화된 것에 의존해야한다.
 * 자주 변경되는 구체 클래스에 의존하지 마라
 * 구체 클래스 -> FixDiscountPolicy, RateDiscountPolicy
 * 추상 : DiscountPolicy
 * 자신 보다 변하기 쉬운 것에 의존하지 마라.
 */