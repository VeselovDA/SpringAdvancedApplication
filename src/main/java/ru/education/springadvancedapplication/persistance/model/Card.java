package ru.education.springadvancedapplication.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    @Pattern(regexp = "^CREDIT$|DEBIT$")
    private String type;
    @Pattern(regexp = "^\\d{20}$")
    private String accountNumber;
    @Future
    private LocalDate endDate;
}
