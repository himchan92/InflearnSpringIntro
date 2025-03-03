package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

public interface MemberRepository {
    Member save(Member member); //저장
    Optional<Member> findById(Long id); //단건 조회(ID기준)
    Optional<Member> findByName(String name);//단건 조회(Name기준)
    List<Member> findAll(); //전체조회
}
