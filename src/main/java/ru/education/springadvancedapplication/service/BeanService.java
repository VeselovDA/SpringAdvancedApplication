package ru.education.springadvancedapplication.service;

import java.util.Map;

public interface BeanService {
    Map<String, Object> getAllRegisterBean();
    void registerBeanFromFile(String fileName);
}
