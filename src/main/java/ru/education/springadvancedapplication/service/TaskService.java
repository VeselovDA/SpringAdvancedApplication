package ru.education.springadvancedapplication.service;

import ru.education.springadvancedapplication.persistance.model.Session;
import ru.education.springadvancedapplication.persistance.model.Task;

public interface TaskService {
    Task executeStatement(Task task);
    Task testMethod(Task task);
    //Session getSession();
}
