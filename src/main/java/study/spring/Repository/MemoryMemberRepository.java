package study.spring.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import study.spring.Domain.Member;

public class MemoryMemberRepository implements MemberRepository{
	private static Map<Long, Member> store = new HashMap<>();
	private static long sequence = 0L;
	
	@Override
	public Member save(Member member) {
		// TODO Auto-generated method stub
		member.setId(++sequence);	// 아이디는 자동 지정
		store.put(member.getId(), member);
		return member;
	}

	@Override
	public Optional<Member> findById(Long id) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(store.get(id));	// null이 반환될 가능성이 있으면 optional로
	}

	@Override
	public Optional<Member> findByName(String name) {
		// TODO Auto-generated method stub
		// 람다
		return store.values().stream()
				 .filter(member -> member.getName().equals(name))	// 반복문 -> 넘어온 name이랑 같을 때
				 .findAny();	// 하나라도 찾기
	}

	@Override
	public List<Member> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<>(store.values());
	}

}
