public class ToDoCommand extends Command {
    private ToDo todo;

    public ToDoCommand(ToDo todo) {
        this.todo = todo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, HardDisk hardDisk) {
        tasks.addTask(this.todo);
        hardDisk.saveTasks(tasks.getTasks());
        ui.showAddTask(this.todo, tasks.size());
    }
}