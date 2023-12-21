package hello.core.Member;

public interface MemberRepository {


    void saveMember(Member member);

    Member getMember(Long memberId);

}
