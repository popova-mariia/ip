package mariiachatbot.command;

import mariiachatbot.storage.HardDisk;
import mariiachatbot.task.Deadline;
import mariiachatbot.ui.Ui;
import mariiachatbot.task.TaskList;

public class DeadlineCommand extends Command {
    private Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        tasks.addTask(this.deadline);
        hardDisk.saveTasks(tasks.getTasks());
        ui.showAddTask(this.deadline, tasks.size());
    }
}