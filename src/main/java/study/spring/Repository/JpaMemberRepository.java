package study.spring.Repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import study.spring.Domain.Member;

public class JpaMemberRepository implements MemberRepository{
	private final EntityManager em;	// build.gradle에서 jpa 적으면 스프링 부트가 entitymanager를 만들어줌

	public JpaMemberRepository(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public Member save(Member member) {
		// TODO Auto-generated method stub
		em.persist(member);
		return member;		// insert 알아서 다 해줌
	}

	@Override
	public Optional<Member> findById(Long id) {
		// TODO Auto-generated method stub
		Member member = em.find(Member.class, id);
		return Optional.ofNullable(member);
	}

	// PK 컬럼이 아닌 나머지 -> JPQL로 작성 필요(객체 대상 쿼리)
	@Override
	public Optional<Member> findByName(String name) {
		// TODO Auto-generated method stub
		// 쿼리에 m.name = :name 안 하면 테스트 시 java.lang.IllegalArgumentException: Could not locate named parameter [name], expecting one of [] 오류
		List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name", name).getResultList();
		return result.stream().findAny();
	}

	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList(); // 객체를 대상으로 쿼리를 날림
		return result;
	}

}
