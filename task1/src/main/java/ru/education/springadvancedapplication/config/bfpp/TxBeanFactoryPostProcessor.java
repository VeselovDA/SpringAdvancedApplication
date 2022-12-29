package ru.education.springadvancedapplication.config.bfpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import ru.education.springadvancedapplication.config.scope.TxScopeConfigurer;

public class TxBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory factory) throws BeansException {
        factory.registerScope(TxScopeConfigurer.ID, new TxScopeConfigurer());
    }
}
