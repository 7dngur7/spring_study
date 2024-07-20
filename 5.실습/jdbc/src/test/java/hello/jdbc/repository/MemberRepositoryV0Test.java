package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryV0Test {
    MemberRepositoryV0 memberRepository = new MemberRepositoryV0();

    @Test
    void curd() throws SQLException {
        Member member = new Member("memberV3", 10000);
        memberRepository.save(member);

        Member member1 = memberRepository.findById(member.getMemberId());
        org.assertj.core.api.Assertions.assertThat(member).isEqualTo(member1);

        memberRepository.updateById(member1.getMemberId(), 20000);

        Member member2 = memberRepository.findById(member1.getMemberId());
        org.assertj.core.api.Assertions.assertThat(member2.getMoney()).isEqualTo(20000);

        memberRepository.deleteById(member.getMemberId());
        assertThatThrownBy(() -> memberRepository.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }


}