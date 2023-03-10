package ru.education.springadvancedapplication.controller;

import org.springframework.web.bind.annotation.*;
import ru.education.springadvancedapplication.persistance.dto.BeanDto;
import ru.education.springadvancedapplication.persistance.model.Person;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class Controller {

    @GetMapping("/beans")
    Map<String, BeanDto> getAllDynamicRegisterBean() {
        return null;
    }

    @PostMapping("/register/")
    BeanDto getAllDynamicRegisterBean(BeanDto beanDto, @RequestParam String fileName) {
        return null;
    }

    @PostMapping("/validationPerson")
    Boolean validation(@RequestBody @Valid Person person){
        return true;
    }
}
