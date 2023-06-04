package study.spring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import study.spring.Domain.Member;
import study.spring.Repository.MemoryMemberRepository;

// 구현 클래스 먼저 만든 후 테스트케이스 작성
// <-> 테스트 먼저 짜는 테스트 주도 개발 TDD
public class MemoryMemberRepositoryTest {
	MemoryMemberRepository repository = new MemoryMemberRepository();

	// 테스트 순서는 보장되지 않음 -> 테스트 하나 끝나면 클리어해줘야
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		
		// 제대로 들어갔는지 검증
		Member result = repository.findById(member.getId()).get();	// optional에서 값 꺼낼 떄 get() (테스트할 떄만 쓰기)

		// 방법 1. (비추)
		System.out.println("result = "+(result == member));
		// 방법 2.
		// static 추가하면 Assertions 생략 가능
		/*
		Assertions.assertEquals(result, member);
			// JUnit 테스트로 실행 시 같으면 초록색 다르면 붉은색
		Assertions.assertThat(member).isEqualTo(result);
		 */
		assertThat(member).isEqualTo(result);
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result = repository.findByName("spring1").get();
		
		assertThat(result).isEqualTo(member1);	// 초록
		// assertThat(result).isEqualTo(member2);	// 빨강
	}
	
	@Test
	public void findAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		
		assertThat(result.size()).isEqualByComparingTo(2);
	}
}
