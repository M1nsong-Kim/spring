package study.spring.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")	// localhost:8080 으로 가면 home.html로 -> index.html로 가기 전에 매핑된 게 있는지 확인하기 때문
	public String home() {
		return "home";
	}
}
