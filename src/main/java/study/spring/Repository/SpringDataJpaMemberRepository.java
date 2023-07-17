package study.spring.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import study.spring.Domain.Member;

// 인터페이스는 다중 상속 가능
													// 인터페이스에 대한 구현체를 스스로 만듦 -> 스프링 빈에 등록까지 함
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
	
	// 공통으로 만들 수 없는 내 프로젝트만의 메서드는 정의해줘야 한다
	// JPQL select m from Member m where m.name = ?
	@Override
	Optional<Member> findByName(String name);
	
}
