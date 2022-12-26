package ru.education.springadvancedapplication.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;
import ru.education.springadvancedapplication.config.annotation.Tx;
import ru.education.springadvancedapplication.persistance.model.Session;
import ru.education.springadvancedapplication.persistance.model.Task;
import ru.education.springadvancedapplication.service.TaskService;

import java.util.Objects;

@Slf4j
@Service
public class TaskServiceImpl implements TaskService {


    @Override
    @Tx
    public Task executeStatement(Task task) {
        //log.info("session id: {}",getSession().getUuid());
        log.info("executed task id:{},",task.getId());
        task.setName(changeString(task.getName()));
        task.setStatus(changeString(task.getStatus()));
        task.setComment(changeString(task.getComment()));
        log.info("success execute task id:{},",task.getId());
        return task;
    }

    @Override
    public void testMethod() {
        log.info("it is test method without Tx annotation");
    }
    private String changeString(String origin){
        Objects.requireNonNull(origin);
        return origin+"Executed";
    }

//    public abstract Session getSession();
}
