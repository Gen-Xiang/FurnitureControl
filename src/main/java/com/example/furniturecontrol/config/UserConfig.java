package com.example.furniturecontrol.config;

import com.example.furniturecontrol.entity.User;
import com.example.furniturecontrol.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository){
        return args -> {
            User chx = new User("chx","12345678","chenhx@qq.com","A stupid");
            User yjr = new User("yjr","010203","yujr@163.com","A stupid, too");
            repository.saveAll(List.of(chx,yjr));
        };
    }

}
