package hello.hellospring.repository;

import static org.assertj.core.api.Assertions.assertThat;

import hello.hellospring.domain.Member;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//클래스에서 돌리면 하위 전체 테스트 수행 가능
//테스트 여러개 돌릴시 실행순서는 보장안됨 -> 하나 끝나면 클리어해줘야 한다.
class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //테스트 종료후
    public void afterEach() {
        //클리어 처리 -> 테스트케이스 여러개 돌릴경우 순서보장안되므로 한번 수행 후 클리어해줘야함
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();

        //DB값과 위설정값이 일치하면 성공
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result); //실무에서는 assertThat 많이 사용
    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring").get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    public void findAll() {
        Member member = new Member();
        member.setName("spring");
        repository.save(member);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}