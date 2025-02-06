public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        if (index > 0 && index <= tasks.size()) {
            Task task = tasks.remove(index - 1);
            hardDisk.saveTasks(tasks.getTasks());
            ui.showDeleteTask(task, tasks.size());
        } else {
            ui.showError("Invalid task index.");
        }
    }
}