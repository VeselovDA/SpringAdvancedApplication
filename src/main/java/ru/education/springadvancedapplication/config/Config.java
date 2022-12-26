package ru.education.springadvancedapplication.config;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.education.springadvancedapplication.config.bfpp.TxBeanFactoryPostProcessor;
import ru.education.springadvancedapplication.config.bpp.TxAnnotationBeanPostProcessor;
import ru.education.springadvancedapplication.persistance.model.Session;
import ru.education.springadvancedapplication.service.TaskService;
import ru.education.springadvancedapplication.service.impl.TaskServiceImpl;

@Configuration
public class Config {

    @Bean
    BeanPostProcessor txBeanPostProcessor(){
        return new TxAnnotationBeanPostProcessor();
    }

    @Bean
    BeanFactoryPostProcessor txBeanFactoryPostProcessor(){
        return new TxBeanFactoryPostProcessor();
    }

    @Scope(scopeName = "tx")
    @Bean
    public Session session() {
        return new Session();
    }
//    @Bean
//    TaskService taskService(){
//        return new TaskServiceImpl() {
//            @Override
//            public Session getSession() {
//                return session();
//            }
//        };
//    }
}
