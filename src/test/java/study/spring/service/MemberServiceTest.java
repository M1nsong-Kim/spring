package study.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import study.spring.Domain.Member;

public class MemberServiceTest {
	
	MemberService memberService = new MemberService();
	
	// 테스트는 영어권 동료랑 하는 게 아니면 한글로 바꿔도 됨 - 실제 코드에 포함되지 않으니까
	@Test
	void 회원가입() {
		// given (상황이 주어짐)
		Member member = new Member();
		member.setName("hello");
		
		// when (실행했을 때)
		Long saveId = memberService.join(member);
		
		// then (이게 나와야 됨)
		Member findMember = memberService.findOne(saveId).get();
		assertThat(member.getName()).isEqualTo(findMember.getName());
				// JUni test -> 초록
	}
	
	@Test
	public void 중복_회원_예외() {
		// given
		Member member1 = new Member();
		member1.setName("spring");
		
		Member member2 = new Member();
		member2.setName("spring");
		
		// when
		memberService.join(member1);
		try {			
			memberService.join(member2);
			fail();
		}catch(IllegalStateException e) {
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
			// 메시지와 다름 -> JUnit test 실행 시 실패(빨강)
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.11");
		}
		
		// then
	}
	
}
