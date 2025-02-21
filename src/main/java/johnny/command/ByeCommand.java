package johnny.command;

import johnny.storage.HardDisk;
import johnny.task.TaskList;
import johnny.ui.Ui;
/**
 * The ByeCommand class represents the command to exit the chatbot.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList tasks, Ui ui, HardDisk hardDisk) {

        return ui.showGoodbyeMessage();
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
