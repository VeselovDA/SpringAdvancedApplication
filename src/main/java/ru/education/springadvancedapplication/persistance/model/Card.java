package ru.education.springadvancedapplication.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private String type;
    private String accountNumber;
    private LocalDate endDate;
}
