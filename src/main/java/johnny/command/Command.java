package johnny.command;

import johnny.storage.HardDisk;
import johnny.task.TaskList;
import johnny.ui.Ui;
/**
 * The abstract Command class.
 */
public abstract class Command {

    public abstract String execute(TaskList tasks, Ui ui, HardDisk hardDisk);
    public boolean isExit() {
        return false;
    }
}
