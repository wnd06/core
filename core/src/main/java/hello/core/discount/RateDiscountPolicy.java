package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

/**
 * 처음 할인 정책은 고정으로 vip회원은 고정적으로 1000원을 할일을 시켰지만 바꾸고 싶어한다.
 * 기획자가 price를 보고 그 price의 10%만 할인하도록 만들고 싶다하여 만든 코드이다.
 * 이제 이 코드를 적용 시킬려면 OrderServiceImpl을 고쳐야 한다.
 */

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;

    @Override
    public int discount(Member member, int price) {

        if(member.getGrade() == Grade.VIP){
            return price * discountPercent / 100;
        } else {
            return 0;
        }
    }
}
