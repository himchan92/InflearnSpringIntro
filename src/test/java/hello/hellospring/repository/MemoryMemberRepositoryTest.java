package hello.hellospring.repository;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import hello.hellospring.domain.Member;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemoryMemberRepositoryTest {

  MemoryMemberRepository repository = new MemoryMemberRepository();

  //테스트케이스는 메소드 실행순서 보장이 안되기에 여러개 한번에 돌릴경우 저장/조회 시점 고려해야한다.
  //순서 보장안되서 여러개 수행시 끝날때마다 클리어처리
  @AfterEach //테스트 종료 후 호출
  public void afterEach() {
    repository.clearStore();
  }

  @Test
  public void save() {
    Member member = new Member();
    member.setName("spring");

    repository.save(member);

    Member result = repository.findById(member.getId()).get();//저장된 ID 기준 조회, .get() 비추하지만 테스트케이스라 임시 사용
    //Assertions.assertEquals(result, member);
    assertThat(member).isEqualTo(result); //실무에서는 assertThat 많이 사용
  }

  @Test
  public void findByName() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    Member result = repository.findByName("spring1").get();

    assertThat(result).isEqualTo(member1);
  }

  @Test
  public void findAll() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    List<Member> result = repository.findAll();

    assertThat(result.size()).isEqualTo(2);
  }
}