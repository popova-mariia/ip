import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 * Subclass of task which has  description, and deadline (by when the task should be done).
 */
public class Deadline extends Task {
    LocalDate by;

    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    public LocalDate getBy() {
        return this.by;
    }

    @Override
    public String getType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]" +super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}