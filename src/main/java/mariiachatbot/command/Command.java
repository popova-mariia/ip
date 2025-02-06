package mariiachatbot.command;

import mariiachatbot.task.TaskList;
import mariiachatbot.storage.HardDisk;
import mariiachatbot.ui.Ui;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, HardDisk hardDisk);
    public boolean isExit() {
        return false;
    }
}
