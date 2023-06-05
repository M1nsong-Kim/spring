package study.spring.service;

import java.util.List;
import java.util.Optional;

import study.spring.Domain.Member;
import study.spring.Repository.MemberRepository;
import study.spring.Repository.MemoryMemberRepository;

public class MemberService {

	private final MemberRepository memberRepository = new MemoryMemberRepository();
	
	// 회원 가입
	public Long join(Member member) {
		// 동명이인 중복 회원 x
		/*
		Optional<Member> result = memberRepository.findByName(member.getName());
		result.ifPresent(m ->{
								throw new IllegalStateException("이미 존재하는 회원입니다.");
							});	// 만약 값이 있으면 예외 발생(if null 안 쓰고 optional 메서드 씀)
		 */
		validateDuplicateMember(member);
		memberRepository.save(member);
		return member.getId();
	}
	
	// 위 메서드의 주석 부분을 메서드로 분리
	private void validateDuplicateMember(Member member){
		memberRepository.findByName(member.getName()).ifPresent(m -> {
			throw new IllegalStateException("이미 존재하는 회원입니다.");
		});
	}
	
	// 전체 회원 조회
	public List<Member> findMembers(){
		return 	memberRepository.findAll();
	}
	
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
}
