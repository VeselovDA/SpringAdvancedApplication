package ru.education.springadvancedapplication.config.scope;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class TxScopeConfigurer implements Scope {

    private Map<String, Object> scopedObjects
            = Collections.synchronizedMap(new HashMap<String, Object>());
    private Map<String, Runnable> destructionCallbacks
            = Collections.synchronizedMap(new HashMap<String, Runnable>());

    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        if(!scopedObjects.containsKey(name)) {
            var object = objectFactory.getObject();
            String name1 = Thread.currentThread().getName();
            scopedObjects.put(name,object);
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
        return "tx";
    }
}
