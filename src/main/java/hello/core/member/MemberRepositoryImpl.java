package hello.core.Member;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Component
public class MemberRepositoryImpl implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void saveMember(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member getMember(Long memberId) {
        return store.get(memberId);
    }

}
