package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        // 실행 > static 검색 > templates 검색
        // home 해당 static(정적) 먼저 찾는데 없으면 templates 파일찾아 보여주는것
        return "home";
    }
}