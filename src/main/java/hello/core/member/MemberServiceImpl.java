package hello.core.Member;

public class MemberServiceImpl implements MemberService{


    private final MemberRepository memberRepository = new MemberRepositoryImpl();

    @Override
    public void join(Member member) {
        memberRepository.saveMember(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.getMember(memberId);
    }
}
