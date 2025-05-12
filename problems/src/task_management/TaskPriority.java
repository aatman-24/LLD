package task_management;

public enum TaskPriority {
    HIGH(2),
    MEDIUM(1),
    LOW(0);

    private final int priority;

    TaskPriority(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}