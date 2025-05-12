package task_management;

import java.time.LocalDateTime;

public class Client {

    public static void main(String[] args) {

        TaskManagement taskManagement = TaskManagement.getInstance();
        User aatman = taskManagement.createUser("Aatman");
        User vishal = taskManagement.createUser("Vishal");

        taskManagement.createTask("Task 1", "Description 1", TaskPriority.HIGH, aatman, LocalDateTime.now());
        taskManagement.createTask("Task 2", "Description 2", TaskPriority.HIGH, aatman, LocalDateTime.now());

        taskManagement.createTask("Task 3", "Description 3", TaskPriority.HIGH, vishal, LocalDateTime.now());
        taskManagement.createTask("Task 4", "Description 4", TaskPriority.HIGH, vishal, LocalDateTime.now());
    }
}
