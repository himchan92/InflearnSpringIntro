package hello.hellospring.service;

import static org.assertj.core.api.AssertionsForClassTypes.fail;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional //변경작업 시 필수설정(디폴트 종료 시 자동롤백)
class MemberServiceTest {

  @Autowired
  MemberService memberService;

  @Autowired
  MemoryMemberRepository memberRepository;

  //수행 전 생성자 DI 수행
  @BeforeEach
  public void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  //수행 후 수행
  @AfterEach
  public void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  void 회원가입() {
    //given
    Member member = new Member();
    member.setName("hello");

    //when
    Long savedId = memberService.join(member);

    //then
    Member findOne = memberService.findOne(savedId).get();
    assertThat(member.getName()).isEqualTo(findOne.getName());
  }

  @Test
  void 중복회원예외() {
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    memberService.join(member1);
    assertThrows(IllegalStateException.class, () -> memberService.join(member2));

//    try {
//      memberService.join(member2);
//      fail();
//    } catch (IllegalStateException e) {
//      assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//    }
  }
}