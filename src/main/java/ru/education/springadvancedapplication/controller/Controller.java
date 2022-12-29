package ru.education.springadvancedapplication.controller;

import org.springframework.web.bind.annotation.*;
import ru.education.springadvancedapplication.persistance.dto.BeanDto;

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
}
