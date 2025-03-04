package johnny.task;
/**
 * Subclass of task which has only description, no deadlines or duration.
 */
public class ToDo extends Task {
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + "[" + (this.isDone() ? "X" : " ") + "] " + this.getDescription();
    }
}
