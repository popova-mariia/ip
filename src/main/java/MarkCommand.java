public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            hardDisk.saveTasks(tasks.getTasks());
            ui.showMarkTask(task);
        } else {
            ui.showError("Invalid task index.");
        }
    }
}