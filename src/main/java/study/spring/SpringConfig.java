package study.spring;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import study.spring.Repository.JdbcMemberRepository;
import study.spring.Repository.MemberRepository;
import study.spring.service.MemberService;

@Configuration
public class SpringConfig {

	private DataSource dataSource;
	
	@Autowired
	public SpringConfig(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	@Bean
	public MemberRepository memberRepository() {
		// return new MemoryMemberRepository();
		return new JdbcMemberRepository(dataSource);	// 메모리가 아니라 DB로
	}
}
