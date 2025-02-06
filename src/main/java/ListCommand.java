public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        ui.showTaskList(tasks);
    }
}