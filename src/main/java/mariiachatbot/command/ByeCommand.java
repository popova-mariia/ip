package mariiachatbot.command;

import mariiachatbot.task.TaskList;
import mariiachatbot.storage.HardDisk;
import mariiachatbot.ui.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        ui.showGoodbyeMessage();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
