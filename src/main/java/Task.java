public abstract class Task {
    String description;
    boolean isDone;

    Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    void markAsDone() {
        this.isDone = true;
    }
    public abstract String getType();


    void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + (isDone ? "X" : " ") + "] " + description;
    }
}