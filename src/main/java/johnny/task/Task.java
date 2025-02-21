package johnny.task;
/**
 * Abstract class that represents a generic task.
 */
public abstract class Task {
    private String description;
    private boolean isDone;

    /**
     * Creates a new instance of Task.
     *
     * @param description Task description.
     */
    public Task(String description) {
        assert description != null && !description.trim().isEmpty() : "Task description cannot be empty";
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public abstract String getType();


    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return this.isDone;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}
