/**
 * Subclass of task which has  description, and deadline (by when the task should be done).
 */
public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public String getBy() {
        return this.by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" +super.toString() + " (by: " + by + ")";
    }
}