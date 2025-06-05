package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //수행 전 클리어(연속 수행시 필수작업)
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();//null 체크
        //Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result); //실무에서 많이사용
    }

    @Test
    public void findByName() {
        Member member = new Member();
        member.setName("spring1");
        repository.save(member);

        Member member1 = new Member();
        member1.setName("spring2");
        repository.save(member1);

        Member result = repository.findByName("spring1").get();
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findAll() {
        Member member = new Member();
        member.setName("spring2");
        repository.save(member);

        Member member1 = new Member();
        member1.setName("spring2");
        repository.save(member1);

        List<Member> memberlist = repository.findAll();

        assertThat(memberlist.size()).isEqualTo(2);
    }
}