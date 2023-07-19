package study.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
// bean에 등록하는 방법 1: @Component	
public class TimeTraceAop {
	
	@Around("execution(* hello.hellospring..*(..))")	// 이 패키지 하위에는 다 적용
	public Object execute(ProceedingJoinPoint joinPoint) throws Throwable{
		long start = System.currentTimeMillis();
		System.out.println("START : " + joinPoint.toString());
		
		try {			
			return joinPoint.proceed();
		}finally {
			long finish = System.currentTimeMillis();
			long timeMs = finish - start;
			System.out.println("END : " + joinPoint.toString() + " " + timeMs + "ms");
		}
		
	}
	
	/*
	 작동 원리
	 	가짜 Service(프록시)를 만들어 controller와 service 사이에 둠
	 	-> 프록시 실행 후 joinPoint.proceed()로 진짜 service 실행
	 */
}
