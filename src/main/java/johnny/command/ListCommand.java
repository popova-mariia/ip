package johnny.command;

import johnny.storage.HardDisk;
import johnny.task.TaskList;
import johnny.ui.Ui;

/**
 * The ListCommand class represents the command to list all the tasks added so far.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        return ui.showTaskList(tasks);
    }
}
