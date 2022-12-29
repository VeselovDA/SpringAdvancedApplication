package ru.education.springadvancedapplication.config.bpp;

import lombok.SneakyThrows;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import ru.education.springadvancedapplication.config.annotation.Tx;
import ru.education.springadvancedapplication.persistance.model.Task;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TxAnnotationBeanPostProcessor implements BeanPostProcessor {
    private final Map<String, Method> txAnnotatedMethod = new HashMap<>();

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(bean.getClass().getMethods()).forEach(method -> {
            if (method.isAnnotationPresent(Tx.class)) {
                txAnnotatedMethod.put(method.getName(), method);
            }
        });
        return bean;
    }

    @Nullable
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        long countTxAnnotatedMethod = Arrays.stream(bean.getClass().getMethods())
                .filter(method -> txAnnotatedMethod.containsKey(method.getName()))
                .count();
        if (countTxAnnotatedMethod == 0) {
            return bean;
        }
        var beanClass = bean.getClass();
        return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                var inputTask = (Task) args[0];
                var copyTask = copyTask(inputTask);

                if (txAnnotatedMethod.containsKey(method.getName())) {
                    try {
                        var invoke = (Task) method.invoke(bean, copyTask);
                        return copyFieldsFromDonor(inputTask, invoke);
                    } catch (Exception e) {
                        throw new RuntimeException("rollback");
                    }
                } else {
                    return method.invoke(bean, args);
                }
            }
        });
    }

    @SneakyThrows
    private Task copyTask(Task inputTask) {
        Task clone = new Task();
        for (Field field : Task.class.getDeclaredFields()) {
            field.setAccessible(true);
            field.set(clone, field.get(inputTask));
        }
        return clone;
    }

    @SneakyThrows
    private Task copyFieldsFromDonor(Task target, Task donor) {
        for (Field field : Task.class.getDeclaredFields()) {
            field.setAccessible(true);
            field.set(target, field.get(donor));
        }
        return target;
    }

}
