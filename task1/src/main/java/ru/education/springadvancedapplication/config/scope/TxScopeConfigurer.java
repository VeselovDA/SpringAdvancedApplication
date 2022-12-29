package ru.education.springadvancedapplication.config.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import ru.education.springadvancedapplication.config.annotation.Tx;

import java.util.*;
import java.util.stream.Collectors;

public class TxScopeConfigurer implements Scope {
    public static final String ID = "tx";

    private Map<String, Object> scopedObjects
            = Collections.synchronizedMap(new HashMap<String, Object>());
    private Map<String, Runnable> destructionCallbacks
            = Collections.synchronizedMap(new HashMap<String, Runnable>());

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        var stackTrace = Thread.currentThread().getStackTrace();
        List<String> stackTraceMethods = Arrays.stream(stackTrace).map(StackTraceElement::getMethodName).collect(Collectors.toList());
        var isAnnotatedMethodExist = Arrays.stream(stackTrace)
                .map(stackTraceElement -> {
                    try {
                        return Class.forName(stackTraceElement.getClassName());
                    } catch (ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                })
                .filter(aClass -> aClass.getName().contains("ru.education"))
                .flatMap(aClass -> Arrays.stream(aClass.getMethods()))
                .filter(method -> stackTraceMethods.contains(method.getName()))
                .anyMatch(method -> method.isAnnotationPresent(Tx.class));
        if (!isAnnotatedMethodExist) {
            return null;
        }
        if (!scopedObjects.containsKey(name)) {
            scopedObjects.put(name, objectFactory.getObject());
        }
        return scopedObjects.get(name);
    }

    @Override
    public Object remove(String name) {
        destructionCallbacks.remove(name);
        return scopedObjects.remove(name);
    }

    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        destructionCallbacks.put(name, callback);
    }

    @Override
    public Object resolveContextualObject(String s) {
        return null;
    }

    @Override
    public String getConversationId() {
        return ID;
    }
}
