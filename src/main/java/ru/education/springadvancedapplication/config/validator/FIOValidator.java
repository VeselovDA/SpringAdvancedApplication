package ru.education.springadvancedapplication.config.validator;

import ru.education.springadvancedapplication.config.annotation.FIO;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class FIOValidator implements ConstraintValidator<FIO, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        var res = s.split(" ");
        if (res.length < 2 || res.length > 3 ) return false;
        return Arrays.stream(res)
                .allMatch(str -> str.matches("^[A-Z|А-Я][a-z|а-я]*$"));
    }
}
