package ru.education.springadvancedapplication.config.bpp;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.Nullable;
import ru.education.springadvancedapplication.config.annotation.Tx;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.IntStream;

public class TxAnnotationBeanPostProcessor implements BeanPostProcessor {
    private final Map<String,Method> txAnnotatedMethod =new HashMap<>();
    @Override
     public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Arrays.stream(bean.getClass().getMethods()).forEach(method -> {
            if (method.isAnnotationPresent(Tx.class)){
                txAnnotatedMethod.put(method.getName(),method);
            }
        });
        return bean;
    }

    @Nullable
    public  Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        var annotatedMethods=new ArrayList<Method>();
        Arrays.stream(bean.getClass().getMethods()).forEach(method -> {
            var annotatedMethod= txAnnotatedMethod.get(method.getName());
            if (Objects.nonNull(annotatedMethod)){
                annotatedMethods.add(annotatedMethod);
            }
        });
        if(annotatedMethods.isEmpty()){
            return bean;
        }
        var beanClass=bean.getClass();
        return Proxy.newProxyInstance(beanClass.getClassLoader(), beanClass.getInterfaces(), new InvocationHandler() {
                 @Override
                 public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                     Object invoke;
                     if (txAnnotatedMethod.containsKey(method.getName())) {
                         var screen = createScreen(args);
                         try {
                             invoke = method.invoke(bean, screen.toArray());
                         } catch (Exception e) {
                             throw new RuntimeException("rollback");
                         }
                     } else {
                         invoke = method.invoke(bean, args);
                     }
                     return invoke;
                 }
        });
    }
        private List<Object> createScreen(Object[] args){
            var screen = new ArrayList<>();
            Arrays.stream(args).forEach(arg->screen.add(copyObject(arg)));
            return screen;
        }
        private Object copyObject(Object object) {
            try
            {
                Object clone = object.getClass().newInstance();
                for(Field field : object.getClass().getDeclaredFields()) {
                    field.setAccessible(true);
                    if(field.get(object) == null || Modifier.isFinal(field.getModifiers()))
                    {
                        continue;
                    }
                    if(field.getType().isPrimitive()
                            || field.getType().equals(String.class)
                            || field.getType().getSuperclass().equals(Number.class)
                            || field.getType().equals(Boolean.class))
                    {
                        field.set(clone, field.get(object));
                    }
                    else {
                        Object childObj = field.get(object);
                        if(childObj == object) {
                            field.set(clone,clone);
                        }
                        else {
                            field.set(clone,copyObject(field.get(object)));
                        }
                    }
                }
                return clone;
            }
            catch (Exception e) {
                return  null;
            }
        }

}
