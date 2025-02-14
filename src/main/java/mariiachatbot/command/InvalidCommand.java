package mariiachatbot.command;

import mariiachatbot.storage.HardDisk;
import mariiachatbot.task.TaskList;
import mariiachatbot.ui.Ui;
/**
 * The InvalidCommand class represents the indicate taht the command cannot be parsed.
 */
public class InvalidCommand extends Command {
    private String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, HardDisk hardDisk) {

        return ui.showError(errorMessage);
    }
}
