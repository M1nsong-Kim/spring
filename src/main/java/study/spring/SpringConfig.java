package study.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import study.spring.Repository.MemberRepository;
import study.spring.aop.TimeTraceAop;
import study.spring.service.MemberService;

@Configuration
public class SpringConfig {

	private final MemberRepository memberRepository;
	
	// @Autowired
	public SpringConfig(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository);
	}
	
	// bean에 등록하는 방법 2: spring config
	public TimeTraceAop TimeTraceAop() {
		return new TimeTraceAop();
	}
	
	/*
	@Bean
	public MemberRepository memberRepository() {
		// return new MemoryMemberRepository();
		// return new JdbcMemberRepository(dataSource);	// 메모리가 아니라 DB로
		// return new JdbcTemplateMemberRepository(dataSource);
		// return new JpaMemberRepository(em);
	}
	 */
}
