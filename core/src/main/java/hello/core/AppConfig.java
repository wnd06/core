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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 인터페이스에만 의존하도록 코드를 설정 해놓은 상태지만 구현체가 없다
 * 그래서 해결방안으로 OrderServiceImpl에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해줘야한다.
 * -> 이 코드가 구현 객체를 생성하고, 연결하는 책임을 가지는 클래스이다.
 * - 스프링으로 전환 하기 -
 * AppConfig에 설정을 구성한다는 뜻의 @Configuration 을 붙여준다.
 * 각 메서드에 @Bean 을 붙여준다. 이렇게 하면 스프링 컨테이너에 스프링 빈으로 등록된다.
 * 등록된 스프링 빈을 사용하기 위해서는 먼저 스프링 컨테이너를 불러온 후 getBean을 활용하여 사용할 수 있다.
 */
@Configuration
public class AppConfig {
    @Bean
     public MemberService memberService() {
         return new MemberServiceImpl(memberRepository());
     }
    @Bean
     public OrderService orderService() {
         return new OrderServiceImpl(
                 memberRepository(), discountPolicy());
     }
    @Bean
     public MemberRepository memberRepository() {
         return new MemoryMemberRepository();
     }
    @Bean
     public DiscountPolicy discountPolicy() {
//         return new FixDiscountPolicy();
         return new RateDiscountPolicy(); //할인 정책 변경 하면 코드를 일일이 변경할 필요가 없다.
     }
}
