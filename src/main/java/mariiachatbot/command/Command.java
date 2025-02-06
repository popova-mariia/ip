package mariiachatbot.command;

import mariiachatbot.storage.HardDisk;
import mariiachatbot.task.TaskList;
import mariiachatbot.ui.Ui;
/**
 * The abstract Command class.
 */
public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, HardDisk hardDisk);
    public boolean isExit() {
        return false;
    }
}
