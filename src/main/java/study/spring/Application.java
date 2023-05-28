package study.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}

/*
 실행 시 콘솔창에서
... o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
-> 웹 브라우저에서 localhost:8080 들어가면 에러 페이지 확인 가능 -> 성공
 */
