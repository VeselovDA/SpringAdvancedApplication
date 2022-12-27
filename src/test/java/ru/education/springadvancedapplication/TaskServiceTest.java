package ru.education.springadvancedapplication;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.education.springadvancedapplication.service.TaskService;
import ru.education.springadvancedapplication.util.TaskUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TaskServiceTest extends BaseTest{
    @Autowired
    private TaskService taskService;

    @Test
    void executeStatement() {
        int testId = 1;
        var testTask = TaskUtil.creteTask(testId);
        var executedTask = TaskUtil.createExecutedTask(testId);
        System.out.println(testTask);
        var result = taskService.executeStatement(testTask);
        System.out.println(result);
        assertEquals(result, executedTask);

    }

    @Test
    void executeStatementWithError() {
        int testId = 1;
        var testTask = TaskUtil.creteTaskWithNullFields(testId);
        System.out.println(testTask);
        try {
            taskService.executeStatement(testTask);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println(testTask);
        }
        assertEquals(TaskUtil.creteTaskWithNullFields(testId), testTask);
    }

    @Test
    void testMethod() {
        int testId = 1;
        var testTask = TaskUtil.creteTaskWithNullFields(testId);
        Exception exception = assertThrows(RuntimeException.class, () -> {
            taskService.testMethod(testTask);
        });
    }

}
