package hello.hellospring.config;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 직접 컴포넌트 스캔 등록되게 구현
@Configuration
public class SpringConfig {

  //스프링빈에 등록할거라고 스프링에게 통보
  // @Service 명시 대신 수동 구현방식
  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository());
  }

  // @Repository 명시 대신 수동 구현방식
  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
}
