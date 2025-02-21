package johnny.command;

import johnny.storage.HardDisk;
import johnny.task.Event;
import johnny.task.TaskList;
import johnny.ui.Ui;

/**
 * The EventCommand class represents the command to create a new event.
 */
public class EventCommand extends Command {
    private Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        tasks.addTask(this.event);
        hardDisk.saveTasks(tasks.getTasks());
        hardDisk.logLatestChange("event " + this.event.getDescription() + " /from " + this.event.getFrom()
                + " /to " + this.event.getTo());
        return ui.showAddTask(this.event, tasks.size());
    }
}
