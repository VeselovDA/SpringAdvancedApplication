package ru.education.springadvancedapplication.service;

import ru.education.springadvancedapplication.persistance.model.Task;

public interface TaskService {
    Task executeStatement(Task task);
    void testMethod();
}
