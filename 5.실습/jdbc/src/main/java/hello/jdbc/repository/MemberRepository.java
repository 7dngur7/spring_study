package hello.jdbc.repository;

import hello.jdbc.domain.Member;

public interface MemberRepository {
    Member save(Member member);

    Member findById(String member_id);

    void updateById(String member_id, int money);

    void deleteById(String member_id);

}


