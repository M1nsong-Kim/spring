package study.spring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import study.spring.Domain.Member;
import study.spring.Repository.MemoryMemberRepository;

public class MemberServiceTest {
	
	// memberService에 이미 memberRepository 객체 만들고 있음(각각 생성자를 만들면 DB가 다르게 생기니까 문제) -> memberService에서 외부에서 넣어지도록 바꾸기
	MemberService memberService; // = new MemberService();
	MemoryMemberRepository memberRepository; // = new MemoryMemberRepository();

	@BeforeEach
	public void beforeEach() {
		memberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memberRepository);
	}
	// => test와 같은 memberRepository 사용하게 됨
	// memberService 입장에서는 직접 생성자를 만들지 않고 외부에서 넣어준다. 이를 DI(Dependency Injection)
	
	// DB 클리어
	@AfterEach
	public void afterEach() {
		memberRepository.clearStore();
	}
	
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
		// IllegarStateException 예외가 나와야 함 -> JUnit test 실행 시 성공(초록)
		assertThrows(IllegalStateException.class, () ->  memberService.join(member2));
		// JUnit 실행 시 실패
		// assertThrows(NullPointerException.class, () ->  memberService.join(member2));
		
		/*
		try 
			memberService.join(member2);
			fail();
		}catch(IllegalStateException e) {
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
			// 메시지와 다름 -> JUnit test 실행 시 실패(빨강)
			assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.11");
		}
		 */
		
		// then
	}
	
}
