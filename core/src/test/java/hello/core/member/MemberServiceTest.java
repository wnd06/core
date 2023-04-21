package hello.core.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl();

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