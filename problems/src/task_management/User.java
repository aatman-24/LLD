package task_management;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class User {

    private int id;
    private String name;
    private List<Task> taskList;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        taskList = new ArrayList<>(5);
    }

    void addTask(Task task) {
        taskList.add(task);
    }

    Task addTask(String title, String description, TaskPriority priority, LocalDateTime dueDate) {
        Task task = new Task(title, description, priority, this, dueDate);
        taskList.add(task);
        return task;
    }

    boolean removeTask(Task task) {
        return taskList.remove(task);
    }

    boolean assignTaskToOtherUser(Task task, User user) {
        this.taskList.remove(task);
        user.addTask(task);
        return true;
    }

    List<Task> getTaskHistory() {
        return this.taskList;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
