package ru.education.springadvancedapplication.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import ru.education.springadvancedapplication.config.bfpp.TxBeanFactoryPostProcessor;
import ru.education.springadvancedapplication.config.bpp.TxAnnotationBeanPostProcessor;
import ru.education.springadvancedapplication.persistance.model.Session;


import java.util.HashSet;

import java.util.Set;

@Configuration
public class Config {

    @Bean
    BeanPostProcessor txBeanPostProcessor() {
        return new TxAnnotationBeanPostProcessor();
    }

    @Bean
    BeanFactoryPostProcessor txBeanFactoryPostProcessor() {
        return new TxBeanFactoryPostProcessor();
    }

    @Bean("customSession")
    @Scope(value = "tx", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public Session session() {
        return new Session();
    }

    @Bean
    Set<String> dynamicRegisterBeans() {
        return new HashSet<>();
    }
}
