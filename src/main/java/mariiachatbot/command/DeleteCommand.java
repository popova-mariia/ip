package mariiachatbot.command;

import mariiachatbot.storage.HardDisk;
import mariiachatbot.task.Task;
import mariiachatbot.task.TaskList;
import mariiachatbot.ui.Ui;

/**
 * The DeleteCommand class represents the command to remove a task at a given index.
 */
public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.remove(index - 1);
            hardDisk.saveTasks(tasks.getTasks());
            return ui.showDeleteTask(task, tasks.size());
        } else {
            return ui.showError("Invalid task index.");
        }
    }
}
