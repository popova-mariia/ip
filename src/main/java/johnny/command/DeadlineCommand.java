package johnny.command;

import johnny.storage.HardDisk;
import johnny.task.Deadline;
import johnny.task.TaskList;
import johnny.ui.Ui;

/**
 * The Deadline Command class represents the creating of a new Deadline task.
 */
public class DeadlineCommand extends Command {
    private Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        tasks.addTask(this.deadline);
        hardDisk.saveTasks(tasks.getTasks());
        hardDisk.logLatestChange("deadline " + this.deadline.getDescription() + " /by " + this.deadline.getBy());
        return ui.showAddTask(this.deadline, tasks.size());
    }
}
