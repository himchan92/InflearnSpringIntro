package hello.hellospring;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello") //클라이언트 url hello 경로통해 호출
    public String hello(Model model) { //view 단으로 데이터 전달 역할 객체
        model.addAttribute("data", "hello!!"); //data안에 hello!! 값 셋팅 후 전달
        return "hello"; //view단 html 경로/명칭과 반드시 일치
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { //URL 호출 시 파라미터 설정
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-spring")
    @ResponseBody //html 없이 바로 return 내용 그대로 반환
    public String helloString(@RequestParam("name") String name) {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        return hello; //객체반환 JSON > 최신 실무는 거의 JSON만 사용함
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}