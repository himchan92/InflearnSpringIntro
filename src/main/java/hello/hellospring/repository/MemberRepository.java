package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository //개발자 직접 구현한 소스는 스프링 컨테이너가 알수없어 관리되게 명시하여 매핑
public interface MemberRepository {
  Member save(Member member); //저장
  Optional<Member> findById(Long id); //단건 조회
  Optional<Member> findByName(String name); //이름 조회
  List<Member> findAll(); //전체조회
}
