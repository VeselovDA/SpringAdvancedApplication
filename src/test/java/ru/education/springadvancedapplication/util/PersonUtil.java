package ru.education.springadvancedapplication.util;

import ru.education.springadvancedapplication.persistance.model.Card;
import ru.education.springadvancedapplication.persistance.model.Person;

import java.time.LocalDate;
import java.util.List;

public class PersonUtil {

    private PersonUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static Person createInvalidPerson(){
        return Person.builder()
                .age(-1)
                .name("Дмитрий")
                .cards(List.of(new Card("CREDIT", "1111", LocalDate.now().plusYears(5)),
                        new Card("DEBIT", "1111", LocalDate.now().plusYears(5))))
                .build();
    }
    public static Person createPerson(){
        return Person.builder()
                .age(23)
                .name("Веселов Дмитрий Андреевич")
                .cards(List.of(new Card("CREDIT", "11111111111111111111", LocalDate.now().plusYears(5)),
                        new Card("DEBIT","22222222222222222222", LocalDate.now().plusYears(5))))
                .build();
    }
}
