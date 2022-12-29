package ru.education.springadvancedapplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.education.springadvancedapplication.persistance.model.Person;
import ru.education.springadvancedapplication.service.BeanService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class BeanRegisterTest extends BaseTest{

    @Autowired
    private BeanService beanService;
    @Autowired
    Set<String> dynamicRegisterBeans;

    @Test
    void registerBeanDefaultPath(){
        var countBeanBeforeRegister=dynamicRegisterBeans.size();
        beanService.registerBeanFromFile(null);
        var countBeanAfterRegister=dynamicRegisterBeans.size();
        assertEquals(countBeanBeforeRegister+1,countBeanAfterRegister);
    }
    @Test
    void registerBean(){
        var countBeanBeforeRegister=dynamicRegisterBeans.size();
        beanService.registerBeanFromFile("csv/userCSV.csv");
        var countBeanAfterRegister=dynamicRegisterBeans.size();
        assertNotEquals(countBeanBeforeRegister,countBeanAfterRegister);
    }

    @Test
    void getAllRegisterBean(){
        beanService.getAllRegisterBean();
    }
}
