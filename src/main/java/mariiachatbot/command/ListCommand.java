package mariiachatbot.command;

import mariiachatbot.task.TaskList;
import mariiachatbot.storage.HardDisk;
import mariiachatbot.ui.Ui;


public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        ui.showTaskList(tasks);
    }
}