package johnny.command;

import johnny.storage.HardDisk;
import johnny.task.Task;
import johnny.task.TaskList;
import johnny.ui.Ui;
/**
 * The MarkCommand class represents the command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            hardDisk.saveTasks(tasks.getTasks());
            hardDisk.logLatestChange("mark " + index);
            return ui.showMarkTask(task);
        } else {
            return ui.showError("Invalid task index.");
        }
    }
}
