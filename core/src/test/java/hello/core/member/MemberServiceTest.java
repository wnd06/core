package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        Member member = new Member(1L,"memberA", Grade.VIP);
        Member member1 = new Member(2L,"memberA", Grade.VIP);

        memberService.join(member);
        memberService.join(member1);
        Member findMember = memberService.findMember(2L);

        Assertions.assertThat(member1).isEqualTo(findMember);

    }

}