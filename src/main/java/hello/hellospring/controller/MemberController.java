package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //빈에 등록
public class MemberController {

    //매번 new로 인스턴스화 할필요없이 한개를 여러군데에서 공유하는방식 권장
    //private final MemberService memberService = new MemberService();

    private MemberService memberService;

    @Autowired // 생성자 DI : 생성시점에만 호출되어 외부변경 확실히 차단되고 권장하는 방식
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }


}
