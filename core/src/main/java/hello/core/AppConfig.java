package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

/**
 * 인터페이스에만 의존하도록 코드를 설정 해놓은 상태지만 구현체가 없다
 * 그래서 해결방안으로 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해줘야한다.
 * -> 이 코드가 구현 객체를 생성하고, 연결하는 책임을 가지는 클래스이다.
 */
public class AppConfig {
     public MemberService memberService() {
         return new MemberServiceImpl(memberRepository());
     }

     public OrderService orderService() {
         return new OrderServiceImpl(
                 memberRepository(), discountPolicy());
     }

     public MemberRepository memberRepository() {
         return new MemoryMemberRepository();
     }

     public DiscountPolicy discountPolicy() {
//         return new FixDiscountPolicy();
         return new RateDiscountPolicy(); //할인 정책 변경 하면 코드를 일일이 변경할 필요가 없다.
     }
}
