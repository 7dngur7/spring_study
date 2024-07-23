package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepository;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class MemberServieceV4 {

    private final MemberRepository memberRepository;

    @Transactional
    public void accountTransfer(String fromId, String toId, int money){
            bizLogic(fromId, toId, money);

    }

    private void bizLogic(String fromId, String toId, int money) {
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        memberRepository.updateById(fromId, fromMember.getMoney()- money);

        validate(toMember);

        memberRepository.updateById(toId,toMember.getMoney()+ money);
    }

    private static void validate(Member toMember) {
        if(toMember.getMemberId().equals("ex")){
            throw new IllegalStateException("오류 사용자!");
        }
    }


}
