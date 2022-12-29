package ru.education.springadvancedapplication.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import ru.education.springadvancedapplication.config.annotation.FIO;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class Person {
    @FIO
    private String name;
    @Positive
    private Integer age;
    @Valid
    private List<Card> cards;
}
