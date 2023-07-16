package study.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import study.spring.Domain.Member;
import study.spring.Repository.MemberRepository;

@SpringBootTest	// 테스트하는 중에도 spring console 뜸
@Transactional
public class MemberServiceIntegrationTest {
	
	// 스프링 컨테이너한테 달라고 하기
	@Autowired MemberService memberService;
	@Autowired MemberRepository memberRepository;

	// 보통 실전에선 테스트용 DB를 따로 두고 삭제 삽입 반복(테스트는 반복이 중요) -> transactional이 테스트 끝나면 자동으로 롤백해줘서 편리(테스트에서만)
	@Test
	@Commit // DB에 반영
	void 회원가입() {
		 //Given
		 Member member = new Member();
		 member.setName("hello");	// JUnit 테스트 실행하면 DB에 있는 spring1은 오류나는데 hello는 DB에 안 들어감(transactional 주석 처리 시)
		 //When
		 Long saveId = memberService.join(member);
		 //Then
		 Member findMember = memberRepository.findById(saveId).get();
		 assertEquals(member.getName(), findMember.getName());
	}
	
	@Test
	public void 중복_회원_예외() {
		 //Given
		 Member member1 = new Member();
		 member1.setName("spring");
		 Member member2 = new Member();
		 member2.setName("spring");
		 //When
		 memberService.join(member1);
		 IllegalStateException e = assertThrows(IllegalStateException.class,
		 () -> memberService.join(member2));//예외가 발생해야 한다.
		 assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
	}
	
}
// jpa 추가 후 실행하면 Hibernate: select member0_.id as id1_0_, member0_.name as name2_0_ from member member0_ where member0_.name=? 이런 식으로 쿼리 확인 가능

// 하지만 통합 테스트마다 단위 테스트 하나하나를 잘 하는 게 더 중요