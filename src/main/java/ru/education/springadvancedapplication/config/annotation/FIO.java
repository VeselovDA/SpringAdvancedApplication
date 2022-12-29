package ru.education.springadvancedapplication.config.annotation;

import ru.education.springadvancedapplication.config.validator.FIOValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = FIOValidator.class)
public @interface FIO {
    String message() default "Incorrect full name";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
