package ru.education.springadvancedapplication.util;

import ru.education.springadvancedapplication.persistance.model.Task;


public class TaskUtil {
    private static final String TEST="test";
    private static final String EXECUTED_TEST="testExecuted";

    private TaskUtil() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    public static Task creteTask(){
        return creteTask(1);
    }
    public static Task creteTask(int id){
        Task task=new Task();
        task.setId(id);
        task.setName(TEST);
        task.setStatus(TEST);
        task.setComment(TEST);
        return task;
    }
    public static Task creteTaskWithNullFields(int id){
        Task task=new Task();
        task.setId(id);
        task.setName(TEST);
        task.setStatus(null);
        task.setComment(null);
        return task;
    }
    public static Task createExecutedTask(int id){
        Task task=new Task();
        task.setId(id);
        task.setName(EXECUTED_TEST);
        task.setStatus(EXECUTED_TEST);
        task.setComment(EXECUTED_TEST);
        return task;
    }

}
