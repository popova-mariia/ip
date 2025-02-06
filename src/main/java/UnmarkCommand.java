public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            task.markAsNotDone();
            hardDisk.saveTasks(tasks.getTasks());
            ui.showUnmarkTask(task);
        } else {
            ui.showError("Invalid task index.");
        }
    }
}