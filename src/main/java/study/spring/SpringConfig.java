package study.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import study.spring.Repository.MemberRepository;
import study.spring.Repository.MemoryMemberRepository;
import study.spring.service.MemberService;

@Configuration
public class SpringConfig {

	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		return new MemoryMemberRepository();
	}
}
