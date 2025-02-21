package johnny.command;

import johnny.storage.HardDisk;
import johnny.task.TaskList;
import johnny.ui.Ui;
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
