package mariiachatbot.command;

import mariiachatbot.storage.HardDisk;
import mariiachatbot.task.Deadline;
import mariiachatbot.task.TaskList;
import mariiachatbot.ui.Ui;

/**
 * The Deadline Command class represents the creating of a new Deadline task.
 */
public class DeadlineCommand extends Command {
    private Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        tasks.addTask(this.deadline);
        hardDisk.saveTasks(tasks.getTasks());
        return ui.showAddTask(this.deadline, tasks.size());
    }
}
