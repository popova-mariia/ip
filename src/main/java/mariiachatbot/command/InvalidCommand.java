package mariiachatbot.command;

import mariiachatbot.task.TaskList;
import mariiachatbot.storage.HardDisk;
import mariiachatbot.ui.Ui;

public class InvalidCommand extends Command {
    private String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        ui.showError(errorMessage);
    }
}