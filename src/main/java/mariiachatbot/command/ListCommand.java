package mariiachatbot.command;

import mariiachatbot.storage.HardDisk;
import mariiachatbot.task.TaskList;
import mariiachatbot.ui.Ui;

/**
 * The ListCommand class represents the command to list all the tasks added so far.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        ui.showTaskList(tasks);
    }
}
