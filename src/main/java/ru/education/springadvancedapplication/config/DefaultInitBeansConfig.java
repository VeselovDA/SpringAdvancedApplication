package ru.education.springadvancedapplication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.education.springadvancedapplication.persistance.model.Card;
import ru.education.springadvancedapplication.persistance.model.Person;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DefaultInitBeansConfig {
    @Bean
    public Person dmitriyPerson() {
        return Person.builder()
                .age(23)
                .name("Дмитрий")
                .cards(List.of(new Card("CREDIT", "1111", LocalDate.now().plusYears(5)),
                        new Card("DEBIT", "1111", LocalDate.now().plusYears(5))))
                .build();
    }

    @Bean
    public Person anastasiaPerson() {
        return Person.builder()
                .age(21)
                .name("Анастасия")
                .cards(List.of(new Card("CREDIT", "2222", LocalDate.now().plusYears(3)),
                        new Card("DEBIT", "22222", LocalDate.now().plusYears(3))))
                .build();
    }
}
