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
    public void execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.remove(index - 1);
            hardDisk.saveTasks(tasks.getTasks());
            ui.showDeleteTask(task, tasks.size());
        } else {
            ui.showError("Invalid task index.");
        }
    }
}
