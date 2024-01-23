package hellocalendar.demo.memberRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class memberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void crudTest(){
        Member member = Member.builder()
                .id("dts")
                .name("이시내")
                .password("sinae")
                .build();
    }
}
