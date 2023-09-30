package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoreApplicationTests {



	MemberService memberService;
	@BeforeEach
	public void beforeEach(){
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();

	}
	@Test
	void join() {
		Member member = new Member(1L, "wooHyuk", Grade.VIP);

		memberService.join(member);
		Member findMember = memberService.findById(1L);

		Assertions.assertThat(member).isEqualTo(findMember);

	}
}
