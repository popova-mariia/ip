package mariiachatbot.command;

import mariiachatbot.storage.HardDisk;
import mariiachatbot.task.TaskList;
import mariiachatbot.ui.Ui;
/**
 * The ByeCommand class represents the command to exit the chatbot.
 */
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
