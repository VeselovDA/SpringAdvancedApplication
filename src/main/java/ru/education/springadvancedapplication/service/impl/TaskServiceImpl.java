package ru.education.springadvancedapplication.service.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.education.springadvancedapplication.config.annotation.Tx;
import ru.education.springadvancedapplication.persistance.model.Session;
import ru.education.springadvancedapplication.persistance.model.Task;
import ru.education.springadvancedapplication.service.TaskService;

import java.util.Objects;

@Slf4j
@Service
public  class TaskServiceImpl implements TaskService {
    @Getter
    private Session session;
    @Autowired
    private void setSession(Session session){
        this.session=session;
    }


    @Override
    @Tx
    public Task executeStatement(Task task) {
        var session = getSession();
        log.warn("session id{}",session.getUuid());
        log.info("executed task id:{},",task.getId());
        task.setName(changeString(task.getName()));
        task.setStatus(changeString(task.getStatus()));
        task.setComment(changeString(task.getComment()));
        log.info("success execute task id:{},",task.getId());
        log.warn("session id{} finish",session.getUuid());
        return task;
    }

    @Override
    public Task testMethod(Task task) {
        log.info("it is test method without Tx annotation");
        var session = getSession();
        log.warn("session id{}",session.getUuid());
        return task;
    }
    private String changeString(String origin){
        Objects.requireNonNull(origin);
        return origin+"Executed";
    }

}
