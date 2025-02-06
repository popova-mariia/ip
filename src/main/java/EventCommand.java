public class EventCommand extends Command {
    private Event event;

    public EventCommand(Event event) {
        this.event = event;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        tasks.addTask(this.event);
        hardDisk.saveTasks(tasks.getTasks());
        ui.showAddTask(this.event, tasks.size());
    }
}