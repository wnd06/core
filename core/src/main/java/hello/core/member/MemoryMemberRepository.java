package hello.core.member;

import java.util.HashMap;
import java.util.Map;

/**
 * Map은 Key와 value를 가지고 값을 저장한다.
 * 그리고 value를 찾고자 할 때 key를 기준으로 value를 찾습니다.
 * HashMap은 해싱을 이용하여 Map을 구현한 구현체이다.
 * 해싱이란 -> 키에 산술적인 연산을 적용하여 항목이 저장되어 있는 테이블의 주소를
 * 계산하여 항목에 접근한다.
 * put(key, Value) : 맵에 자료 저장.
 */
public class MemoryMemberRepository implements MemberRepository{

    //다형성 이용.
    private static Map<Long, Member> store = new HashMap<>();
    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId); // get을 통하여 key를 통해 value값을 알아낸다.
    }
}
