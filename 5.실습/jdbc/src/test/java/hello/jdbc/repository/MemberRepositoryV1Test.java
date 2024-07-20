package hello.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.domain.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class MemberRepositoryV1Test {

    MemberRepositoryV1 memberRepository;

    @BeforeEach
    void beforeEach() throws Exception {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL); dataSource.setUsername(USERNAME); dataSource.setPassword(PASSWORD);

        memberRepository = new MemberRepositoryV1(dataSource);
    }

    @Test
    void curd() throws SQLException {
        Member member = new Member("memberV4", 10000);
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