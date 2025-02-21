package johnny.command;

import johnny.storage.HardDisk;
import johnny.task.Task;
import johnny.task.TaskList;
import johnny.ui.Ui;

/**
 * The UnmarkCommand class represents the command to mark a task as undone.
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            task.markAsNotDone();
            hardDisk.saveTasks(tasks.getTasks());
            hardDisk.logLatestChange("unmark " + index);
            return ui.showUnmarkTask(task);
        } else {
            return ui.showError("Invalid task index.");
        }
    }
}
