package ru.itis.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.springbootdemo.controllers.InHandController;
import ru.itis.springbootdemo.controllers.ShelterController;

import java.io.File;
import java.io.FilenameFilter;

@SpringBootApplication
public class SpringBootDemoApplication {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        ShelterController shelterController = new ShelterController();
        System.out.println(shelterController.getJSON("https://some-random-api.ml/facts/dog"));
        InHandController inHandController = new InHandController();
        String[] files = inHandController.getFiles();
        System.out.println(files.length);
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }


}
