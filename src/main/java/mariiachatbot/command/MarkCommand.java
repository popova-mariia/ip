package mariiachatbot.command;

import mariiachatbot.storage.HardDisk;
import mariiachatbot.task.Task;
import mariiachatbot.task.TaskList;
import mariiachatbot.ui.Ui;
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
            return ui.showMarkTask(task);
        } else {
            return ui.showError("Invalid task index.");
        }
    }
}
