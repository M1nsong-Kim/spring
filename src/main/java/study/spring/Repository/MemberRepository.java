package study.spring.Repository;

import java.util.List;
import java.util.Optional;

import study.spring.Domain.Member;

// 인터페이스로 만드는 이유 -> 데이터 저장소가 선정되지 않았음을 가정: 구현 클래스를 변경할 수 있도록 인터페이스로 설계
public interface MemberRepository {
	Member save(Member member);
	Optional<Member> findById(Long id);	// optional: null값 처리
	Optional<Member> findByName(String name);
	List<Member> findAll();
}
