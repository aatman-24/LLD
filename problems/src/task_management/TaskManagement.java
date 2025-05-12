package task_management;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class TaskManagement {

    private static TaskManagement taskManagement;
    private List<User> userList;
    private List<Task> taskList;
    private PriorityQueue<Task> remainderQueue;

    private TaskManagement() {
        userList = new ArrayList<>(10);
        taskList = new ArrayList<>(10);
        remainderQueue = new PriorityQueue<>((t1, t2) -> t1.getDueDate().compareTo(t2.getDueDate()));
    }

    public static TaskManagement getInstance() {
        if (taskManagement == null) {
            taskManagement = new TaskManagement();
        }
        return taskManagement;
    }

    User createUser(String name) {
        User user = new User(userList.size(), name);
        userList.add(user);
        return user;
    }

    Task createTask(String title, String description, TaskPriority priority, User assignedTo, LocalDateTime dueDate) {
        if(userList.contains(assignedTo)) {
            Task task = assignedTo.addTask(title, description, priority, dueDate);
            taskList.add(task);
            return task;
        }
        System.out.println("User not found: " + assignedTo);
        return null;
    }

    Task updateTask(Task task, String title, String description, TaskPriority priority, LocalDateTime dueDate) {
        if(taskList.contains(task)) {
            if(title != null) task.setTitle(title);
            if(description != null) task.setDescription(description);
            if(priority != null) task.setPriority(priority);
            if(dueDate != null) task.setDueDate(dueDate);
            return task;
        }
        System.out.println("Task not found: " + task);
        return null;
    }

    boolean removeTask(Task task) {
        if(taskList.contains(task)) {
            task.getAssignedTo().removeTask(task);
            return taskList.remove(task);
        }
        System.out.println("Task not found: " + task);
        return false;
    }

    boolean assignTaskToOtherUser(Task task, User user) {
        if(taskList.contains(task)) {
            if(userList.contains(user)) {
                task.getAssignedTo().assignTaskToOtherUser(task, user);
                return true;
            }
            System.out.println("User not found: " + user);
            return false;
        }
        System.out.println("Task not found: " + task);
        return false;
    }

    void setRemainderForTask(Task task, LocalDateTime dueDate) {
        if(taskList.contains(task)) {
            remainderQueue.add(task);
        }
    }

    // call this function every day
    void updateRemainder() {
        while(!remainderQueue.isEmpty() && remainderQueue.peek().getDueDate().isEqual(LocalDateTime.now())) {
            Task task = remainderQueue.poll();
            User user = task.getAssignedTo();
            System.out.println("Remainder To User: " + user);
        }
    }

    List<Task> getUserTaskHistory(User user) {
        if(userList.contains(user)) {
            return user.getTaskHistory();
        }
        System.out.println("User not found: " + user);
        return null;
    }

    // Filtering based on the priority
    List<Task> getTasksByPriority(TaskPriority priority) {
        return this.taskList.stream().filter(task -> task.getPriority() == priority).toList();
    }

    // Filtering based on the status
    List<Task> getTasksByStatus(TaskStatus status) {
        return this.taskList.stream().filter(task -> task.getStatus() == status).toList();
    }

    // Filtering based on the assigned user
    List<Task> getTasksByUser(User user) {
        return this.taskList.stream().filter(task -> task.getAssignedTo() == user).toList();
    }
}
