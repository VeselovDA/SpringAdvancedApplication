package ru.education.springadvancedapplication.persistance.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Person {
    private String name;
    private Integer age;
    private List<Card> cards;
}
