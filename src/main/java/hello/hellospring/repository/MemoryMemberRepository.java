package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //PK
        store.put(member.getId(), member);

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null 방지 Optional 사용
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
            .filter(member -> member.getName().equals(name))
            .findAny(); //전체돌면서 있으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //values 이용해 Member 내용 전부 반환
    }

    public void clearStore() {
        store.clear();
    }
}
