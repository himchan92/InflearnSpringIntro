package hello.hellospring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 어노테이션 빈등록 허용범위 : 같은 패키지내에서만 가능, 다른패키지에 만든것은 주입 및 연동 안됨
@SpringBootApplication
public class HelloSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringApplication.class, args);
    }

}