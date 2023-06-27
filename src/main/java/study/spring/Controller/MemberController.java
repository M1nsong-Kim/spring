package study.spring.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import study.spring.service.MemberService;

// 스프링 부트 > 스프링 컨테이너 > @컨트롤러 (빈)
// 						스프링 컨테이너가 뜰 때 생성한다
@Controller
public class MemberController {
	
	private final MemberService memberService;
	
	@Autowired	// 생성자 만들 때 memberService랑 '연결' -> 의존 관계 주입(DI)
	// MemberService에 @Service 붙여야 스프링이 인식 (하는 김에 @Repository도 해줌)
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 스프링 빈을 등록하는 방법 - 2가지 다 알아야 함
	// 1. 컴포넌트 스캔과 자동 의존 관계 설정	
	// 	: controller, service, repository는 component를 포함 -> 스프링은 component가 들어간 걸 객체로 만듦(스프링 빈으로 자동 등록) 
	// 	해당 패키지와 하위 패키지까지만 스프링 빈으로 등록 / 싱글톤으로 생성(하나의 객체) - (따로 설정할 순 있음)
	
	// 2. 자바 코드로 직접 스프링 빈 등록하기
	// @service @repository 지우기
	
	/*
	 DI
	 1. 생성자 주입: 생성자 위에 @Autowired - 권장
	 2. 필드 주입: 필드 앞에만 @Autowired
	 	바꿀 수 있는 방법이 없다 그대로 필드를 가져오기만 함
	 3. setter 주입: setter 위에 @Autowired
	 	public으로 노출 됨 - 잘못 바꾸면 문제 생길 우려
	 	setter는 사실 한 번 생기면 바꿀 이유가 없다
	 */
}
