package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //순수 자바코드는 스프링컨테이너가 인식하지 못해 명시해줘서 관리하게 명시하여 매핑
public class MemberService {

  private final MemberRepository memberRepository;

  @Autowired //스프링컨테이너가 대신 생성자 주입 지원(사전 어노테이션 매핑으로 컨테이너 인식 필수)
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }

  //private final MemberRepository memberRepository = new MemoryMemberRepository();

  //회원가입
  public Long join(Member member) {
    //중복회원체크
    validateDuplicateMember(member);
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName())
        .ifPresent(m -> {
          throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
  }

  //전체 회원조회
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }

  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }

}
